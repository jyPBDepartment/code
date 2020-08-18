package com.jy.pc.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.jy.pc.DAO.RoleMenuRelationDao;
import com.jy.pc.Entity.MenuEntity;
import com.jy.pc.Entity.RoleEntity;
import com.jy.pc.Entity.RoleMenuRelationEntity;
import com.jy.pc.Service.RoleMenuRelationService;
import com.jy.pc.Utils.DbLogUtil;

@Service
public class RoleMenuRelationImpl implements RoleMenuRelationService {

	@Autowired
	RoleMenuRelationDao roleMenuRelationDao;
	@Autowired
	DbLogUtil logger;

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

	@Override
	public boolean hasRelationByMenu(String menuId) {
		return roleMenuRelationDao.hasRelationByMenu(menuId) > 0 ? true : false;
	}

	@Override
	public boolean hasRelationByRole(String roleId) {
		return roleMenuRelationDao.hasRelationByRole(roleId) > 0 ? true : false;
	}

	@Transactional
	@Override
	public void batchSave(String roleId, String idArr) {
		JSONArray array = JSONArray.parseArray(idArr);
		List<RoleMenuRelationEntity> entities = new ArrayList<RoleMenuRelationEntity>();
		for (int i = 0; i < array.size(); i++) {
			RoleMenuRelationEntity entity = new RoleMenuRelationEntity();
			entity.setRole(new RoleEntity(roleId));
			entity.setMenu(new MenuEntity(array.getString(i)));
			entities.add(entity);
		}
		logger.initCustomizedLog("角色管理", "角色授权", entities);
		// 清空角色下原有权限
		roleMenuRelationDao.deleteByRole(roleId);
		// 重新挂载权限
		roleMenuRelationDao.saveAll(entities);
	}

	// 清空角色授权
	@Transactional
	public void deleteByRole(String roleId) {
		logger.initCustomizedLog("角色管理", "清空授权", "roleId:"+roleId);
		roleMenuRelationDao.deleteByRole(roleId);
	}
}
