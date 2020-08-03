package com.jy.pc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.CaseInfoDao;
import com.jy.pc.Entity.CaseInfoEntity;
import com.jy.pc.Service.CaseInfoService;

@Service
public class CaseInfoServiceImpl implements CaseInfoService {

	@Autowired
	public CaseInfoDao caseInfoDao;

	// 看图识病添加
	@Override
	public CaseInfoEntity save(CaseInfoEntity caseInfoEntity) {

		return caseInfoDao.saveAndFlush(caseInfoEntity);
	}

	// 看图识病查找
	@Override
	public CaseInfoEntity findBId(String id) {

		return caseInfoDao.findBId(id);
	}

	// 看图识病分页与模糊查询
	@Override
	public Page<CaseInfoEntity> findListByName(String name, String auditStatus, Pageable pageable) {
		String caseName = "%" + name + "%";
		String status = "%" + auditStatus + "%";
		return caseInfoDao.findListByName(caseName, status, pageable);
	}

	// 看图识病修改
	@Override
	public CaseInfoEntity update(CaseInfoEntity caseInfoEntity) {

		return caseInfoDao.saveAndFlush(caseInfoEntity);
	}

	// 看图识病删除
	@Override
	public void delete(String id) {
		caseInfoDao.deleteById(id);

	}

}
