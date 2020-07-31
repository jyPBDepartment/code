package com.jy.pc.Service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.MenuDao;
import com.jy.pc.Entity.MenuEntity;
import com.jy.pc.Service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{
	@Autowired
	MenuDao menuDao;
	@Override
	public List<MenuEntity> findListByName(String name) {
		String nameParam = "%"+name+"%";
		return menuDao.findListByName(nameParam);
	}

	@Override
	public MenuEntity save(MenuEntity menuEntity) {
		return menuDao.saveAndFlush(menuEntity);
	}

	@Override
	public void update(MenuEntity menuEntity) {
		menuDao.saveAndFlush(menuEntity);		
	}

	@Override
	public void delete(String id) {
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
