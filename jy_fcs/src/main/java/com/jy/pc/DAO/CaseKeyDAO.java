package com.jy.pc.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.CaseKeyEntity;

public interface CaseKeyDAO extends JpaRepository<CaseKeyEntity, String>{
	@Query(value="delete from sas_case_key  where case_id = :caseId",nativeQuery = true)
	@Modifying
	public void deleteByCase(@Param("caseId")String caseId);
}
