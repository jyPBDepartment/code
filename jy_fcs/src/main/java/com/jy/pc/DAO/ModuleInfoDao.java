package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.ModuleInfoEntity;

public interface ModuleInfoDao extends JpaRepository<ModuleInfoEntity, String> {
	// 查询
	@Query(value = "select * from sas_module_info t where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.status like ?2,1=1) order by t.create_date desc", countQuery = "select count(*) from sas_module_info t where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.status like ?2,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<ModuleInfoEntity> findListByName(String name, String status, Pageable pageable);

	// 通过id查询
	@Query(value = "select * from sas_module_info t where t.id =:id", nativeQuery = true)
	public ModuleInfoEntity findId(@Param("id") String id);

	// 查询模块表所有非禁用信息
	@Query(value = "select * from sas_module_info t where t.status='0' order by t.create_date desc", nativeQuery = true)
	public List<ModuleInfoEntity> findModuleOn();

	// 根据模块名称查询模块表非禁用信息
	@Query(value = "select * from sas_module_info t where t.status='0' and  if(?1 !='',t.name like ?1,1=1) order by t.create_date desc", nativeQuery = true)
	public List<ModuleInfoEntity> findModuleListByName(String name);

	// 查询所有有效的模块信息
	@Query(value = "select * from sas_module_info t where t.status ='0' order by t.create_date desc", nativeQuery = true)
	public List<ModuleInfoEntity> findListByMobile();

}
