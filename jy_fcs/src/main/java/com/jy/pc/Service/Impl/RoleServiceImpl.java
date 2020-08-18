package com.jy.pc.Service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.RoleDao;
import com.jy.pc.Entity.RoleEntity;
import com.jy.pc.Service.RoleService;
import com.jy.pc.Utils.DbLogUtil;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDao roleDao;
	@Autowired
	DbLogUtil logger;

	@Override
	public Page<RoleEntity> findListByName(String name, Pageable pageable) {
		String nameParam = "%" + name + "%";
		return roleDao.findListByName(nameParam, pageable);
	}

	@Override
	@Transactional
	public RoleEntity save(RoleEntity roleEntity) {
		RoleEntity result = roleDao.saveAndFlush(roleEntity);
		logger.initAddLog(roleEntity);
		return result;
	}

	@Override
	@Transactional
	public void enable(RoleEntity roleEntity,boolean result) {
		logger.initEnableLog(roleEntity,result);
		roleDao.saveAndFlush(roleEntity);

	}
	
	@Override
	@Transactional
	public void update(RoleEntity roleEntity) {
		logger.initUpdateLog(roleEntity);
		roleDao.saveAndFlush(roleEntity);

	}

	@Override
	public void delete(String id) {
		logger.initDeleteLog(roleDao.findId(id));
		roleDao.deleteById(id);

	}

	@Override
	public RoleEntity findId(String id) {
		return roleDao.findId(id);
	}

}
