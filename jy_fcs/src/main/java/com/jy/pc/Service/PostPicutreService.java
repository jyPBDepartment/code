package com.jy.pc.Service;

import java.util.List;

import com.jy.pc.Entity.PostPictureEntity;

public interface PostPicutreService {

	// 添加
	public PostPictureEntity save(PostPictureEntity postPictureEntity);

	// 修改
	public void update(PostPictureEntity postPictureEntity);

	// 删除
	public void delete(String id);

	// findByPhotoId
	public List<PostPictureEntity> findByPhotoId(String photoId);

	// findByPostId
	public List<PostPictureEntity> findByPostId(String postId);
}
