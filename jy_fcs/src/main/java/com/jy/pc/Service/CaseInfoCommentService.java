package com.jy.pc.Service;

import java.util.List;
import java.util.Map;

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
	
	public Page<List<Map<String, Object>>> findPageByCase(String name, String commentUserName, String commentContent,Pageable pageable);
	
	//通过评论人的id查询
	public List<CaseInfoCommentEntity> findByUserId(String commentUserId);
	
	//用户id、文章点评id查询评论信息
	public Page<List<Map<String,Object>>> findCommentByUserId(String caseId,String userId,Pageable pageable);

	// 查询最新一条评论
	public CaseInfoCommentEntity findNewCommentId(String caseId);
	
	//查询看图识病单条下所有评论
	public Page<List<Map<String, Object>>> findCommentPage(String caseId,String userId,Pageable pageable);
}
