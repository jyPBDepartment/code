package com.jy.pc.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.QuestionDao;
import com.jy.pc.Entity.QuestionEntity;
import com.jy.pc.Service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{
	@Autowired
	private QuestionDao questionDao;
	@Override
	public void save(QuestionEntity questionEntity) {
		questionDao.save(questionEntity);
		
	}

	@Override
	public void update(QuestionEntity questionEntity) {
		questionDao.saveAndFlush(questionEntity);
		
	}

	@Override
	public void delete(String id) {
		questionDao.deleteById(id);
		
	}

	@Override
	public QuestionEntity findBId(String id) {
		
		return questionDao.findBId(id);
	}

	@Override
	public Page<QuestionEntity> findListByName(String name, String phoneNum, Pageable pageable) {
		String questionName = "%"+name+"%";
		String questionTel = "%"+phoneNum+"%";
		return questionDao.findListByName(questionName, questionTel, pageable);
	}

}
