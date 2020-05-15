package com.jy.pc.Service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.RoleDao;
import com.jy.pc.Entity.RoleEntity;
import com.jy.pc.Service.RoleService;
@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleDao roleDao;
	@Override
	public void add(RoleEntity role) {
		// TODO Auto-generated method stub
		roleDao.saveAndFlush(role);
	}

	@Override
	public void update(RoleEntity role) {
		// TODO Auto-generated method stub
		roleDao.save(role);
	}

	@Override
	public void delete(RoleEntity role) {
		// TODO Auto-generated method stub
		roleDao.delete(role);
	}

	@Override
	public List<RoleEntity> findAll(RoleEntity role) {
		// TODO Auto-generated method stub
		List<RoleEntity> list =new ArrayList<RoleEntity>();
		list =roleDao.findAll();
		return list;
	}

	@Override
	public RoleEntity findById(String id) {
		// TODO Auto-generated method stub
		RoleEntity role;
		role = roleDao.getOne(id);
		return role;
	}

	

}
