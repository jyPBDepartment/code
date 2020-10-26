package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.EduLessonInfoDao;
import com.jy.pc.Entity.EduLessonInfoEntity;
import com.jy.pc.Entity.EduLessonStudentRelationEntity;
import com.jy.pc.Service.EduLessonInfoService;
import com.jy.pc.Utils.DbLogUtil;

@Service
public class EduLessonInfoServiceImpl implements EduLessonInfoService {
	@Autowired
	EduLessonInfoDao eduLessonInfoDao;
	@Autowired
	private DbLogUtil logger;

	@Override
	public Page<EduLessonInfoEntity> findListByParam(String name, String status, Pageable pageable) {
		String nameParam = "%" + name + "%";
		return eduLessonInfoDao.findListByName(nameParam, status, pageable);
	}

	@Override
	public EduLessonInfoEntity save(EduLessonInfoEntity dbLogInfoEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(EduLessonInfoEntity moduleInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public EduLessonInfoEntity findInfobyId(String id) {
		return eduLessonInfoDao.GetById(id);
	}

	@Override
	public List<EduLessonStudentRelationEntity> findRelaById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enable(EduLessonInfoEntity entity, boolean result) {
		logger.initEnableLog(entity, result);
		eduLessonInfoDao.saveAndFlush(entity);
	}


}
