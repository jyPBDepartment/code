package com.jy.pc.Service;

import java.sql.Date;
import java.util.List;


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
	public OrganEntity findBId(String id);
	public List<OrganEntity> findListByName(String name,String superId);
	
	
}
