package com.jy.pc.Service.Impl;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.DeployModuleDao;
import com.jy.pc.Entity.DeployModuleEntity;
import com.jy.pc.Service.DeployModuleService;

@Service
public class DeployModuleServiceImpl implements DeployModuleService{
	@Autowired
	private DeployModuleDao deployModuleDao;
	@Override
	public DeployModuleEntity save(DeployModuleEntity deployModule) {
		return deployModuleDao.saveAndFlush(deployModule);
	}

	@Override
	public void update(DeployModuleEntity deployModule) {
		deployModuleDao.saveAndFlush(deployModule);
	}

	@Override
	public void delete(String id) {
		deployModuleDao.deleteById(id);
	}

	@Override
	public DeployModuleEntity findBId(String id) {
		return deployModuleDao.findBId(id);
	}

	@Override
	public Page<DeployModuleEntity> findListByName(String deployModuleName, Pageable pageable) {
		String name = "%" + deployModuleName + "%";
		return deployModuleDao.findListByName(name, pageable);
	}

	@Override
	public void enable(DeployModuleEntity deployModule, boolean result) {
		deployModuleDao.saveAndFlush(deployModule);
	}

	//查询所有有效模块信息
	@Override
	public List<DeployModuleEntity> findAllDeployModuleInfo() {
		return deployModuleDao.findAllDeployModuleInfo();
	}

}
