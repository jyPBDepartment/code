package com.jy.pc.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.ExplanationDao;
import com.jy.pc.Entity.ExplanationEntity;
import com.jy.pc.Service.ExplanstionService;
@Service
public class ExplanationServiceImpl implements ExplanstionService{
	@Autowired
	private ExplanationDao explanationDao;

	@Override
	public ExplanationEntity save(ExplanationEntity explanation) {
		return explanationDao.saveAndFlush(explanation);
	}

	@Override
	public Page<ExplanationEntity> findListByName(String name, String phoneNum, Pageable pageable) {
		String appName = "%"+name+"%";
		String appPhoneNum = "%"+phoneNum+"%";

		return explanationDao.findListByName(appName, appPhoneNum, pageable);
	}

}
