package com.jy.pc.DAO;

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
}
