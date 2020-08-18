package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.AccountPowerInfoDao;
import com.jy.pc.Entity.AccountPowerInfoEntity;
import com.jy.pc.Service.AccountPowerInfoService;
import com.jy.pc.Utils.DbLogUtil;

@Service
@Transactional
public class AccountPowerInfoServiceImpl implements AccountPowerInfoService {
	@Autowired
	private AccountPowerInfoDao accountPowerInfoDao;
	@Autowired
	DbLogUtil logger;
	
	@Override
	public AccountPowerInfoEntity save(AccountPowerInfoEntity accountPowerInfo) {
		AccountPowerInfoEntity result = accountPowerInfoDao.saveAndFlush(accountPowerInfo);
		logger.initAddLog(accountPowerInfo);
		return result;
	}

	@Override
	@Transactional
	public void update(AccountPowerInfoEntity accountPowerInfo) {
		logger.initCustomizedLog("账号管理", "权限设置 -- 新增", accountPowerInfo);
		accountPowerInfoDao.saveAndFlush(accountPowerInfo);

	}

	@Override
	@Transactional
	public void delete(String id) {
		accountPowerInfoDao.deleteById(id);
	}

	@Override
	public void deleteByJurCode(String jurCodel, String accountId) {
		AccountPowerInfoEntity entity = new AccountPowerInfoEntity();
		entity.setAccountId(accountId);
		entity.setJurCodel(jurCodel);
		logger.initCustomizedLog("账号管理", "权限设置 -- 删除", entity);
		accountPowerInfoDao.deleteByJurCode(jurCodel, accountId);
	}

	@Override
	public List<AccountPowerInfoEntity> findAccountId(String accountId) {
		return accountPowerInfoDao.findAccountId(accountId);
	}

	@Override
	public List<AccountPowerInfoEntity> findId(String accountId) {
		return accountPowerInfoDao.findId(accountId);
	}
}
