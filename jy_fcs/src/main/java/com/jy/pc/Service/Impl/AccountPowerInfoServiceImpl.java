package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.AccountPowerInfoDao;
import com.jy.pc.Entity.AccountPowerInfoEntity;
import com.jy.pc.Service.AccountPowerInfoService;

@Service
@Transactional
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

	public void deleteByJurCode(String id) {
		accountPowerInfoDao.deleteByJurCode(id);
	}
	@Override
	public List<AccountPowerInfoEntity> findAccountId(String accountId) {
		return accountPowerInfoDao.findAccountId(accountId);
	}

	@Override
	public AccountPowerInfoEntity findId(String accountId, String jurCodel) {
		return accountPowerInfoDao.findId(accountId, jurCodel);
	}

//	@Override
//	public AccountPowerInfoEntity finddeleteId(String accountId, String jurCodel) {
//		return accountPowerInfoDao.finddeleteId(accountId, jurCodel);
//	}

}
