package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.CaseInfoCommentEntity;

//看图识病评论表 Service
public interface CaseInfoCommentService {
	// 添加
	public CaseInfoCommentEntity save(CaseInfoCommentEntity caseInfoComment);
	//修改
	public void update(CaseInfoCommentEntity caseInfoComment);
	//查询
	public Page<CaseInfoCommentEntity> findListByName(String commentContent, String commentUserName, String caseId, Pageable pageable);
	//findById
	public CaseInfoCommentEntity findId(String id);
	// 删除
	public void delete(String id);
	// 添加
	public CaseInfoCommentEntity saveCaseInfo(CaseInfoCommentEntity caseInfoComment);
	// 修改状态
	CaseInfoCommentEntity updateEnable(CaseInfoCommentEntity caseInfoCommentEntity, boolean result);
	

}
