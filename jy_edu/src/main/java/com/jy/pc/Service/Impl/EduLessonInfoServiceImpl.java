package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.EduLessonInfoDao;
import com.jy.pc.DAO.EduLessonRelaDao;
import com.jy.pc.DAO.SysLocalUserDao;
import com.jy.pc.Entity.EduLessonInfoEntity;
import com.jy.pc.Entity.EduLessonStudentRelationEntity;
import com.jy.pc.Entity.SysLocalUserEntity;
import com.jy.pc.Service.EduLessonInfoService;
import com.jy.pc.Utils.DbLogUtil;

@Service
public class EduLessonInfoServiceImpl implements EduLessonInfoService {
	@Autowired
	EduLessonInfoDao eduLessonInfoDao;
	@Autowired
	private DbLogUtil logger;
	@Autowired
	private EduLessonRelaDao eduLessonRelaDao;
	@Autowired
	private SysLocalUserDao userDao;

	@Override
	public Page<EduLessonInfoEntity> findListByParam(String name, String status, String createBy, Pageable pageable) {
		String nameParam = "%" + name + "%";
		String createParam = "%" + createBy + "%";
		return eduLessonInfoDao.findListByName(nameParam, status, createParam, pageable);
	}

	@Override
	public EduLessonInfoEntity save(EduLessonInfoEntity entity) {
		eduLessonInfoDao.save(entity);
		logger.initAddLog(entity);
		return entity;
	}

	@Override
	public void update(EduLessonInfoEntity entity) {
		eduLessonInfoDao.save(entity);
		logger.initUpdateLog(entity);

	}

	@Override
	public void delete(String id) {
		EduLessonInfoEntity entity = eduLessonInfoDao.GetById(id);
		logger.initDeleteLog(entity);
		eduLessonInfoDao.deleteById(id);
	}

	@Override
	public EduLessonInfoEntity findInfobyId(String id) {
		return eduLessonInfoDao.GetById(id);
	}

	@Override
	public List<EduLessonStudentRelationEntity> findRelaById(String lessonId, String name) {
		return eduLessonRelaDao.findRelaById(lessonId, "%" + name + "%");
	}

	@Override
	public void enable(EduLessonInfoEntity entity, boolean result) {
		logger.initEnableLog(entity, result);
		eduLessonInfoDao.saveAndFlush(entity);
	}

	// 移动端 - 首页 - 热门课程加载（根据阅读量倒序排列）
	@Override
	public List<EduLessonInfoEntity> getListByReading() {
		return eduLessonInfoDao.getListByReading();
	}

	@Override
	public List<EduLessonStudentRelationEntity> getLessonsByUserId(String userId) {
		return eduLessonRelaDao.getLessonsByUserId(userId);
	}

	@Override
	public void enrollLesson(EduLessonInfoEntity lesson, String userId) {
		SysLocalUserEntity user = userDao.findId(userId);
		EduLessonStudentRelationEntity rela = new EduLessonStudentRelationEntity();
		rela.setLesson(lesson);
		rela.setUser(user);
		eduLessonRelaDao.saveAndFlush(rela);
	}

}
