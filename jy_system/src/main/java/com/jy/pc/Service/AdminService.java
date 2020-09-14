package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.AdminEntity;


public interface AdminService {
	//登录
	public Boolean checkUser(String userName, String password);
	//管理员添加
	public AdminEntity save(AdminEntity adminEntity);
	//管理员修改
	public AdminEntity update(AdminEntity adminEntity);
	//管理员删除
	public void delete(String id);
	//管理员findById
	public AdminEntity findBId(String id);
	//管理员分页与模糊查询
	public Page<AdminEntity> findListByName(String loginName,Pageable pageable);
	
}
