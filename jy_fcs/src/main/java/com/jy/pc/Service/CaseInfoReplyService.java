package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.CaseInfoReplyEntity;

//看图识病回复表 Service
public interface CaseInfoReplyService {
	// 添加
	public CaseInfoReplyEntity save(CaseInfoReplyEntity caseInfoReplyEntity);

	// 修改
	public void update(CaseInfoReplyEntity caseInfoReplyEntity);

	// 查询
	public Page<CaseInfoReplyEntity> findListByName(String replyContent, String replyUserName, Pageable pageable);

	// 删除
	public void delete(String id);

	// 通过评论id查询
	public List<CaseInfoReplyEntity> findByCommentId(String commentId);
	
	// 修改状态
	CaseInfoReplyEntity updateEnable(CaseInfoReplyEntity caseInfoReplyEntity, boolean result);
	
	// 通过id查询
	public CaseInfoReplyEntity findId(String id);

}
