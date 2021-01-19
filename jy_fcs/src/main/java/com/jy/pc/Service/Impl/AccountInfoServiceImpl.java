package com.jy.pc.Service.Impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jy.pc.DAO.AccountInfoDao;
import com.jy.pc.Entity.AccountInfoEntity;
import com.jy.pc.Entity.AccountPowerInfoEntity;
import com.jy.pc.Service.AccountInfoService;
import com.jy.pc.Service.AccountPowerInfoService;
import com.jy.pc.Utils.Aes;
import com.jy.pc.Utils.DbLogUtil;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {
	@Autowired
	AccountInfoDao accountInfoDao;
	@Autowired
	DbLogUtil logger;
	@Autowired
	private AccountPowerInfoService accountPowerInfoService;

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
		logger.initAddLog(accountInfo);
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
		AccountInfoEntity entity = new AccountInfoEntity();
		entity.setName(accountInfo.getName());
		entity.setPassWord(accountInfo.getPassWord());
		logger.initCustomizedLog("账户管理", "修改密码", entity);
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

	@Override
	public Map<String, String> updateJur(String accountId,String addItem,String deleteItem) {
		Map<String, String> map = new HashMap<String, String>();
		Aes aes = new Aes();
		String ad = "";
		String de = "";
		try {
			//添加
			if (!addItem.isEmpty()) {
				ad = aes.desEncrypt(addItem);
				JSONArray jsonObject = JSONObject.parseArray(ad);
				Set set = new HashSet();
				for (int i = 0; i < jsonObject.size(); i++) {
					set.add(jsonObject.get(i));
				}
				for (int j = 0; j < jsonObject.size(); j++) {
					AccountPowerInfoEntity accountPowerInfoEntity = new AccountPowerInfoEntity();
					accountPowerInfoEntity.setAccountId(accountId);
					accountPowerInfoEntity.setJurCodel(jsonObject.get(j).toString());
					accountPowerInfoService.update(accountPowerInfoEntity);
				}

			}
			//删除
			if (!deleteItem.isEmpty()) {
				de = aes.desEncrypt(deleteItem);
				JSONArray jsonObject = JSONObject.parseArray(de);
				Set set = new HashSet();
				for (int i = 0; i < jsonObject.size(); i++) {
					set.add(jsonObject.get(i));
				}
				for (int j = 0; j < jsonObject.size(); j++) {
					accountPowerInfoService.deleteByJurCode(jsonObject.get(j).toString(), accountId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("status", "0");
		map.put("message", "修改成功");
		return map;
	}

	@Override
	@Transactional
	public void resetPass(AccountInfoEntity accountInfo) {
		accountInfoDao.saveAndFlush(accountInfo);
	}
}
