package com.jy.pc.Service.Impl;

import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.AccountRoleInfoDao;
import com.jy.pc.Entity.AccountRoleInfoEntity;
import com.jy.pc.Service.AccountRoleInfoService;
import com.jy.pc.Utils.DbLogUtil;

@Service
@Transactional
public class AccountRoleInfoServiceImpl implements AccountRoleInfoService {

	@Autowired
	DbLogUtil logger;

	@Autowired
	AccountRoleInfoDao accountRoleInfoDao;

	public void save(AccountRoleInfoEntity accountRoleInfoEntity) throws ServiceException{

		accountRoleInfoDao.saveAndFlush(accountRoleInfoEntity);
	}
	
	public AccountRoleInfoEntity findRoleIdByAccountId(String accountId) {
		
		return accountRoleInfoDao.findRoleIdByAccountId(accountId);
	}

}
