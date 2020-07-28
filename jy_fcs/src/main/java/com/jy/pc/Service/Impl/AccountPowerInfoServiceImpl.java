package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.AccountPowerInfoDao;
import com.jy.pc.Entity.AccountPowerInfoEntity;
import com.jy.pc.Service.AccountPowerInfoService;

@Service

public class AccountPowerInfoServiceImpl implements AccountPowerInfoService {
	@Autowired
	private AccountPowerInfoDao accountPowerInfoDao;

	@Override
	public AccountPowerInfoEntity save(AccountPowerInfoEntity accountPowerInfo) {
		return accountPowerInfoDao.saveAndFlush(accountPowerInfo);
	}

	@Override
	public void update(AccountPowerInfoEntity accountPowerInfo) {
		accountPowerInfoDao.saveAndFlush(accountPowerInfo);

	}

	@Override
	public void delete(String id) {
		accountPowerInfoDao.deleteById(id);
	}

	@Override
	public List<AccountPowerInfoEntity> findAccountId(String accountId) {
		return accountPowerInfoDao.findAccountId(accountId);
	}

}
