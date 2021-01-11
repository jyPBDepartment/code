package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.CaseInfoReplyEntity;

//看图识病回复信息表 DAO
public interface CaseInfoReplyDAO extends JpaRepository<CaseInfoReplyEntity, String> {
	// 分页与模糊查询
	@Query(value = "select * from sas_case_info_reply  t  where if(?1 !='',t.reply_content like ?1,1=1) and if(?2 !='',t.reply_user_name like ?2,1=1) order by t.reply_date desc",
			countQuery = "select count(*) from sas_case_info_reply t  where if(?1 !='',t.reply_content like ?1,1=1) and if(?2 !='',t.reply_user_name like ?2,1=1) order by t.reply_date desc", nativeQuery = true)
	public Page<CaseInfoReplyEntity> findListByName(String replyContent, String replyUserName, Pageable pageable);

	// 通过评论id查询
	@Query(value = "select * from sas_case_info_reply t where t.comment_id =:commentId", nativeQuery = true)
	public List<CaseInfoReplyEntity> findByCommentId(String commentId);
	
	// 通过id查询
	@Query(value = "select * from sas_case_info_reply  where id =:id", nativeQuery = true)
	public CaseInfoReplyEntity findId(@Param("id") String id);
}
