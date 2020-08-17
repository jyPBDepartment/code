package com.jy.pc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.DbLogInfoDao;
import com.jy.pc.Entity.DbLogInfoEntity;
import com.jy.pc.Service.DbLogInfoService;
@Service
public class DbLogInfoServiceImpl implements DbLogInfoService {
	@Autowired
	DbLogInfoDao dbLogInfoDao;

	@Override
	public Page<DbLogInfoEntity> findListByContent(String module, String action, Pageable pageable) {
		String contentParam = "%" + module + "%";
		String userParam = "%" + action + "%";
		return dbLogInfoDao.findListByContent(contentParam, userParam, pageable);
	}

	@Override
	public DbLogInfoEntity save(DbLogInfoEntity dbLogInfoEntity) {
		return dbLogInfoDao.saveAndFlush(dbLogInfoEntity);
	}

	@Override
	public void update(DbLogInfoEntity dbLogInfoEntity) {
		dbLogInfoDao.saveAndFlush(dbLogInfoEntity);

	}

	@Override
	public void delete(String id) {
		dbLogInfoDao.deleteById(id);
	}

	@Override
	public DbLogInfoEntity findId(String id) {
		return dbLogInfoDao.findId(id);
	}

}
