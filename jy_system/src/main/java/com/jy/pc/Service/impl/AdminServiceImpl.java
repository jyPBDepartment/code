package com.jy.pc.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.AdminDao;
import com.jy.pc.Entity.AdminEntity;
import com.jy.pc.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDao adminDao;
	//添加
	@Override
	public AdminEntity save(AdminEntity adminEntity) {
		
		return adminDao.saveAndFlush(adminEntity);
	}
	//修改
	@Override
	public AdminEntity update(AdminEntity adminEntity) {
		
		return adminDao.saveAndFlush(adminEntity);
	}
	//删除
	@Override
	public void delete(String id) {
		
		adminDao.deleteById(id);
	}
	//根据id查询
	@Override
	public AdminEntity findBId(String id) {
	
		return adminDao.findBId(id);
	}
	//分页和模糊查询
	@Override
	public Page<AdminEntity> findListByName(String loginName, Pageable pageable) {
		
		String adminName = "%"+loginName+"%";
		return adminDao.findListByName(adminName, pageable);
	}
	

}
