package com.jy.pc.Service.Impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.AgriculturalDao;
import com.jy.pc.DAO.GrainTradingCommentDao;
import com.jy.pc.DAO.GrainTradingReplyDao;
import com.jy.pc.Entity.AgriculturalEntity;
import com.jy.pc.Entity.GrainTradingCommentEntity;
import com.jy.pc.Service.GrainTradingCommentService;

@Service
public class GrainTradingCommentServiceImpl implements GrainTradingCommentService {
	@Autowired
	private GrainTradingCommentDao commentDao;
	@Autowired
	private GrainTradingReplyDao replyDao;
	@Autowired
	private AgriculturalDao agriculturalDao;

	@Override
	public Page<GrainTradingCommentEntity> findByPostId(String agrId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public GrainTradingCommentEntity save(GrainTradingCommentEntity grainTradingCommentEntity) {
		AgriculturalEntity grain = agriculturalDao.findId(grainTradingCommentEntity.getAid());
		grain.setCommentNum(grain.getCommentNum() + 1);
		agriculturalDao.save(grain);
		return commentDao.save(grainTradingCommentEntity);
	}

	@Override
	public void update(GrainTradingCommentEntity moduleInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void delete(String id) {
		GrainTradingCommentEntity comment = commentDao.findInfoById(id);
		AgriculturalEntity grain = agriculturalDao.findId(comment.getAid());
		grain.setCommentNum(grain.getCommentNum() - 1);
		comment.setStatus(-1);
		agriculturalDao.save(grain);
		commentDao.save(comment);
		replyDao.logicalDelete(id);
	}

	@Override
	public GrainTradingCommentEntity findId(String id) {
		return commentDao.findNormalInfoById(id);
	}

	@Override
	public void enable(GrainTradingCommentEntity grainTradingCommentEntity, boolean result) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<GrainTradingCommentEntity> findPostId(String postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<List<Map<String, Object>>> findPageByParam(String title, String name, String content,
			Pageable pageable) {
		title = "%" + title + "%";
		name = "%" + name + "%";
		content = "%" + content + "%";
		return commentDao.findPageByParam(title, name, content, pageable);
	}

	@Override
	public GrainTradingCommentEntity findNewestById(String aid) {
		return commentDao.findNewestById(aid);
	}

	@Override
	public Page<List<Map<String, Object>>> findCommentPage(String aid,Pageable pageable) {
		return commentDao.findCommentPage(aid,pageable);
	}

}
