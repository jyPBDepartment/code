package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.CasePraiseEntity;

//看图识病点赞表 DAO
public interface CasePraiseDAO extends JpaRepository<CasePraiseEntity, String> {
	// 通过看图识病id 点赞用户id查询
	@Query(value = "SELECT * FROM sas_case_praise t where t.case_id =:caseId and t.praise_user_id =:praiseUserId", nativeQuery = true)
	public CasePraiseEntity findUserId(String caseId, String praiseUserId);
}
