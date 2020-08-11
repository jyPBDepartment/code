package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.CommentReplyInfoEntity;

public interface CommentReplyInfoDao extends JpaRepository<CommentReplyInfoEntity, String>{
	@Query(value="select * from sas_comment_reply_info t where t.id =:id",nativeQuery = true)
	public CommentReplyInfoEntity findId(@Param("id")String id);
	
	@Query(value="select * from sas_comment_reply_info t where t.comment_id =:commentId",nativeQuery = true)
	public List<CommentReplyInfoEntity> findByComment(@Param("commentId")String commentId);
	
	@Query(value="select * from sas_comment_reply_info t where if(?1 !='',t.reply_content like ?1,1=1) "
			+ "and if(?2 !='',t.reply_user_name like ?2,1=1) order by t.reply_date desc ",
			countQuery="select count(*) from sas_comment_reply_info t where if(?1 !='',t.reply_content like ?1,1=1) "
					+ "and if(?2 !='',t.reply_user_name like ?2,1=1)order by t.reply_date desc",nativeQuery = true)
	public Page<CommentReplyInfoEntity> findListByContent(String content,String user,Pageable pageable);
	
	@Query(value="delete from sas_comment_reply_info  where comment_id = :commentId",nativeQuery = true)
	@Modifying
	public void deleteByComment(@Param("commentId")String commentId);
}
