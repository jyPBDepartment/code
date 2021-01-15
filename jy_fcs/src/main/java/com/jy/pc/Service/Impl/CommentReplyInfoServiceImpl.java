package com.jy.pc.Service.Impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.CommentReplyInfoDao;
import com.jy.pc.Entity.CommentReplyInfoEntity;
import com.jy.pc.POJO.CommentReplyInfoPO;
import com.jy.pc.Service.CommentReplyInfoService;
import com.jy.pc.Utils.DbLogUtil;
@Service
public class CommentReplyInfoServiceImpl implements CommentReplyInfoService {

	@Autowired
	CommentReplyInfoDao commentReplyInfoDao;
	@Autowired
	DbLogUtil logger;
	
	@Override
	public Page<CommentReplyInfoEntity> findListByContent(String content,String user,String commentId, Pageable pageable) {
		String contentParam = "%"+content+"%";
		String userParam = "%"+user+"%";
		return commentReplyInfoDao.findListByContent(contentParam,userParam,commentId, pageable);
	}

	@Override
	public CommentReplyInfoEntity save(CommentReplyInfoEntity commentReplyInfoEntity) {
		return commentReplyInfoDao.saveAndFlush(commentReplyInfoEntity);
	}

	@Transactional
	@Override
	public void enable(CommentReplyInfoEntity commentReplyInfoEntity,boolean result) {
		logger.initEnableLog(commentReplyInfoEntity,result);
		commentReplyInfoDao.saveAndFlush(commentReplyInfoEntity);

	}
	
	@Transactional
	@Override
	public void update(CommentReplyInfoEntity commentReplyInfoEntity) {
		logger.initUpdateLog(commentReplyInfoEntity);
		commentReplyInfoDao.saveAndFlush(commentReplyInfoEntity);

	}

	@Transactional
	@Override
	public void delete(String id) {
		logger.initDeleteLog(commentReplyInfoDao.findId(id));
		commentReplyInfoDao.deleteById(id);

	}

	@Override
	public CommentReplyInfoEntity findId(String id) {
		return commentReplyInfoDao.findId(id);
	}

	@Override
	public Page<CommentReplyInfoPO> findByCommentId(String commentId, Pageable pageable) {
		// TODO Auto-generated method stub
		return commentReplyInfoDao.findPageByCommentPO(commentId,pageable);
	}

	// 条件查询帖子回复人ID
	@Override
	public List<CommentReplyInfoEntity> findByUserReplyId(String replyUserId) {
		return commentReplyInfoDao.findByUserReplyId(replyUserId);
	}

	// 根据id,userId查询回复信息
	@Override
	public Page<List<Map<String, Object>>> findReplyByUserId(String commentId, String userId, Pageable pageable) {
		return commentReplyInfoDao.findReplyByUserId(commentId, userId,pageable);
	}

	// 查询是否为自己回复
	@Override
	public Page<List<Map<String, Object>>> findByIsMyReplyPage(String cid, String userId, Pageable pageable) {
		return commentReplyInfoDao.findByIsMyReplyPage(cid, userId, pageable);
	}

	//查询评论下所有回复
	@Override
	public Page<List<Map<String, Object>>> findReplyPage(String commentId, String userId, Pageable pageable) {
		return commentReplyInfoDao.findReplyPage(commentId, userId, pageable);
	}

}
