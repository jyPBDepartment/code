package com.jy.pc.Service.Impl;

import java.util.List;

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
		String nameParam = "%" + name + "%";
		return eduIssueDao.findListByName(nameParam, status, createBy, pageable);
	}

	@Override
	public EduIssueInfoEntity findInfobyId(String id) {
		return eduIssueDao.GetById(id);
	}

	@Override
	public Page<EduIssueInfoEntity> findMgtByParam(String userName, String card, String vocationId, Pageable pageable) {
		String nameParam = "%" + userName + "%";
		return eduIssueDao.findMgtByParam(nameParam, card, vocationId, pageable);
	}

	@Override
	public void save(EduIssueInfoEntity entity) {
		eduIssueDao.saveAndFlush(entity);

	}

	@Override
	public List<EduIssueInfoEntity> findInfoByVocation(String vocationId, String status) {
		return eduIssueDao.findInfoByVocation(vocationId, status);
	}

}
