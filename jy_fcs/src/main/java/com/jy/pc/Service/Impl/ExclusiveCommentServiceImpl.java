package com.jy.pc.Service.Impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.ArticleManageDao;
import com.jy.pc.DAO.ExclusiveCommentDao;
import com.jy.pc.DAO.ExclusiveReplyDao;
import com.jy.pc.Entity.ArticleManageEntity;
import com.jy.pc.Entity.ExclusiveCommentEntity;
import com.jy.pc.Service.ExclusiveCommentService;

@Service
public class ExclusiveCommentServiceImpl implements ExclusiveCommentService{
	@Autowired
	private ExclusiveCommentDao commentDao;
	@Autowired
	private ExclusiveReplyDao replyDao;
	@Autowired
	private ArticleManageDao articleManageDao;

	@Override
	public Page<ExclusiveCommentEntity> findByPostId(String agrId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ExclusiveCommentEntity save(ExclusiveCommentEntity exclusiveCommentEntity) {
		ArticleManageEntity articleManageEntity = articleManageDao.findBId(exclusiveCommentEntity.getArtId());
		articleManageEntity.setCommentNum(articleManageEntity.getCommentNum() + 1);
		articleManageDao.save(articleManageEntity);
		return commentDao.save(exclusiveCommentEntity);
	}

	@Override
	public void update(ExclusiveCommentEntity moduleInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void delete(String id) {
		ExclusiveCommentEntity comment = commentDao.findInfoById(id);
		comment.setStatus(-1);
		commentDao.save(comment);
		replyDao.logicalDelete(id);
	}

	@Override
	public ExclusiveCommentEntity findId(String id) {
		return commentDao.findNormalInfoById(id);
	}

	@Override
	public void enable(ExclusiveCommentEntity exclusiveCommentEntity, boolean result) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ExclusiveCommentEntity> findPostId(String postId) {
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
	
	public Page<List<Map<String,Object>>> findCommentByUserId(String artId,String userId,Pageable pageable){
		return commentDao.findCommentByUserId(artId,userId,pageable);
	}
}
