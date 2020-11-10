package com.jy.pc.Service.Impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.EduUserExamDao;
import com.jy.pc.Entity.EduUserExamEntity;
import com.jy.pc.Service.EduUserExamService;

@Service
@Transactional
public class EduUserExamServiceImpl implements EduUserExamService {

	@Autowired
	private EduUserExamDao eduUserExamDao;

	public void deleteByExam(String userId, String examId) {
		if (eduUserExamDao.findByExam(userId, examId) != null) {
			eduUserExamDao.deleteByExam(userId, examId);
		}
	}

	public void save(EduUserExamEntity eduUserExamEntity) throws ServiceException {
		eduUserExamDao.saveAndFlush(eduUserExamEntity);
	}

	public List<Map<String, Object>> getExamResultByUserId(String userId) throws ServiceException {
		return eduUserExamDao.getExamResultByUserId(userId);
	}
	
	public EduUserExamEntity isPass(String userId,String examId) {
		return eduUserExamDao.findByExam(userId,examId);
	}
}
