package com.jy.pc.Service;

import com.jy.pc.Entity.RespPageEntity;
import com.jy.pc.Entity.UserEntity;

public interface UserService{
	
	public Boolean checkUser(String userName,String password);
	
	
	void save(UserEntity userEntity);
	
	void delete(UserEntity userEntity);

	RespPageEntity getAllUserByPage(Integer page, Integer size);
}
