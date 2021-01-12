package com.jy.pc.DAO;

import java.util.List;
import java.util.Map;

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
			+ "WHERE t.postInfoEntity.id = ?1 and t.status = 0 order by t.commentDate desc", 
			countQuery = "SELECT count(*) FROM PostCommentInfoEntity AS t WHERE t.postInfoEntity.id = ?1 and t.status = 0 order by t.commentDate desc",nativeQuery = false)
	public Page<PostCommentInfoPO> findPageByPostPO(@Param("postId") String postId, Pageable pageable);
	
	@Query(value = "SELECT new com.jy.pc.POJO.PostCommentInfoPO(t.id,t.commentContent,t.commentUserName,t.commentDate,t.status) FROM PostCommentInfoEntity AS t "
			+ "WHERE t.postInfoEntity.id = ?1 and t.status = 0 order by t.commentDate desc", nativeQuery = false)
	public List<PostCommentInfoPO> findByPostPO(@Param("postId") String postId);

	@Query(value = "select t1.id as id,t.name as name,t1.comment_content as commentContent,t1.comment_user_name as commentUserName,date_format( t1.comment_date, '%Y-%m-%d %H:%i:%s' ) as date from sas_post_info t,sas_post_comment_info t1 where t.id = t1.post_id " + 
			" and if(?1 !='',t1.comment_content like ?1,1=1) and if(?2 !='',t1.comment_user_name like ?2,1=1) order by t1.comment_date desc ", 
			countQuery = "select count(*) from sas_post_info t,sas_post_comment_info t1 where t.id = t1.post_id" + 
					" and if(?1 !='',t1.comment_content like ?1,1=1) and if(?2 !='',t1.comment_user_name like ?2,1=1) order by t1.comment_date desc", nativeQuery = true)
	public Page<List<Map<String, Object>>> findListByContent(String content, String user, Pageable pageable);

	//通过帖子id查询
	@Query(value="select * from sas_post_comment_info p where p.post_id=:postId",nativeQuery = true)
	public List<PostCommentInfoEntity> findPostId(@Param("postId") String postId);
	
	@Query(value="delete from sas_post_comment_info  where id = :id",nativeQuery = true)
	@Modifying
	public void deleteByIdNotJoin(@Param("id") String id);
}
