package com.jy.pc.DAO;

import java.util.List;

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
	@Query(value = "select * from sas_case_info  t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.audit_status like ?2,1=1) order by t.create_date desc", countQuery = "select count(*) from sas_case_info t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.audit_status like ?2,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<CaseInfoEntity> findListByName(String name, String auditStatus, Pageable pageable);
	
	// 查询所有病虫害信息的最新3条记录
	@Query(value = "select * from sas_case_info  t  where audit_status='1' order by t.create_date desc limit 3", nativeQuery = true)
	public List<CaseInfoEntity> findCaseInfo();
	
	// 关键字搜索病虫害信息
	@Query(value = " select distinct t.* from (sas_case_info t inner join sas_case_key d on t.id=d.case_id inner join  sas_key_word k on d.key_id=k.id) where k.name like :name order by t.create_date desc", nativeQuery = true)
	public List<CaseInfoEntity> findCaseInfoByKey(@Param("name") String name);

}
