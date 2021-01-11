package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.PostPictureDao;
import com.jy.pc.Entity.PostPictureEntity;
import com.jy.pc.Service.PostPicutreService;

@Service
public class PostPictureServiceImpl implements PostPicutreService {

	@Autowired
	private PostPictureDao postPictureDao;

	// 添加
	@Override
	public PostPictureEntity save(PostPictureEntity postPictureEntity) {

		return postPictureDao.saveAndFlush(postPictureEntity);
	}

	// 修改
	@Override
	public void update(PostPictureEntity postPictureEntity) {

		postPictureDao.saveAndFlush(postPictureEntity);
	}

	// 刪除
	@Override
	public void delete(String id) {

		postPictureDao.deleteById(id);
	}

	// findByPostId
	@Override
	public List<PostPictureEntity> findByPostId(String postId) {

		return postPictureDao.findByPostId(postId);
	}

	// findByPhotoId
	@Override
	public List<PostPictureEntity> findByPhotoId(String photoId) {

		return postPictureDao.findByPhotoId(photoId);
	}

}
