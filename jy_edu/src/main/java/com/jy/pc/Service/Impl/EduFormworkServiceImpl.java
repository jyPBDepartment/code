package com.jy.pc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.EduFormworkDao;
import com.jy.pc.Entity.EduFormworkEntity;
import com.jy.pc.Service.EduFormworkService;
import com.jy.pc.Utils.DbLogUtil;

@Service
public class EduFormworkServiceImpl implements EduFormworkService {
	@Autowired
	private DbLogUtil logger;
	@Autowired
	EduFormworkDao eduFormworkDao;

	// 分页查询
	@Override
	public Page<EduFormworkEntity> findListByParam(String name, String status, String createBy, Pageable pageable) {
		String nameParam = "%" + name + "%";
		String createParam = "%" + createBy + "%";
		return eduFormworkDao.findListByName(nameParam, status, createParam, pageable);
	}

	// 新增保存
	@Override
	public EduFormworkEntity save(EduFormworkEntity entity) {
		eduFormworkDao.save(entity);
		logger.initAddLog(entity);
		return entity;
	}

	// 修改实体
	@Override
	public void update(EduFormworkEntity entity) {
		eduFormworkDao.save(entity);
		logger.initUpdateLog(entity);
	}

	// 删除实体
	@Override
	public void delete(String id) {
		EduFormworkEntity entity = eduFormworkDao.GetById(id);
		eduFormworkDao.delete(entity);
		logger.initDeleteLog(entity);
	}

	// 根据主键查询实体
	@Override
	public EduFormworkEntity findInfobyId(String id) {
		return eduFormworkDao.GetById(id);
	}

	// 切换状态
	@Override
	public void enable(EduFormworkEntity entity, boolean result) {
		logger.initEnableLog(entity, result);
		eduFormworkDao.save(entity);
	}

}
