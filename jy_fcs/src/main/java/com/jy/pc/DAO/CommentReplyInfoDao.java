package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.CommentReplyInfoEntity;

public interface CommentReplyInfoDao extends JpaRepository<CommentReplyInfoEntity, String>{
	@Query(value="select * from sas_comment_reply_info t where t.id =:id",nativeQuery = true)
	public CommentReplyInfoEntity findId(@Param("id")String id);
	
	@Query(value="select * from sas_comment_reply_info t where if(?1 !='',t.reply_content like ?1,1=1) order by t.reply_date desc ",
			countQuery="select count(*) from sas_comment_reply_info t where if(?1 !='',t.reply_content like ?1,1=1) order by t.reply_date desc",nativeQuery = true)
	public Page<CommentReplyInfoEntity> findListByContent(String content,Pageable pageable);
}
