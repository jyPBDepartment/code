package com.jy.pc.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.CommentReplyInfoDao;
import com.jy.pc.DAO.PictureInfoDAO;
import com.jy.pc.DAO.PostCommentInfoDao;
import com.jy.pc.DAO.PostInfoDao;
import com.jy.pc.DAO.PostPictureDao;
import com.jy.pc.Entity.PictureInfoEntity;
import com.jy.pc.Entity.PostInfoEntity;
import com.jy.pc.Entity.PostPictureEntity;
import com.jy.pc.POJO.CommentReplyInfoPO;
import com.jy.pc.POJO.PostCommentInfoPO;
import com.jy.pc.Service.PostInfoService;
import com.jy.pc.Utils.DbLogUtil;
import com.jy.pc.Utils.FCSDateUtil;

@Service
@Transactional
public class PostInfoServiceImpl implements PostInfoService {

	@Autowired
	private PostInfoDao invitationDao;
	@Autowired
	private PostCommentInfoDao postCommentInfoDao;
	@Autowired
	private CommentReplyInfoDao commentReplyInfoDao;
	@Autowired
	private DbLogUtil logger;
	@Autowired
	private PictureInfoDAO pictureInfoDAO;
	@Autowired
	private PostPictureDao postPictureDao;

	// 农活预约总数
	@Override
	public List<PostInfoEntity> findInva() {

		return invitationDao.findInva();
	}

	// 查询
	@Override
	public Page<PostInfoEntity> findListByName(String name, String createUser, Pageable pageable) {
		String invName = "%" + name + "%";
		String user = "%" + createUser + "%";
		return invitationDao.findListByName(invName, user, pageable);
	}

	// findById
	@Override
	public PostInfoEntity findId(String id) {
		return invitationDao.findId(id);
	}

	// 启用/禁用
	@Override
	@Transactional
	public void enable(PostInfoEntity invitation, boolean result) {
		logger.initEnableLog(invitation, result);
		invitationDao.saveAndFlush(invitation);
	}

	// 审核
	@Override
	@Transactional
	public void audit(PostInfoEntity invitation, boolean result) {
		logger.initAuditLog(invitation, result);
		invitationDao.saveAndFlush(invitation);
	}

	// 修改
	@Override
	public void update(PostInfoEntity invitation) {
		invitationDao.saveAndFlush(invitation);
	}

	// 添加
	@Override
	public PostInfoEntity save(PostInfoEntity postInfo) {
		return invitationDao.saveAndFlush(postInfo);
	}

	// 加载所有帖子 评论回复 列表信息
	@Override
	public Page<PostInfoEntity> findListWithSub(String postType, Pageable pageable) {
		Page<PostInfoEntity> page = invitationDao.findListWithSub(postType, pageable);
		List<PostCommentInfoPO> commentList = null;
		List<CommentReplyInfoPO> replyList = null;
		for (PostInfoEntity post : page.getContent()) {
			commentList = postCommentInfoDao.findByPostPO(post.getId());
			/*
			 * for (PostCommentInfoPO comment : commentList) { replyList =
			 * commentReplyInfoDao.findByCommentPO(comment.getId());
			 * comment.setReplyList(replyList); comment.setReplySize(replyList.size()); }
			 */
			post.setCommentSize(commentList.size());
			post.setTime(FCSDateUtil.CalculateTime(post.getCreateDate()));
			// post.setCommentList(commentList);
		}
		return page;
	}

	@Override
	@Transactional
	public void delete(String id) {
		invitationDao.deleteById(id);
	}

	@Override
	public PostInfoEntity Boutique(PostInfoEntity invitation, boolean result) {

		return invitationDao.saveAndFlush(invitation);
	}

	// 帖子添加接口
	@Override
	public PostInfoEntity saveAgr(String[] addItem, PostInfoEntity postInfoEntity) {
		Date date = new Date();
		postInfoEntity.setCreateDate(date);
		postInfoEntity.setStatus("1");// 默认禁用
		postInfoEntity.setAuditStatus("0");// 默认审核状态 未审核
		invitationDao.saveAndFlush(postInfoEntity);
		for (int i = 0; i < addItem.length; i++) {
			PictureInfoEntity pictureInfoEntity = new PictureInfoEntity();
			PostPictureEntity postPictureEntity = new PostPictureEntity();
			pictureInfoEntity.setPicName(postInfoEntity.getName());
			pictureInfoEntity.setPicUrl(addItem[i]);
			pictureInfoDAO.saveAndFlush(pictureInfoEntity);
			postPictureEntity.setPhotoId(postInfoEntity.getId());
			postPictureEntity.setPostId(pictureInfoEntity.getId());
			postPictureDao.saveAndFlush(postPictureEntity);
		}
		return postInfoEntity;
	}

	// 查询最新最火热议好评精品
	@Override
	public Page<PostInfoEntity> findByType(String type, Pageable pageable) {

		Page<PostInfoEntity> result = null;
		switch (type) {
		case "0":
			result = invitationDao.findListByHotLabel(pageable);// 最火
			break;
		case "1":
			result = invitationDao.findListByNewLabel(pageable);// 最新
			break;
		case "2":
			result = invitationDao.findListByBoutiqueLabel(pageable);// 精品
			break;
		case "3":
			result = invitationDao.findListByThumbsLabel(pageable);// 好评
			break;
		case "4":
			result = invitationDao.findListByCommentLabel(pageable);// 热议
			break;
		}

		return result;
	}

	// 通过id,userId查询帖子信息
	@Override
	public Map<String, Object> findInfoByPostUserId(String id, String userId) {
		return invitationDao.findInfoByPostUserId(id, userId);
	}


}
