package com.jy.pc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.ModuleInfoDao;
import com.jy.pc.Entity.ModuleInfoEntity;
import com.jy.pc.Service.ModuleInfoService;

@Service
public class ModuleInfoServiceImpl implements ModuleInfoService{
	@Autowired
	private ModuleInfoDao moduleInfoDao;

	@Override
	public ModuleInfoEntity save(ModuleInfoEntity moduleInfo) {
		return moduleInfoDao.saveAndFlush(moduleInfo);
	}

	@Override
	public void update(ModuleInfoEntity moduleInfo) {
		moduleInfoDao.saveAndFlush(moduleInfo);
	}

	@Override
	public void delete(String id) {
		moduleInfoDao.deleteById(id);
	}

	@Override
	public ModuleInfoEntity findId(String id) {
		return moduleInfoDao.findId(id);
	}

	@Override
	public Page<ModuleInfoEntity> findListByName(String name, Pageable pageable) {
		String moduleName = "%"+name+"%";
		return moduleInfoDao.findListByName(moduleName, pageable);
	}

}
