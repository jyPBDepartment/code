package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.CommentReplyInfoDao;
import com.jy.pc.DAO.PostCommentInfoDao;
import com.jy.pc.DAO.PostInfoDao;
import com.jy.pc.Entity.PostInfoEntity;
import com.jy.pc.POJO.CommentReplyInfoPO;
import com.jy.pc.POJO.PostCommentInfoPO;
import com.jy.pc.Service.PostInfoService;
import com.jy.pc.Utils.DbLogUtil;

@Service

public class PostInfoServiceImpl implements PostInfoService {

	@Autowired
	private PostInfoDao invitationDao;
	@Autowired
	private PostCommentInfoDao postCommentInfoDao;
	@Autowired
	private CommentReplyInfoDao commentReplyInfoDao;
	@Autowired
	private DbLogUtil logger;

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
			for (PostCommentInfoPO comment : commentList) {
				replyList = commentReplyInfoDao.findByCommentPO(comment.getId());
				comment.setReplyList(replyList);
				comment.setReplySize(replyList.size());
			}
			post.setCommentSize(commentList.size());
			post.setCommentList(commentList);
		}
		return page;
	}

}
