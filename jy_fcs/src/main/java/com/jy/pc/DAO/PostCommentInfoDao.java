package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.PostCommentInfoEntity;
import com.jy.pc.POJO.PostCommentInfoPO;

public interface PostCommentInfoDao extends JpaRepository<PostCommentInfoEntity, String> {

	@Query(value = "select * from sas_post_comment_info t where t.id =:id", nativeQuery = true)
	public PostCommentInfoEntity findId(@Param("id") String id);

	@Query(value = "SELECT new com.jy.pc.POJO.PostCommentInfoPO(t.id,t.commentContent,t.commentUserName,t.commentDate,t.status) FROM PostCommentInfoEntity AS t "
			+ "WHERE t.postInfoEntity.id = ?1 and t.status = 0 order by t.commentDate desc", nativeQuery = false)
	public List<PostCommentInfoPO> findByPostPO(@Param("postId") String postId);

	@Query(value = "select * from sas_post_comment_info t where if(?1 !='',t.comment_content like ?1,1=1) and "
			+ "if(?2 !='',t.comment_user_name like ?2,1=1) order by t.comment_date desc ", countQuery = "select count(*) from sas_post_comment_info t where if(?1 !='',t.comment_content like ?1,1=1) and "
					+ "if(?2 !='',t.comment_user_name like ?2,1=1) order by t.comment_date desc", nativeQuery = true)
	public Page<PostCommentInfoEntity> findListByContent(String content, String user, Pageable pageable);

	@Query(value="delete from sas_post_comment_info  where id = :id",nativeQuery = true)
	@Modifying
	public void deleteByIdNotJoin(@Param("id") String id);

}
