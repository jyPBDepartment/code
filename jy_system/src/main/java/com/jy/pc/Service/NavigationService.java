package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.NavigationEntity;


public interface NavigationService {
	//导航添加
	public void save(NavigationEntity navigationEntity);
	//导航修改
	public void update(NavigationEntity navigationEntity);
	//导航删除
	public void delete(String id);
	//导航findById
	public NavigationEntity findBId(String id);
	//导航分页与模糊查询
	public Page<NavigationEntity> findListByName(String name,Pageable pageable);
	//下拉列表显示
	public List<NavigationEntity> findAll();
}
