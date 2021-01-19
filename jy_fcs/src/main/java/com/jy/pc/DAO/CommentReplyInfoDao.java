package com.jy.pc.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.CommentReplyInfoEntity;

public interface CommentReplyInfoDao extends JpaRepository<CommentReplyInfoEntity, String> {
	@Query(value = "select * from sas_comment_reply_info t where t.id =:id", nativeQuery = true)
	public CommentReplyInfoEntity findId(@Param("id") String id);

	// @Query(value="select * from sas_comment_reply_info t where t.comment_id
	// =:commentId",nativeQuery = true)
//	@Query(value = "SELECT new com.jy.pc.POJO.CommentReplyInfoPO(t.id,t.replyContent,t.replyUserName,t.replyDate,t.status) FROM CommentReplyInfoEntity AS t "
//			+ "WHERE t.postCommentInfoEntity.id = ?1 and t.status = 0 order by t.replyDate desc", nativeQuery = false)
//	public List<CommentReplyInfoPO> findByCommentPO(@Param("commentId")String commentId);
//	
//	@Query(value = "SELECT new com.jy.pc.POJO.CommentReplyInfoPO(t.id,t.replyContent,t.replyUserName,t.replyDate,t.status) FROM CommentReplyInfoEntity AS t "
//			+ "WHERE t.postCommentInfoEntity.id = ?1 and t.status = 0 order by t.replyDate desc", 
//			countQuery="SELECT count(*) FROM CommentReplyInfoEntity AS t  "
//					+ "WHERE t.postCommentInfoEntity.id = ?1 and t.status = 0 order by t.replyDate desc",nativeQuery = false)
//	public Page<CommentReplyInfoPO> findPageByCommentPO(@Param("commentId")String commentId,Pageable pageable);

	@Query(value = "select * from sas_comment_reply_info t where if(?1 !='',t.reply_content like ?1,1=1) "
			+ "and if(?2 !='',t.reply_user_name like ?2,1=1) and t.comment_id =?3 and t.status != -1 order by t.reply_date desc ", countQuery = "select count(*) from sas_comment_reply_info t where if(?1 !='',t.reply_content like ?1,1=1) "
					+ "and if(?2 !='',t.reply_user_name like ?2,1=1) and t.comment_id =?3 and t.status != -1 order by t.reply_date desc", nativeQuery = true)
	public Page<CommentReplyInfoEntity> findListByContent(String content, String user, String commentId,
			Pageable pageable);

	@Query(value = "delete from sas_comment_reply_info  where comment_id = :commentId", nativeQuery = true)
	@Modifying
	public void deleteByComment(@Param("commentId") String commentId);

	// 条件查询帖子回复人ID
	@Query(value = "SELECT * from  sas_comment_reply_info t where t.reply_user_id =:replyUserId", nativeQuery = true)
	public List<CommentReplyInfoEntity> findByUserReplyId(@Param("replyUserId") String replyUserId);

	// 根据id,userId查询回复信息
	@Query(value = "select t.id,t.comment_id as commentId,t.reply_content as replyContent,t.reply_user_name as replyUserName,t.reply_user_id as replyUserId,t.is_anonymous as isAnonymous,t.reply_pic as replyPic,t.status,date_format(t.reply_date,'%Y-%m-%d %H:%i:%s') as replyDate,(select t.id from sas_comment_reply_info t1 where t.id=t1.id and t1.reply_user_id =:userId) as isMyReply from sas_comment_reply_info t where t.comment_id =:commentId", countQuery = "select count(0) from sas_post_comment_info t where t.comment_id =:commentId", nativeQuery = true)
	public Page<List<Map<String, Object>>> findReplyByUserId(@Param("commentId") String commentId,
			@Param("userId") String userId, Pageable pageable);

	// 查询评论下所有回复
	@Query(value = "select t.id,if(reply_user_id = ?2,1,0) as isMyReply,t.status as status,date_format( t.reply_date, '%Y-%m-%d %H:%i:%s' ) AS replyDate,t.reply_user_id as replyUserId,t.reply_pic as replyPic,t.reply_content as replyContent,t.comment_id as commentId,reply_user_name as replyUserName,t.reply_user_name as user,t.is_anonymous as isAnonymous from sas_comment_reply_info t where t.comment_id=?1 and status != -1 order by reply_date desc ", countQuery = "select count(0) from sas_comment_reply_info t where t.comment_id=?1 and status != -1", nativeQuery = true)
	public Page<List<Map<String, Object>>> findReplyPage(String commentId, String userId, Pageable pageable);

	// 通过评论id查询
	@Query(value = "select * from sas_comment_reply_info t where t.comment_id =:commentId", nativeQuery = true)
	public List<CommentReplyInfoEntity> findByCommentId(String commentId);
}
