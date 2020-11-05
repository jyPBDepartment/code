package com.jy.pc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.EduIssueDao;
import com.jy.pc.Entity.EduIssueInfoEntity;
import com.jy.pc.Service.EduIssueService;

@Service
public class EduIssueServiceImpl implements EduIssueService {
	@Autowired
	EduIssueDao eduIssueDao;

	@Override
	public Page<EduIssueInfoEntity> findListByParam(String name, String status, String createBy, Pageable pageable) {
		String createParam = "%" + createBy + "%";
		return eduIssueDao.findListByName(name, status, createParam, pageable);
	}

	@Override
	public EduIssueInfoEntity findInfobyId(String id) {
		return eduIssueDao.GetById(id);
	}

	@Override
	public Page<EduIssueInfoEntity> findMgtByParam(String userName, String card, String vocationId, Pageable pageable) {
		return eduIssueDao.findMgtByParam(userName, card, vocationId, pageable);
	}

}
