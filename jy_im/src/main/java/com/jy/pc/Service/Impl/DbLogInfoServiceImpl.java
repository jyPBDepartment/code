package com.jy.pc.Service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.DbLogInfoDao;
import com.jy.pc.Entity.DbLogInfoEntity;
import com.jy.pc.Service.DbLogInfoService;
import com.jy.pc.Utils.DbLogUtil;
@Service
public class DbLogInfoServiceImpl implements DbLogInfoService {
	@Autowired
	DbLogInfoDao dbLogInfoDao;
	@Autowired
	DbLogUtil logger;

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
	
	@Transactional
	@Override
	public void deleteAll() {
		 //底层源码实现方式为先查询所有数据 再进行遍历逐一删除 出于性能考虑使用sql删除，同理不建议使用deleteAll()
		dbLogInfoDao.onceDeleteAll();
		logger.initCustomizedLog("日志管理","清空日志", "清空日志默认不进行备份操作");
	}

	@Override
	public DbLogInfoEntity findId(String id) {
		return dbLogInfoDao.findId(id);
	}

}
