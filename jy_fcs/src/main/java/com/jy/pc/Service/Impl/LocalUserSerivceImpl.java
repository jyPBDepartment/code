package com.jy.pc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.LocalUserDao;
import com.jy.pc.Entity.LocalUserEntity;
import com.jy.pc.Service.LocalUserService;

@Service
public class LocalUserSerivceImpl implements LocalUserService{
	@Autowired
	private LocalUserDao localUserDao;
	@Override
	public LocalUserEntity findId(String id) {
		return localUserDao.findId(id);
	}

}
