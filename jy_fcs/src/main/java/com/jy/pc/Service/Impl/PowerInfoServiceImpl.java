package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.PowerInfoDao;
import com.jy.pc.Entity.PowerInfoEntity;
import com.jy.pc.Service.PowerInfoService;
import com.jy.pc.Utils.DbLogUtil;

@Service
public class PowerInfoServiceImpl implements PowerInfoService {
	@Autowired
	private PowerInfoDao powerInfoDao;
	@Autowired
	DbLogUtil logger;

	// 权限添加
	@Override
	@Transactional
	public PowerInfoEntity save(PowerInfoEntity powerInfoEntity) {
		PowerInfoEntity result = powerInfoDao.saveAndFlush(powerInfoEntity);
		logger.initAddLog(powerInfoEntity);
		return result;
	}

	// 权限启用禁用
	@Override
	@Transactional
	public PowerInfoEntity enable(PowerInfoEntity powerInfoEntity,boolean result) {
		logger.initEnableLog(powerInfoEntity,result);
		return powerInfoDao.saveAndFlush(powerInfoEntity);
	}

	// 权限修改
	@Override
	@Transactional
	public PowerInfoEntity update(PowerInfoEntity powerInfoEntity) {
		logger.initUpdateLog(powerInfoEntity);
		return powerInfoDao.saveAndFlush(powerInfoEntity);
	}

	// 权限删除
	@Override
	@Transactional
	public void delete(String id) {
		logger.initDeleteLog(powerInfoDao.findBId(id));
		powerInfoDao.deleteById(id);

	}

	// 权限查找
	@Override
	public PowerInfoEntity findBId(String id) {

		return powerInfoDao.findBId(id);
	}

	// 权限分页与模糊查询
	@Override
	public Page<PowerInfoEntity> findListByName(String jurName, String jurCode, Pageable pageable) {
		String name = "%" + jurName + "%";
		String code = "%" + jurCode + "%";
		return powerInfoDao.findListByName(name, code, pageable);
	}

	// 权限上级权限编码分类
	@Override
	public List<PowerInfoEntity> findSubPowerList() {

		return powerInfoDao.findSubPowerList();
	}

	// 权限查询所有
	@Override
	public List<PowerInfoEntity> findAll() {

		return powerInfoDao.findAll();
	}

	// 权限关联账户
	@Override
	public List<PowerInfoEntity> findCount() {

		return powerInfoDao.findCount();
	}

	// 删除前验证
	@Override
	public List<PowerInfoEntity> findAccountLink() {

		return powerInfoDao.findAccountLink();
	}

	@Override
	public boolean findJurCode(String subJurCode) {
		int count = powerInfoDao.findJurCode(subJurCode);
		return count > 0 ? true : false;
	}

	@Override
	public List<PowerInfoEntity> findListById(String id) {

		return powerInfoDao.findListById(id);
	}

}
