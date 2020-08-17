package com.jy.pc.Service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.AccountInfoDao;
import com.jy.pc.DAO.CommentReplyInfoDao;
import com.jy.pc.Entity.AccountInfoEntity;
import com.jy.pc.Service.AccountInfoService;
import com.jy.pc.Utils.DbLogUtil;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {
	@Autowired
	AccountInfoDao accountInfoDao;
	@Autowired
	CommentReplyInfoDao commentReplyInfoDao;
	@Autowired
	DbLogUtil logger;

	// 登录
	@Override
	public Boolean checkUser(String name, String password) {
		AccountInfoEntity accountInfoEntity = accountInfoDao.findUserInfo(name, password);

		if (accountInfoEntity != null) {
			return true;
		} else {
			return false;
		}

	}

	// 搜索
	@Override
	public Page<AccountInfoEntity> findListByName(String name, String phone, String auditStatus, Pageable pageable) {
		String userName = "%" + name + "%";
		String telPhone = "%" + phone + "%";
		return accountInfoDao.findListByName(userName, telPhone, auditStatus, pageable);
	}

	// 新增
	@Transactional
	@Override
	public AccountInfoEntity save(AccountInfoEntity accountInfo) {
		AccountInfoEntity result = accountInfoDao.saveAndFlush(accountInfo);
		logger.initAddLog(accountInfo.getId());
		return result;
	}

	// 切换状态
	@Override
	@Transactional
	public void enable(AccountInfoEntity accountInfo, boolean result) {
		logger.initEnableLog(accountInfo, result);
		accountInfoDao.saveAndFlush(accountInfo);
	}

	// 修改密码
	@Override
	@Transactional
	public void updatePwd(AccountInfoEntity accountInfo) {
		logger.initCustomizedLog("账户管理", "修改密码", accountInfo.getPassWord());
		accountInfoDao.saveAndFlush(accountInfo);
	}

	// 修改
	@Override
	@Transactional
	public void update(AccountInfoEntity accountInfo) {
		logger.initUpdateLog(accountInfo);
		accountInfoDao.saveAndFlush(accountInfo);
	}

	// 删除
	@Override
	@Transactional
	public void delete(String id) {
		logger.initDeleteLog(accountInfoDao.findId(id));
		accountInfoDao.deleteById(id);
	}

	// findbyid
	@Override
	public AccountInfoEntity findId(String id) {
		return accountInfoDao.findId(id);
	}

	// 通过name，password查询数据
	@Override
	public AccountInfoEntity findUserInfo(String name, String password) {
		return accountInfoDao.findUserInfo(name, password);
	}
}
