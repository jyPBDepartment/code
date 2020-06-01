package com.jy.pc.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.RolesDao;
import com.jy.pc.Entity.RolesEntity;
import com.jy.pc.Service.RolesService;

@Service
public class RolesServiceImpl implements RolesService{
	
	@Autowired
	private RolesDao rolesDao;
	@Override
	public RolesEntity save(RolesEntity roles) {
		return rolesDao.saveAndFlush(roles);
		
	}

	@Override
	public void update(RolesEntity roles) {
		rolesDao.saveAndFlush(roles);
	}

	@Override
	public void delete(String id) {
		rolesDao.deleteById(id);
	}

	@Override
	public List<RolesEntity> findAll() {
		return rolesDao.findAll();
	}

	@Override
	public RolesEntity findId(String id) {
		return rolesDao.findId(id);
	}

	@Override
	public Page<RolesEntity> findListByName(String roleName, Integer roleType,Pageable pageable) {
		String name = "%"+roleName+"%";
		return rolesDao.findListByName(name, roleType,pageable);
	}

}
