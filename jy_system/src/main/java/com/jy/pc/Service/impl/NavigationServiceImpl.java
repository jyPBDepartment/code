package com.jy.pc.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.NavigationDao;
import com.jy.pc.Entity.NavigationEntity;
import com.jy.pc.Service.NavigationService;


@Service
public class NavigationServiceImpl implements NavigationService{
	@Autowired
	private NavigationDao navigationDao;
	//导航添加
	@Override
	public void save(NavigationEntity navigationEntity) {
		
		navigationDao.save(navigationEntity);
	}
	//导航修改
	@Override
	public void update(NavigationEntity navigationEntity) {
		
		navigationDao.saveAndFlush(navigationEntity);
	}
	//导航删除
	@Override
	public void delete(String id) {
		
		navigationDao.deleteById(id);
	}
	//导航findById
	@Override
	public NavigationEntity findBId(String id) {
		
		return navigationDao.findBId(id);
	}
	//导航分页与模糊查询
	@Override
	public Page<NavigationEntity> findListByName(String name,Pageable pageable) {
		String navName = "%"+name+"%";
		return navigationDao.findListByName(navName, pageable);
	}
	public List<NavigationEntity> findAll() {
		// TODO Auto-generated method stub
		return navigationDao.findAll();
	}
	
}
