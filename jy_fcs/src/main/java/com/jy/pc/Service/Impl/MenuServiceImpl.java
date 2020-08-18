package com.jy.pc.Service.Impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.MenuDao;
import com.jy.pc.Entity.MenuEntity;
import com.jy.pc.Service.MenuService;
import com.jy.pc.Utils.DbLogUtil;

@Service
public class MenuServiceImpl implements MenuService{
	@Autowired
	MenuDao menuDao;
	@Autowired
	DbLogUtil logger;
	
	@Override
	public List<MenuEntity> findListByName(String name) {
		String nameParam = "%"+name+"%";
		return menuDao.findListByName(nameParam);
	}

	@Override
	@Transactional
	public MenuEntity save(MenuEntity menuEntity) {
		MenuEntity result = menuDao.saveAndFlush(menuEntity);
		logger.initAddLog(menuEntity);
		return result;
	}

	@Override
	@Transactional
	public void enable(MenuEntity menuEntity,boolean result) {
		logger.initEnableLog(menuEntity,result);
		menuDao.saveAndFlush(menuEntity);		
	}
	
	@Override
	@Transactional
	public void changeSort(MenuEntity menuEntity) {
		logger.initCustomizedLog("菜单管理", "修改排序", "修改之前："+menuDao.findId(menuEntity.getId()).getSort());
		menuDao.saveAndFlush(menuEntity);		
	}
	
	@Override
	@Transactional
	public void update(MenuEntity menuEntity) {
		logger.initUpdateLog(menuEntity);
		menuDao.saveAndFlush(menuEntity);		
	}

	@Override
	@Transactional
	public void delete(String id) {
		logger.initDeleteLog(menuDao.findId(id));
		menuDao.deleteById(id);		
	}

	@Override
	public MenuEntity findId(String id) {
		return menuDao.findId(id);
	}

	@Override
	public boolean hasSubMenu(String parentId) {
		int count = menuDao.findSubMenuCount(parentId);
		return count > 0 ? true : false;
	}

	@Override
	public List<Map<String,Object>> findTree() {
		return menuDao.findTree();
	}

}
