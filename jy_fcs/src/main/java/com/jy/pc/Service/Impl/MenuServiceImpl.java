package com.jy.pc.Service.Impl;

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
	public Page<MenuEntity> findListByName(String name, Pageable pageable) {
		String nameParam = "%"+name+"%";
		return menuDao.findListByName(nameParam, pageable);
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

}
