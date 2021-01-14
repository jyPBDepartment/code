package com.jy.pc.Service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.CaseInfoEntity;
// 看图识病 Service
public interface CaseInfoService {

	// 添加
	public CaseInfoEntity save(CaseInfoEntity caseInfoEntity);
	public CaseInfoEntity saveCase(CaseInfoEntity caseInfoEntity,String caseInfo);

	// 添加(级联添加关键词)
	public CaseInfoEntity saveWithKeyword(CaseInfoEntity caseInfoEntity, String keywords);

	// 修改(级联修改关键词)
	public CaseInfoEntity updateWithKeyword(CaseInfoEntity caseInfoEntity, String keywords);

	// findById
	public CaseInfoEntity findBId(String id);

	// 分页与模糊查询
	public Page<CaseInfoEntity> findListByName(String name, String auditStatus, Pageable pageable);
	
	// 接口 -- 分页与查询
	public Page<CaseInfoEntity> findPage(String name, String cropsTypeCode,String dipTypeCode, Pageable pageable);

	// 修改
	public CaseInfoEntity update(CaseInfoEntity caseInfoEntity);
	public CaseInfoEntity updateCase(CaseInfoEntity caseInfoEntity,HttpServletRequest res,HttpServletResponse req);

	// 删除
	public void delete(String id);

	// 查询所有病虫害信息的最新3条记录
	public List<CaseInfoEntity> findCaseInfo();

	// 关键字搜索病虫害信息
	public List<CaseInfoEntity> findCaseInfoByKey(String name);

	//切换状态 启用禁用
	CaseInfoEntity enable(CaseInfoEntity caseInfoEntity, boolean result);
	
	//设为精选 0是1否
	CaseInfoEntity setSelect(CaseInfoEntity caseInfoEntity, boolean result);
	
	//设为与我无关
	CaseInfoEntity setIsIrrelevant(CaseInfoEntity caseInfoEntity, boolean result);
	
	// 看图识病查询
	public Page<CaseInfoEntity> findByNum(Integer type,Pageable pageable);
	
	// 通过id userId查询看图识病信息
	public Map<String,Object> findInfoByUserId(String id,String userId);

}
