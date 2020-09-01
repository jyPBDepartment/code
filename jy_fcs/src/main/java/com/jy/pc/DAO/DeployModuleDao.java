package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.DeployModuleEntity;

public interface DeployModuleDao extends JpaRepository<DeployModuleEntity, String>{
	//fingById方法
	@Query(value = "select * from sas_deploy_module_info  where id =:id", nativeQuery = true)
	public DeployModuleEntity findBId(@Param("id") String id);
	// 分页与模糊查询
	@Query(value = "select * from sas_deploy_module_info  t  where if(?1 !='',t.deploy_module_name like ?1,1=1) order by t.create_date desc", countQuery = "select count(*) from sas_deploy_module_info t  where if(?1 !='',t.deploy_module_name like ?1,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<DeployModuleEntity> findListByName(String deployModuleName, Pageable pageable);
	// 查询有效的模块信息
	@Query(value = "select * from sas_deploy_module_info t where t.status='0' order by t.create_date desc", countQuery = "select count(*) from sas_deploy_module_info t where t.status='0' order by t.create_date desc", nativeQuery = true)
	public Page<DeployModuleEntity> findAllDeployModuleInfo(Pageable pageable);
}
