package com.jy.pc.Service.Impl;

import javax.transaction.Transactional;

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

	public void save(EduUserExamEntity eduUserExamEntity) {
		eduUserExamDao.saveAndFlush(eduUserExamEntity);
	}
}
