package com.jy.pc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.CaseInfoIrrelevantDAO;
import com.jy.pc.Entity.CaseInfoIrrelevantEntity;
import com.jy.pc.Service.CaseInfoIrrelevantService;

//看图识病与我无关表ServiceImpl
@Service
public class CaseInfoIrrelevantServiceImpl implements CaseInfoIrrelevantService{
	@Autowired
	private CaseInfoIrrelevantDAO caseInfoIrrelevantDAO;

	//新增
	@Override
	public CaseInfoIrrelevantEntity save(CaseInfoIrrelevantEntity caseInfoIrrelevant) {
		return caseInfoIrrelevantDAO.saveAndFlush(caseInfoIrrelevant);
	}

	//删除
	@Override
	public void delete(String id) {
		caseInfoIrrelevantDAO.deleteById(id);
	}
	
	// 根据用户id 看图识病id查询
	@Override
	public CaseInfoIrrelevantEntity findCaseUserId(String caseId, String irrelevantnUserId) {
		return caseInfoIrrelevantDAO.findCaseUserId(caseId, irrelevantnUserId);
	}

}
