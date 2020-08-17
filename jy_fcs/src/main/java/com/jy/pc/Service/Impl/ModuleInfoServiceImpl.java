package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.ModuleInfoDao;
import com.jy.pc.Entity.ModuleInfoEntity;
import com.jy.pc.Service.ModuleInfoService;
import com.jy.pc.Utils.DbLogUtil;

@Service
public class ModuleInfoServiceImpl implements ModuleInfoService {
	@Autowired
	private ModuleInfoDao moduleInfoDao;
	@Autowired
	private DbLogUtil logger;

	// 新增
	@Override
	@Transactional
	public ModuleInfoEntity save(ModuleInfoEntity moduleInfo) {
		logger.initAddLog(moduleInfo);
		return moduleInfoDao.saveAndFlush(moduleInfo);
	}

	// 启用 禁用 状态切换
	@Override
	@Transactional
	public void enable(ModuleInfoEntity moduleInfo,boolean result) {
		logger.initEnableLog(moduleInfo,result);
		moduleInfoDao.saveAndFlush(moduleInfo);
	}

	// 修改
	@Override
	@Transactional
	public void update(ModuleInfoEntity moduleInfo) {
		logger.initUpdateLog(moduleInfo);
		moduleInfoDao.saveAndFlush(moduleInfo);
	}

	// 删除
	@Override
	@Transactional
	public void delete(String id) {
		logger.initDeleteLog(moduleInfoDao.findId(id));
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
