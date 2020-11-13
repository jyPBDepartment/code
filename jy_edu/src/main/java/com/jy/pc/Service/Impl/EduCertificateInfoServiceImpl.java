package com.jy.pc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.EduCertificateInfoDao;
import com.jy.pc.Entity.EduCertificateInfoEntity;
import com.jy.pc.Service.EduCertificateInfoService;
import com.jy.pc.Utils.DbLogUtil;

@Service
public class EduCertificateInfoServiceImpl implements EduCertificateInfoService {
	@Autowired
	EduCertificateInfoDao eduCertificateInfoDao;
	@Autowired
	private DbLogUtil logger;

	// 分页查询
	@Override
	public Page<EduCertificateInfoEntity> findListByParam(String name, String status, String createBy,
			Pageable pageable) {
		String nameParam = "%" + name + "%";
		String createParam = "%" + createBy + "%";
		return eduCertificateInfoDao.findListByName(nameParam, status, createParam, pageable);
	}

	// 新增保存
	@Override
	public EduCertificateInfoEntity save(EduCertificateInfoEntity entity) {
		eduCertificateInfoDao.save(entity);
		logger.initAddLog(entity);
		return entity;
	}

	// 修改实体
	@Override
	public void update(EduCertificateInfoEntity entity) {
		eduCertificateInfoDao.save(entity);
		logger.initUpdateLog(entity);
	}

	// 删除实体
	@Override
	public void delete(String id) {
		EduCertificateInfoEntity entity = eduCertificateInfoDao.GetById(id);
		eduCertificateInfoDao.delete(entity);
		logger.initDeleteLog(entity);
	}

	// 根据主键查询实体
	@Override
	public EduCertificateInfoEntity findInfobyId(String id) {
		return eduCertificateInfoDao.GetById(id);
	}

	// 切换状态
	@Override
	public void enable(EduCertificateInfoEntity entity, boolean result) {
		logger.initEnableLog(entity, result);
		eduCertificateInfoDao.save(entity);

	}

	@Override
	public EduCertificateInfoEntity findInfobyVocation(String vocationId) {
		return eduCertificateInfoDao.findInfobyVocation(vocationId);
		
	}

}
