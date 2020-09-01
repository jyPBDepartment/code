package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.DeployModuleEntity;

public interface DeployModuleService {
	// 添加
	public DeployModuleEntity save(DeployModuleEntity deployModule);

	// 修改
	public void update(DeployModuleEntity deployModule);

	// 删除
	public void delete(String id);
	
	//通过id查询
	public DeployModuleEntity findBId(String id);
	
	//分页查询
	public Page<DeployModuleEntity> findListByName(String deployModuleName, Pageable pageable);
	
	//状态
	void enable(DeployModuleEntity deployModule, boolean result);
	
	//查询所有有效模块信息
	public Page<DeployModuleEntity> findAllDeployModuleInfo(Pageable pageable);
}
