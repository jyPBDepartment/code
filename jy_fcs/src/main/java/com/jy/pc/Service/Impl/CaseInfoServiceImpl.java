package com.jy.pc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.CaseInfoDao;
import com.jy.pc.Entity.CaseInfoEntity;
import com.jy.pc.Service.CaseInfoService;

@Service
public class CaseInfoServiceImpl implements CaseInfoService{

	@Autowired
	public CaseInfoDao caseInfoDao;
	@Override
	public CaseInfoEntity save(CaseInfoEntity caseInfoEntity) {
		
		return caseInfoDao.saveAndFlush(caseInfoEntity);
	}

	@Override
	public CaseInfoEntity findBId(String id) {
	
		return caseInfoDao.findBId(id);
	}

	@Override
	public Page<CaseInfoEntity> findListByName(String name, String auditStatus, Pageable pageable) {
		String caseName = "%"+name+"%";
		String status = "%"+auditStatus+"%";
		return caseInfoDao.findListByName(caseName, status, pageable);
	}

	@Override
	public CaseInfoEntity update(CaseInfoEntity caseInfoEntity) {
		
		return caseInfoDao.saveAndFlush(caseInfoEntity);
	}

	@Override
	public void delete(String id) {
		caseInfoDao.deleteById(id);
		
	}



}
