package com.jy.pc.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.ExclusiveReplyEntity;

public interface ExclusiveReplyDao extends JpaRepository<ExclusiveReplyEntity, String>{
	
	@Query(value = "SELECT t.comment_content AS commentContent,t1.id,t1.comment_id,t1.reply_content AS replyContent,t1.reply_user_name AS replyUserName,date_format( t1.reply_date,'%Y-%m-%d %H:%i:%s' ) AS replyDate,t1.STATUS FROM sas_exclusive_comment t,sas_exclusive_reply t1 WHERE t.id = t1.comment_id "
			+ "	AND t1.comment_id =?1 AND t1.reply_user_name LIKE ?2"
			+ "	AND t1.reply_content LIKE ?3"
			+ "	AND t.comment_content LIKE ?4"
			+ "	AND t1.STATUS != '-1' order by t.reply_date desc", countQuery = "SELECT count(*) FROM sas_exclusive_comment t,sas_exclusive_reply t1 WHERE t.id = t1.comment_id"
					+ "	AND t1.comment_id =?1 "
					+ "	AND t1.reply_user_name LIKE ?2"
					+ "	AND t1.reply_content LIKE ?3"
					+ "	AND t.comment_content LIKE ?4"
					+ "	AND t1.STATUS != '-1'", nativeQuery = true)
	public Page<List<Map<String, Object>>> findReplyPageByParam(String commentId, String name, String content,String title,
			Pageable pageable);
	
	@Query(value = "select * from sas_exclusive_reply t where t.id=?1", nativeQuery = true)
	public ExclusiveReplyEntity findInfoById(String id);

	@Query(value = "select * from sas_exclusive_reply t where t.id=?1 and status != '-1'", nativeQuery = true)
	public ExclusiveReplyEntity findNormalInfoById(String id);

	@Query(value = "update sas_exclusive_reply t set t.status = '-1' where t.comment_id = ?1 ", nativeQuery = true)
	@Modifying
	public void logicalDelete(String cid);
	
	@Query(value = "select t.receive_user_name as receiveUserName,t.receive_user_id as receiveUserId,t.id,t.comment_id as commentId,t.reply_content as replyContent,t.reply_user_name as replyUserName,t.reply_pic as replyPic,t.reply_user_id as replyUserId,t.is_anonymous as isAnonymous,t.status,date_format(t.reply_date,'%Y-%m-%d %H:%i:%s') as replyDate,(select t.id from sas_exclusive_reply t1 where t.id=t1.id and t1.reply_user_id =:userId) as isMyReply from sas_exclusive_reply t where t.comment_id =:commmentId and t.status ='1' order by t.reply_date desc", 
			countQuery = "select count(0) from sas_exclusive_reply t where t.comment_id =:commmentId and t.status ='1'",
			nativeQuery = true)
	public Page<List<Map<String,Object>>> findReplyByUserId(@Param("commmentId") String commmentId,@Param("userId") String userId,Pageable pageable);
}
