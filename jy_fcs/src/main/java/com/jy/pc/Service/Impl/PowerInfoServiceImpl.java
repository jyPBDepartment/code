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
public class PowerInfoServiceImpl implements PowerInfoService{
@Autowired
private PowerInfoDao powerInfoDao;
	
	@Override
	public PowerInfoEntity save(PowerInfoEntity powerInfoEntity) {
		
		return powerInfoDao.saveAndFlush(powerInfoEntity);
	}

	@Override
	public PowerInfoEntity update(PowerInfoEntity powerInfoEntity) {
	
		return powerInfoDao.saveAndFlush(powerInfoEntity);
	}

	@Override
	public void delete(String id) {
		powerInfoDao.deleteById(id);
		
	}

	@Override
	public PowerInfoEntity findBId(String id) {
		
		return powerInfoDao.findBId(id);
	}

	@Override
	public Page<PowerInfoEntity> findListByName(String jurName, String jurCode, Pageable pageable) {
		String name = "%"+jurName+"%";
		String code = "%"+jurCode+"%";
		return powerInfoDao.findListByName(name, code, pageable);
	}

	@Override
	public List<PowerInfoEntity> findSubPowerList() {
		
		return powerInfoDao.findSubPowerList();
	}

	@Override
	public List<PowerInfoEntity> findAll() {
		
		return powerInfoDao.findAll();
	}

	@Override
	public List<PowerInfoEntity> findCount() {
	
		return powerInfoDao.findCount();
	}

}
