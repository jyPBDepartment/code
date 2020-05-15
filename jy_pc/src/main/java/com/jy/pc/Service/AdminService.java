package com.jy.pc.Service;

import java.util.List;

import com.jy.pc.Entity.AdminEntity;

public interface AdminService {
	
	/**
	 * 公共/常用的方法
	 * 
	 * */
	public void save(AdminEntity adminEntity);
	public void update(AdminEntity adminEntity);
	public void delete(String adminId);
	public List<AdminEntity> findAll();
	public AdminEntity findById(String adminId);
	
	
	
	/**
	 * 实际业务产生的方法
	 * 
	 * */
	//验证用户登录信息
	public Boolean checkLogin(String loginName,String password);
	
	
	public List<AdminEntity> findListByName(String adminName,String adminPhone,String adminStatic);
}
