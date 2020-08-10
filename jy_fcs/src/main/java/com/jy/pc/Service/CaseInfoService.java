package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.CaseInfoEntity;

public interface CaseInfoService {

	// 添加
	public CaseInfoEntity save(CaseInfoEntity caseInfoEntity);

	// findById
	public CaseInfoEntity findBId(String id);

	// 分页与模糊查询
	public Page<CaseInfoEntity> findListByName(String name, String auditStatus, Pageable pageable);

	// 修改
	public CaseInfoEntity update(CaseInfoEntity caseInfoEntity);

	// 删除
	public void delete(String id);
	
	//查询所有病虫害信息的最新3条记录
	public List<CaseInfoEntity> findCaseInfo();

}
