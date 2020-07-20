package com.jy.pc.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.RoleDao;
import com.jy.pc.Entity.AdminEntity;
import com.jy.pc.Entity.LimitEntity;
import com.jy.pc.Entity.RoleEntity;
import com.jy.pc.Service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleDao roleDao;
	@Override
	public RoleEntity save(RoleEntity role) {
		return roleDao.saveAndFlush(role);
	}

	@Override
	public void update(RoleEntity role) {
		roleDao.saveAndFlush(role);
	}

	@Override
	public void delete(String id) {
		roleDao.deleteById(id);
	}

	@Override
	public List<RoleEntity> findAll() {
		return roleDao.findAll();
	}

	@Override
	public RoleEntity findId(String id) {
		return roleDao.findId(id);
	}

	@Override
	public Page<RoleEntity> findListByName(String name, Pageable pageable) {
		String roleName = "%"+name+"%";
		return roleDao.findListByName(roleName, pageable);
	}

	@Override
	public List<RoleEntity> findAl() {
		return roleDao.findAl();
	}

	@Override
	public List<RoleEntity> findRoleLink() {
		return roleDao.findRoleLink();
	}

}
