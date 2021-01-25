package com.jy.pc.Service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.ExclusiveReplyDao;
import com.jy.pc.Entity.ExclusiveReplyEntity;
import com.jy.pc.Service.ExclusiveReplyService;

@Service
public class ExclusiveReplyServiceImpl implements ExclusiveReplyService{
	@Autowired
	private ExclusiveReplyDao replyDao;
	
	@Override
	public Page<List<Map<String, Object>>> findReplyPageByParam(String commentId,String content, String user,String title, Pageable pageable) {
		user = "%" + user + "%";
		content = "%" + content + "%";
		title = "%" + title + "%";
		return replyDao.findReplyPageByParam(commentId,content,user,title,pageable);
	}

	@Override
	public Page<ExclusiveReplyEntity> findByPostId(String agrId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExclusiveReplyEntity save(ExclusiveReplyEntity exclusiveReplyEntity) {
		return replyDao.saveAndFlush(exclusiveReplyEntity);
	}

	@Override
	public void update(ExclusiveReplyEntity exclusiveReplyEntity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		ExclusiveReplyEntity reply = replyDao.findInfoById(id);
		reply.setStatus("-1");
		replyDao.save(reply);
	}

	@Override
	public ExclusiveReplyEntity findId(String id) {
		return replyDao.findNormalInfoById(id);
	}

	@Override
	public void enable(ExclusiveReplyEntity exclusiveReplyEntity, boolean result) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ExclusiveReplyEntity> findPostId(String postId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Page<List<Map<String,Object>>> findReplyByUserId(String commmentId,String userId,Pageable pageable){
		return replyDao.findReplyByUserId(commmentId,userId,pageable);
	}

}
