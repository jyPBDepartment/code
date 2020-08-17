package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.CaseInfoEntity;

public interface CaseInfoService {

	// 添加
	public CaseInfoEntity save(CaseInfoEntity caseInfoEntity);
	
	//添加(级联添加关键词)
	public CaseInfoEntity saveWithKeyword(CaseInfoEntity caseInfoEntity,String keywords);
	
	//修改(级联修改关键词)
	public CaseInfoEntity updateWithKeyword(CaseInfoEntity caseInfoEntity,String keywords);

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

	// 关键字搜索病虫害信息
	public List<CaseInfoEntity> findCaseInfoByKey(String name);
}
