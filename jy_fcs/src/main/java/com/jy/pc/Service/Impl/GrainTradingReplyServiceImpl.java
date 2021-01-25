package com.jy.pc.Service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.GrainTradingReplyDao;
import com.jy.pc.Entity.GrainTradingReplyEntity;
import com.jy.pc.Service.GrainTradingReplyService;

@Service
public class GrainTradingReplyServiceImpl implements GrainTradingReplyService {

	@Autowired
	private GrainTradingReplyDao replyDao;
	
	@Override
	public Page<GrainTradingReplyEntity> findListByContent(String content, String user, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<GrainTradingReplyEntity> findByPostId(String agrId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GrainTradingReplyEntity save(GrainTradingReplyEntity grainTradingReplyEntity) {
		return replyDao.saveAndFlush(grainTradingReplyEntity);
	}

	@Override
	public void update(GrainTradingReplyEntity grainTradingReplyEntity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		GrainTradingReplyEntity reply = replyDao.findInfoById(id);
		reply.setStatus("-1");
		replyDao.save(reply);
	}

	@Override
	public GrainTradingReplyEntity findId(String id) {
		return replyDao.findNormalInfoById(id);
	}

	@Override
	public void enable(GrainTradingReplyEntity grainTradingReplyEntity, boolean result) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<GrainTradingReplyEntity> findPostId(String postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<List<Map<String, Object>>> findPageByParam(String title, String name, String content,String aid,
			Pageable pageable) {
		title = "%" + title + "%";
		name = "%" + name + "%";
		content = "%" + content + "%";
		return replyDao.findPageByParam(title, name, content, aid,pageable);
	}

	@Override
	public Page<List<Map<String, Object>>> findCommentPage(String cid, String userId,Pageable pageable) {
		return replyDao.findCommentPage(cid,userId,pageable);
	}

	@Override
	public List<GrainTradingReplyEntity> getMyReply(String userId) {
		return replyDao.getMyReply(userId);
	}

}
