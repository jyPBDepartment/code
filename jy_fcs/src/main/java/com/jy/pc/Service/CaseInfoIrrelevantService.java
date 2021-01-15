package com.jy.pc.Service;

import com.jy.pc.Entity.CaseInfoIrrelevantEntity;

//看图识病与我无关表Service
public interface CaseInfoIrrelevantService {
	// 添加
	public CaseInfoIrrelevantEntity save(CaseInfoIrrelevantEntity caseInfoIrrelevant);
	// 删除
	public void delete(String id);
	
	// 根据用户id 看图识病id查询
	public CaseInfoIrrelevantEntity findCaseUserId(String caseId, String irrelevantnUserId);
}
