package com.jy.pc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.PostCommentInfoDao;
import com.jy.pc.Entity.PostCommentInfoEntity;
import com.jy.pc.Service.PostCommentInfoService;

@Service
public class PostCommentInfoServiceImpl implements PostCommentInfoService {
	@Autowired
	PostCommentInfoDao postCommentInfoDao;

	@Override
	public Page<PostCommentInfoEntity> findListByContent(String content, String user,Pageable pageable) {
		String contentParam = "%"+content+"%";
		String userParam = "%"+user+"%";
		return postCommentInfoDao.findListByContent(contentParam, userParam,pageable);
	}

	@Override
	public PostCommentInfoEntity save(PostCommentInfoEntity postCommentInfoEntity) {
		return postCommentInfoDao.saveAndFlush(postCommentInfoEntity);
	}

	@Override
	public void update(PostCommentInfoEntity postCommentInfoEntity) {
		postCommentInfoDao.saveAndFlush(postCommentInfoEntity);

	}

	@Override
	public void delete(String id) {
		postCommentInfoDao.deleteById(id);

	}

	@Override
	public PostCommentInfoEntity findId(String id) {
		return postCommentInfoDao.findId(id);
	}

}
