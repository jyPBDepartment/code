package com.jy.pc.Service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.UserDao;
import com.jy.pc.Entity.UserEntity;
import com.jy.pc.Service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	// 验证用户登录
	public Boolean checkUser(String userName, String password) {

		UserEntity userInfo = userDao.findUserInfo(userName);

		if (userInfo != null) {
			return true;
		} else {
			return false;
		}

	}
	
}
