package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.CaseInfoIrrelevantEntity;

//看图识病与我无关表DAO
public interface CaseInfoIrrelevantDAO extends JpaRepository<CaseInfoIrrelevantEntity, String>{
	
	// 根据用户id 看图识病id查询
	@Query(value = "select * from sas_case_info_irrelevant t where t.case_id =:caseId and  t.irrelevantn_user_id =:irrelevantnUserId", nativeQuery = true)
	public CaseInfoIrrelevantEntity findCaseUserId(String caseId, String irrelevantnUserId);

}
