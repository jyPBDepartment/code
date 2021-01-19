package com.jy.pc.Service.Impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.CommentReplyInfoDao;
import com.jy.pc.DAO.PostCommentInfoDao;
import com.jy.pc.DAO.PostInfoDao;
import com.jy.pc.Entity.PostCommentInfoEntity;
import com.jy.pc.Entity.PostInfoEntity;
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
	@Autowired
	PostInfoDao postInfoDao;
	
	@Override
	public Page<List<Map<String, Object>>> findListByContent(String content, String user,Pageable pageable) {
		String contentParam = "%"+content+"%";
		String userParam = "%"+user+"%";
//		Page<PostCommentInfoEntity> page = postCommentInfoDao.findListByContent(contentParam, userParam,pageable);
		return postCommentInfoDao.findListByContent(contentParam, userParam,pageable);
	}

	@Override
	public PostCommentInfoEntity save(PostCommentInfoEntity postCommentInfoEntity) {
		PostInfoEntity postInfo = postInfoDao.findId(postCommentInfoEntity.getPostId());
		postInfo.setCommentNum(postInfo.getCommentNum()+1);
		postInfoDao.save(postInfo);
		
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

//	@Override
//	public Page<PostCommentInfoPO> findByPostId(String postId, Pageable pageable) {
//		Page<PostCommentInfoPO> page = postCommentInfoDao.findPageByPostPO(postId,pageable);
//		for(PostCommentInfoPO info : page.getContent()) {
//			List<CommentReplyInfoPO> replyList = commentReplyInfoDao.findByCommentPO(info.getId());
//			info.setReplySize(replyList.size());
//			if(!replyList.isEmpty()) {
//				List<CommentReplyInfoPO> simpleList = replyList.subList(0, 1);
//				info.setReplyList(simpleList);
//			}
//		}
//		return page;
//	}

	@Override
	public List<PostCommentInfoEntity> findPostId(String postId) {
		return postCommentInfoDao.findPostId(postId);
	}

	@Override
	public List<PostCommentInfoEntity> findByUserId(String commentUserId) {
		
		return postCommentInfoDao.findByUserId(commentUserId);
	}

	// id查询评论列表信息
	@Override
	public Page<List<Map<String, Object>>> findCommentByUserId(String postId, String userId,Pageable pageable) {
		return postCommentInfoDao.findCommentByUserId(postId, userId,pageable);
	}

	

	@Override
	public PostCommentInfoEntity findByNewCommentId(String postId) {
		return postCommentInfoDao.findByNewCommentId(postId);
	}

	@Override
	public Page<List<Map<String, Object>>> findByCommentPage(String postId, String userId, Pageable pageable) {
		return postCommentInfoDao.findByCommentPage(postId, userId, pageable);
	}

}
