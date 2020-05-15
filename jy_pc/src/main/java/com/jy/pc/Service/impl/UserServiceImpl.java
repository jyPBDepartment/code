package com.jy.pc.Service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.UserDao;
import com.jy.pc.Entity.RespPageEntity;
import com.jy.pc.Entity.UserEntity;
import com.jy.pc.Service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	//验证用户登录
	public Boolean checkUser(String userName,String password) {
		
		UserEntity userInfo = userDao.findUserInfo("zgh");
		
		if(userInfo!=null) {
			return true;
		}else {
			return false;
		}
		 
	}
	
	//新增用户
	public void save(UserEntity userEntity) {
		
		userDao.saveAndFlush(userEntity);
	}

	//删除用户
	public void delete(UserEntity userEntity) {
		userDao.delete(userEntity);
	}
	
	public RespPageEntity getAllUserByPage(Integer page, Integer size) {
		
		RespPageEntity pageEntity = new RespPageEntity();
//	    // 默认从0开始
//	    if (page != null && size != null) {
//	        page = (page-1)*size;
//	    }
//	    // 获取当前也用户信息
//	    List<UserEntity> users =  userDao.findByLastname(page, size);
//	    pageEntity.setData(users);
//	    // 获取当前用户总量
//	    Long total = userDao.getTotal();
//	    pageEntity.setTotal(total);
	    return pageEntity;

	}
}
