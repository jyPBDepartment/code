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

	@Override
	public Boolean checkUser(String name, String password) {
		AccountInfoEntity accountInfoEntity = accountInfoDao.findUserInfo(name,password);

		if (accountInfoEntity != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Page<AccountInfoEntity> findListByName(String name,String phone,String auditStatus, Pageable pageable) {
		String userName = "%"+name+"%";
		String telPhone = "%"+phone+"%";
		return accountInfoDao.findListByName(userName,telPhone,auditStatus, pageable);
	}

	@Override
	public AccountInfoEntity save(AccountInfoEntity accountInfo) {
		return accountInfoDao.saveAndFlush(accountInfo);
	}

	@Override
	public void update(AccountInfoEntity accountInfo) {
		accountInfoDao.saveAndFlush(accountInfo);
	}

	@Override
	public void delete(String id) {
		accountInfoDao.deleteById(id);
	}

	@Override
	public AccountInfoEntity findId(String id) {
		return accountInfoDao.findId(id);
	}
}
