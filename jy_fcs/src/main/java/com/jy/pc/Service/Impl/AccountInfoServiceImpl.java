package com.jy.pc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.AccountInfoDao;
import com.jy.pc.Entity.AccountInfoEntity;
import com.jy.pc.Service.AccountInfoService;

@Service
public class AccountInfoServiceImpl implements AccountInfoService{
	@Autowired
	AccountInfoDao accountInfoDao;

	//登录
	@Override
	public Boolean checkUser(String name, String password) {
		AccountInfoEntity accountInfoEntity = accountInfoDao.findUserInfo(name,password);

		if (accountInfoEntity != null) {
			return true;
		} else {
			return false;
		}

	}

	//搜索
	@Override
	public Page<AccountInfoEntity> findListByName(String name,String phone,String auditStatus, Pageable pageable) {
		String userName = "%"+name+"%";
		String telPhone = "%"+phone+"%";
		return accountInfoDao.findListByName(userName,telPhone,auditStatus, pageable);
	}

	//新增
	@Override
	public AccountInfoEntity save(AccountInfoEntity accountInfo) {
		return accountInfoDao.saveAndFlush(accountInfo);
	}

	//修改
	@Override
	public void update(AccountInfoEntity accountInfo) {
		accountInfoDao.saveAndFlush(accountInfo);
	}

	//删除
	@Override
	public void delete(String id) {
		accountInfoDao.deleteById(id);
	}

	//findbyid
	@Override
	public AccountInfoEntity findId(String id) {
		return accountInfoDao.findId(id);
	}

	//通过name，password查询数据
	@Override
	public AccountInfoEntity findUserInfo(String name, String password) {
		return accountInfoDao.findUserInfo(name, password);
	}
}
