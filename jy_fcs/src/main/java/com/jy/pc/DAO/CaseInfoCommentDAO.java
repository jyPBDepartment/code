package com.jy.pc.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.CaseInfoCommentEntity;
public interface CaseInfoCommentDAO extends JpaRepository<CaseInfoCommentEntity, String>{
	// fingById方法
	@Query(value = "select * from sas_case_info_comment  where id =:id", nativeQuery = true)
	public CaseInfoCommentEntity findBId(@Param("id") String id);

	// 分页与模糊查询
	@Query(value = "select * from sas_case_info_comment  t  where if(?1 !='',t.comment_content like ?1,1=1) and if(?2 !='',t.comment_user_name like ?2,1=1) and if(?3 !='',t.case_id like ?3,1=1) order by t.comment_date desc",
			countQuery = "select count(*) from sas_case_info_comment t  where if(?1 !='',t.comment_content like ?1,1=1) and if(?2 !='',t.comment_user_name like ?2,1=1) and if(?3 !='',t.case_id like ?3,1=1) order by t.comment_date desc", nativeQuery = true)
	public Page<CaseInfoCommentEntity> findListByName(String commentContent, String commentUserName, String caseId, Pageable pageable);
	
	//分页与模糊查询
	@Query(value = "select t.id,t.comment_content as commentContent,date_format(t.comment_date,'%Y-%m-%d %H:%i:%s') as date,t.comment_user_name as commentUserName,t.status,t.is_anonymous as isAnonymous,t1.name as title from sas_case_info_comment t,sas_case_info t1 where t.case_id=t1.id and t.status != -1 and t1.name like ?1 and t.comment_user_name like ?2 and t.comment_content like ?3 order by t.comment_date desc", 
			countQuery = "select count(0) from sas_case_info_comment t,sas_case_info t1 where t.case_id=t1.id and t1.status != -1 and t2.name like ?1 and t1.comment_user_name like ?2 and t1.comment_content like ?3 order by t1.comment_date desc", nativeQuery = true)
	public Page<List<Map<String, Object>>> findPageByCase(String name, String commentUserName, String commentContent,Pageable pageable);

	// 通过评论人id查询
	@Query(value = "select * from sas_case_info_comment  where comment_user_id =:commentUserId", nativeQuery = true)
	public List<CaseInfoCommentEntity> findByUserId(String commentUserId);
	
	//根据看图识病id、用户id查询评论列表
	@Query(value = "select t.*,date_format(t.comment_date,'%Y-%m-%d %H:%i:%s') as date,(select t.id from sas_case_info_comment t1 where t.id=t1.id and t1.comment_user_id = :userId) as is_mine,(select count(*) from sas_case_info_reply t2 where t2.comment_id = t.id)  as replyCount from sas_case_info_comment t where t.case_id = :caseId", nativeQuery = true)
	public List<Map<String,Object>> findCommentByUserId(@Param("caseId") String caseId,@Param("userId") String userId);
}
