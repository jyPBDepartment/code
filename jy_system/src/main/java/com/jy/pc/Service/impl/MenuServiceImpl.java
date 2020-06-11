package com.jy.pc.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.MenuDao;
import com.jy.pc.Entity.MenuEntity;
import com.jy.pc.Service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	
	public Page<MenuEntity> findListByName(String name,Pageable pageable){
		return menuDao.findListByName("%"+name+"%",pageable);
		
	}

}
