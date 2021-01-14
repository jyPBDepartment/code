package com.jy.pc.DAO;

import java.util.List;
import java.util.Map;

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
	
	//分页模糊搜索
	@Query(value = "select t3.id,t3.reply_content as replyContent,t1.comment_content as commentContent, date_format(t3.reply_date,'%Y-%m-%d %H:%i:%s') as date,t3.reply_user_name as replyUserName,t3.status,t3.is_anonymous as isAnonymous,t2.name as title from sas_case_info_comment t1,sas_case_info t2,sas_case_info_reply t3 where t1.case_id=t2.id and t1.id = t3.comment_id and t3.status != -1 and t2.name like ?1 and t3.reply_user_name like ?2 and t3.reply_content like ?3 and t1.id like ?4 order by t3.reply_date desc", 
			countQuery = "select count(0) from sas_case_info_comment t1,sas_case_info t2,sas_case_info_reply t3 where t1.case_id=t2.id and t1.id = t3.comment_id and t3.status != -1 and t2.name like ?1 and t3.reply_user_name like ?2 and t3.reply_content like ?3 and t1.id like ?4 order by t3.reply_date desc", 
			nativeQuery = true)
	public Page<List<Map<String, Object>>> findPageByParam(String name, String replyUserName, String replyContent,String commentId,Pageable pageable);
	
	// 通过回复人id查询
	@Query(value = "select * from sas_case_info_reply  where reply_user_id =:replyUserId", nativeQuery = true)
	public List<CaseInfoReplyEntity> findByReplyId(String replyUserId);
	
	//评论id、用户id查询回复信息
	@Query(value = "select t.*,date_format(t.reply_date,'%Y-%m-%d %H:%i:%s') as date,(select t.id from sas_case_info_reply t1 where t.id=t1.id and t1.reply_user_id =:userId) as is_mine from sas_case_info_reply t where t.comment_id =:commentId", nativeQuery = true)
	public List<Map<String,Object>> findReplyByUserId(@Param("commentId") String commentId,@Param("userId") String userId);
}
