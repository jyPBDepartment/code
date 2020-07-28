package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.CaseInfoEntity;

public interface CaseInfoDao extends JpaRepository<CaseInfoEntity, String> {

	// fingById方法
	@Query(value = "select * from sas_case_info  where id =:id", nativeQuery = true)
	public CaseInfoEntity findBId(@Param("id") String id);

	// 分页与模糊查询
	@Query(value = "select * from sas_case_info  t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.audit_status like ?2,1=1) order by t.create_date desc", 
			countQuery = "select count(*) from sas_case_info t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.audit_status like ?2,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<CaseInfoEntity> findListByName(String name, String auditStatus, Pageable pageable);

}
