package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.PowerInfoDao;
import com.jy.pc.Entity.PowerInfoEntity;
import com.jy.pc.Service.PowerInfoService;

@Service
public class PowerInfoServiceImpl implements PowerInfoService {
	@Autowired
	private PowerInfoDao powerInfoDao;

	// 权限添加
	@Override
	public PowerInfoEntity save(PowerInfoEntity powerInfoEntity) {

		return powerInfoDao.saveAndFlush(powerInfoEntity);
	}

	// 权限修改
	@Override
	public PowerInfoEntity update(PowerInfoEntity powerInfoEntity) {

		return powerInfoDao.saveAndFlush(powerInfoEntity);
	}

	// 权限删除
	@Override
	public void delete(String id) {
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
}
