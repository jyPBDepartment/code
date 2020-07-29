package com.jy.pc.Service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.RoleDao;
import com.jy.pc.Entity.RoleEntity;
import com.jy.pc.Service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDao roleDao;

	@Override
	public Page<RoleEntity> findListByName(String name, Pageable pageable) {
		String nameParam = "%"+name+"%";
		return roleDao.findListByName(nameParam, pageable);
	}

	@Override
	public RoleEntity save(RoleEntity roleEntity) {
		return roleDao.saveAndFlush(roleEntity);
	}

	@Override
	public void update(RoleEntity roleEntity) {
		roleDao.saveAndFlush(roleEntity);
		
	}

	@Override
	public void delete(String id) {
		roleDao.deleteById(id);
		
	}

	@Override
	public RoleEntity findId(String id) {
		return roleDao.findId(id);
	}
	

}
