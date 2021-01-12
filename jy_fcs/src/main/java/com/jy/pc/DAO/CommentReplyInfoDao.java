package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.CommentReplyInfoEntity;
import com.jy.pc.POJO.CommentReplyInfoPO;

public interface CommentReplyInfoDao extends JpaRepository<CommentReplyInfoEntity, String>{
	@Query(value="select * from sas_comment_reply_info t where t.id =:id",nativeQuery = true)
	public CommentReplyInfoEntity findId(@Param("id")String id);
	
	//@Query(value="select * from sas_comment_reply_info t where t.comment_id =:commentId",nativeQuery = true)
	@Query(value = "SELECT new com.jy.pc.POJO.CommentReplyInfoPO(t.id,t.replyContent,t.replyUserName,t.replyDate,t.status) FROM CommentReplyInfoEntity AS t "
			+ "WHERE t.postCommentInfoEntity.id = ?1 and t.status = 0 order by t.replyDate desc", nativeQuery = false)
	public List<CommentReplyInfoPO> findByCommentPO(@Param("commentId")String commentId);
	
	@Query(value = "SELECT new com.jy.pc.POJO.CommentReplyInfoPO(t.id,t.replyContent,t.replyUserName,t.replyDate,t.status) FROM CommentReplyInfoEntity AS t "
			+ "WHERE t.postCommentInfoEntity.id = ?1 and t.status = 0 order by t.replyDate desc", 
			countQuery="SELECT count(*) FROM CommentReplyInfoEntity AS t  "
					+ "WHERE t.postCommentInfoEntity.id = ?1 and t.status = 0 order by t.replyDate desc",nativeQuery = false)
	public Page<CommentReplyInfoPO> findPageByCommentPO(@Param("commentId")String commentId,Pageable pageable);
	
	@Query(value="select * from sas_comment_reply_info t where if(?1 !='',t.reply_content like ?1,1=1) "
			+ "and if(?2 !='',t.reply_user_name like ?2,1=1) and t.comment_id =?3 order by t.reply_date desc ",
			countQuery="select count(*) from sas_comment_reply_info t where if(?1 !='',t.reply_content like ?1,1=1) "
					+ "and if(?2 !='',t.reply_user_name like ?2,1=1) and t.comment_id =?3 order by t.reply_date desc",nativeQuery = true)
	public Page<CommentReplyInfoEntity> findListByContent(String content,String user,String commentId,Pageable pageable);
	
	@Query(value="delete from sas_comment_reply_info  where comment_id = :commentId",nativeQuery = true)
	@Modifying
	public void deleteByComment(@Param("commentId")String commentId);
}
