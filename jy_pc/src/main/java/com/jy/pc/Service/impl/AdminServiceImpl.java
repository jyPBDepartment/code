package com.jy.pc.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.AdminDao;
import com.jy.pc.Entity.AdminEntity;
import com.jy.pc.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;
	@Override
	public void save(AdminEntity adminEntity) {
		adminDao.save(adminEntity);
	}

	@Override
	public void update(AdminEntity adminEntity) {
		adminDao.saveAndFlush(adminEntity);
	}

	@Override
	public void delete(String adminId) {
		adminDao.deleteById(adminId);
	}

	@Override
	public List<AdminEntity> findAll() {
		return adminDao.findAll();
	}

	@Override
	public AdminEntity findById(String adminId) {
		return adminDao.findBId(adminId);
	}
	
	
	
	public Boolean checkLogin(String loginName,String password) {
		
		AdminEntity adminEntity = adminDao.findByName(loginName);
		//判断是否存在用户
		if(adminEntity!=null) {
			//比较密码是否正确
			if(password.equals(adminEntity.getAdminPassword())) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	
	public List<AdminEntity> findListByName(String adminName,String adminPhone,String adminStatic){
		String name = "%"+adminName+"%";
		String phone = "%"+adminPhone+"%";
		return adminDao.findListByName(name,phone,adminStatic);
		
	}

}
