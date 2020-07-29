package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.RoleMenuRelationDao;
import com.jy.pc.Entity.RoleMenuRelationEntity;
import com.jy.pc.Service.RoleMenuRelationService;

@Service
public class RoleMenuRelationImpl implements RoleMenuRelationService{

	@Autowired
	RoleMenuRelationDao roleMenuRelationDao;

	@Override
	public RoleMenuRelationEntity save(RoleMenuRelationEntity roleMenuRelationEntity) {
		return roleMenuRelationDao.saveAndFlush(roleMenuRelationEntity);
	}

	@Override
	public void update(RoleMenuRelationEntity roleMenuRelationEntity) {
		roleMenuRelationDao.saveAndFlush(roleMenuRelationEntity);
		
	}

	@Override
	public void delete(String id) {
		roleMenuRelationDao.deleteById(id);		
	}

	@Override
	public RoleMenuRelationEntity findId(String id) {
		return roleMenuRelationDao.findId(id);
	}

	@Override
	public List<RoleMenuRelationEntity> findRelationByRole(String roleId) {
		return roleMenuRelationDao.findRelationByRole(roleId);
	}

	@Override
	public List<RoleMenuRelationEntity> findRelationByMenu(String menuId) {
		return roleMenuRelationDao.findRelationByMenu(menuId);
	}

	@Override
	public int checkRealtion(String roleId, String menuId) {
		return roleMenuRelationDao.checkRealtion(roleId, menuId);
	}

}
