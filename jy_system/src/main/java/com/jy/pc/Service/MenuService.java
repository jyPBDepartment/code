package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.MenuEntity;

public interface MenuService {
	
	public Page<MenuEntity> findListByName(String name,Pageable pageable);

}
