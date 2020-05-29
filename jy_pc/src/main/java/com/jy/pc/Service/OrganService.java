package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.OrganEntity;

public interface OrganService {
	/**
	 * 公共/常用的方法
	 * 
	 * */
	public void save(OrganEntity OrganEntity);
	public void update(OrganEntity OrganEntity);
	public void delete(String id);
	public List<OrganEntity> findAll();
	/**
	 * finById,模糊查询方法
	 * 
	 * */
	public OrganEntity findBId(String id);
	public Page<OrganEntity> findListByName(String name,String superId,Pageable pageable);
}
