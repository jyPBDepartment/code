package com.jy.pc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.DAO.CommentReplyInfoDao;
import com.jy.pc.Entity.CommentReplyInfoEntity;
import com.jy.pc.Service.CommentReplyInfoService;

public class CommentReplyInfoServiceImpl implements CommentReplyInfoService {

	@Autowired
	CommentReplyInfoDao commentReplyInfoDao;
	
	@Override
	public Page<CommentReplyInfoEntity> findListByContent(String content, Pageable pageable) {
		String contentParam = "%"+content+"%";
		return commentReplyInfoDao.findListByContent(contentParam, pageable);
	}

	@Override
	public CommentReplyInfoEntity save(CommentReplyInfoEntity commentReplyInfoEntity) {
		return commentReplyInfoDao.saveAndFlush(commentReplyInfoEntity);
	}

	@Override
	public void update(CommentReplyInfoEntity commentReplyInfoEntity) {
		commentReplyInfoDao.saveAndFlush(commentReplyInfoEntity);

	}

	@Override
	public void delete(String id) {
		commentReplyInfoDao.deleteById(id);

	}

	@Override
	public CommentReplyInfoEntity findId(String id) {
		return commentReplyInfoDao.findId(id);
	}

}
