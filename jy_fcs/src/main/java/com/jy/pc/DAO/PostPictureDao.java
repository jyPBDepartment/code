package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.PostPictureEntity;

public interface PostPictureDao extends JpaRepository<PostPictureEntity, String> {

	// findByPhotoId方法
	@Query(value = "select * from sas_post_picture t where t.photo_id =:photoId", nativeQuery = true)
	public List<PostPictureEntity> findByPhotoId(@Param("photoId") String photoId);

	// findByPostId方法
	@Query(value = "select * from sas_post_picture t where t.post_id = :postId", nativeQuery = true)
	public List<PostPictureEntity> findByPostId(@Param("postId") String postId);

}
