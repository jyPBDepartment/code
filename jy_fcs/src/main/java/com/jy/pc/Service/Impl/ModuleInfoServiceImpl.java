package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.ModuleInfoDao;
import com.jy.pc.Entity.ModuleInfoEntity;
import com.jy.pc.Service.ModuleInfoService;

@Service
public class ModuleInfoServiceImpl implements ModuleInfoService {
	@Autowired
	private ModuleInfoDao moduleInfoDao;

	// 新增
	@Override
	public ModuleInfoEntity save(ModuleInfoEntity moduleInfo) {
		return moduleInfoDao.saveAndFlush(moduleInfo);
	}

	// 修改
	@Override
	public void update(ModuleInfoEntity moduleInfo) {
		moduleInfoDao.saveAndFlush(moduleInfo);
	}

	// 删除
	@Override
	public void delete(String id) {
		moduleInfoDao.deleteById(id);
	}

	// findById
	@Override
	public ModuleInfoEntity findId(String id) {
		return moduleInfoDao.findId(id);
	}

	// 搜索
	@Override
	public Page<ModuleInfoEntity> findListByName(String name, String status, Pageable pageable) {
		String moduleName = "%" + name + "%";
		return moduleInfoDao.findListByName(moduleName, status, pageable);
	}

	// 根据模块名称查询非禁用模块信息
	@Override
	public List<ModuleInfoEntity> findModuleListByName(String name) {
		String moduleName = "%" + name + "%";
		return moduleInfoDao.findModuleListByName(moduleName);
	}

	// 查询模块表所有非禁用信息
	@Override
	public List<ModuleInfoEntity> findModuleOn() {
		return moduleInfoDao.findModuleOn();
	}

	// 查询所有有效的模块信息
	@Override
	public List<ModuleInfoEntity> findListByMobile() {

		return moduleInfoDao.findListByMobile();
	}

}
