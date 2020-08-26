package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.CommentReplyInfoDao;
import com.jy.pc.DAO.PostCommentInfoDao;
import com.jy.pc.Entity.PostCommentInfoEntity;
import com.jy.pc.POJO.CommentReplyInfoPO;
import com.jy.pc.POJO.PostCommentInfoPO;
import com.jy.pc.Service.PostCommentInfoService;
import com.jy.pc.Utils.DbLogUtil;

@Service
public class PostCommentInfoServiceImpl implements PostCommentInfoService {
	@Autowired
	PostCommentInfoDao postCommentInfoDao;
	@Autowired
	CommentReplyInfoDao commentReplyInfoDao;
	@Autowired
	DbLogUtil logger;
	
	@Override
	public Page<PostCommentInfoEntity> findListByContent(String content, String user,Pageable pageable) {
		String contentParam = "%"+content+"%";
		String userParam = "%"+user+"%";
		Page<PostCommentInfoEntity> page = postCommentInfoDao.findListByContent(contentParam, userParam,pageable);
		return postCommentInfoDao.findListByContent(contentParam, userParam,pageable);
	}

	@Override
	public PostCommentInfoEntity save(PostCommentInfoEntity postCommentInfoEntity) {
		return postCommentInfoDao.saveAndFlush(postCommentInfoEntity);
	}

	/**
	 *	切换启用 / 禁用状态 
	 */
	@Transactional
	@Override
	public void enable(PostCommentInfoEntity postCommentInfoEntity,boolean result) {
		logger.initEnableLog(postCommentInfoEntity,result);
		postCommentInfoDao.saveAndFlush(postCommentInfoEntity);

	}
	
	@Transactional
	@Override
	public void update(PostCommentInfoEntity postCommentInfoEntity) {
		logger.initUpdateLog(postCommentInfoEntity);
		postCommentInfoDao.saveAndFlush(postCommentInfoEntity);

	}

	@Transactional
	@Override
	public void delete(String id) {
		logger.initDeleteLog(postCommentInfoDao.findId(id));
		//级联删除回复信息
		commentReplyInfoDao.deleteByComment(id);
		postCommentInfoDao.deleteByIdNotJoin(id);
	}

	@Override
	public PostCommentInfoEntity findId(String id) {
		return postCommentInfoDao.findId(id);
	}

	@Override
	public Page<PostCommentInfoPO> findByPostId(String postId, Pageable pageable) {
		Page<PostCommentInfoPO> page = postCommentInfoDao.findPageByPostPO(postId,pageable);
		List<CommentReplyInfoPO> replyList = null;
		List<CommentReplyInfoPO> simpleList = null;
		for(PostCommentInfoPO info : page.getContent()) {
			replyList = commentReplyInfoDao.findByCommentPO(info.getId());
			info.setReplySize(replyList.size());
			if(!replyList.isEmpty()) {
				simpleList = replyList.subList(0, 1);
			}
			info.setReplyList(simpleList);
		}
		return page;
	}

}
