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

//	@Query(value = "SELECT new com.jy.pc.POJO.PostCommentInfoPO(t.id,t.commentContent,t.commentUserName,t.commentDate,t.status) FROM PostCommentInfoEntity AS t "
//			+ "WHERE t.postInfoEntity.id = ?1 and t.status = 0 order by t.commentDate desc", countQuery = "SELECT count(*) FROM PostCommentInfoEntity AS t WHERE t.postInfoEntity.id = ?1 and t.status = 0 order by t.commentDate desc", nativeQuery = false)
//	public Page<PostCommentInfoPO> findPageByPostPO(@Param("postId") String postId, Pageable pageable);
//
//	@Query(value = "SELECT new com.jy.pc.POJO.PostCommentInfoPO(t.id,t.commentContent,t.commentUserName,t.commentDate,t.status) FROM PostCommentInfoEntity AS t "
//			+ "WHERE t.postInfoEntity.id = ?1 and t.status = 0 order by t.commentDate desc", nativeQuery = false)
//	public List<PostCommentInfoPO> findByPostPO(@Param("postId") String postId);

	@Query(value = "select t1.id as id,t.name as name,t1.comment_content as commentContent,t1.comment_user_name as commentUserName,date_format( t1.comment_date, '%Y-%m-%d %H:%i:%s' ) as date from sas_post_info t,sas_post_comment_info t1 where t.id = t1.post_id "
			+ " and if(?1 !='',t1.comment_content like ?1,1=1) and if(?2 !='',t1.comment_user_name like ?2,1=1) order by t1.comment_date desc ", countQuery = "select count(*) from sas_post_info t,sas_post_comment_info t1 where t.id = t1.post_id"
					+ " and if(?1 !='',t1.comment_content like ?1,1=1) and if(?2 !='',t1.comment_user_name like ?2,1=1) order by t1.comment_date desc", nativeQuery = true)
	public Page<List<Map<String, Object>>> findListByContent(String content, String user, Pageable pageable);

	// 通过帖子id查询
	@Query(value = "select * from sas_post_comment_info p where p.post_id=:postId", nativeQuery = true)
	public List<PostCommentInfoEntity> findPostId(@Param("postId") String postId);

	@Query(value = "delete from sas_post_comment_info  where id = :id", nativeQuery = true)
	@Modifying
	public void deleteByIdNotJoin(@Param("id") String id);

	// 通过评论人ID查询
	@Query(value = "SELECT * from  sas_post_comment_info t1 where t1.comment_user_id =:commentUserId", nativeQuery = true)
	public List<PostCommentInfoEntity> findByUserId(@Param("commentUserId") String commentUserId);

	// id查询评论列表信息
	@Query(value = "select t.id,t.is_anonymous as isAnonymous,t.comment_content as content,t.comment_user_name as nickName,t.comment_pic as commentPic,t.status,date_format(t.comment_date,'%Y-%m-%d %H:%i:%s') as commentTime,(select t.id from sas_post_comment_info t1 where t.id=t1.id and t1.comment_user_id = :userId) as isMyComment,(select count(*) from sas_comment_reply_info t2 where t2.comment_id = t.id)  as replyNum from sas_post_comment_info t where t.post_id = :postId", countQuery = "select count(0) from sas_post_comment_info t where t.post_id = :postId ", nativeQuery = true)
	public Page<List<Map<String, Object>>> findCommentByUserId(@Param("postId") String postId,
			@Param("userId") String userId, Pageable pageable);

	// 查询最新一条评论
	@Query(value = "select * from sas_post_comment_info t where t.post_id =:postId ORDER BY t.comment_date desc LIMIT 1", nativeQuery = true)
	public PostCommentInfoEntity findByNewCommentId(String postId);

	// 查询帖子下所有评论
	@Query(value = "select if(t1.comment_user_id = ?2,1,0) as isMyComment,t1.id,t1.comment_content AS content,date_format( t1.comment_date, '%Y-%m-%d %H:%i:%s' ) AS commentTime,t1.comment_user_name AS nickName,t1.status as status,t1.is_anonymous AS isAnonymous,t1.comment_pic as commentPic,(select count(0) from sas_comment_reply_info t2 where comment_id = t1.id and t2.status=0) as replyNum FROM sas_post_comment_info t1 where t1.status != -1 and t1.post_id = ?1 ORDER BY t1.comment_date DESC", countQuery = "select count(0) FROM sas_post_comment_info t1 where t1.status != -1 and t1.post_id = ?1", nativeQuery = true)
	public Page<List<Map<String, Object>>> findByCommentPage(String postId, String userId, Pageable pageable);
}
