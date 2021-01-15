package com.jy.pc.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.CaseInfoReplyDAO;
import com.jy.pc.Entity.CaseInfoCommentEntity;
import com.jy.pc.Entity.CaseInfoReplyEntity;
import com.jy.pc.Service.CaseInfoReplyService;

//看图识病回复表 ServiceImpl
@Service
public class CaseInfoReplyServiceImpl implements CaseInfoReplyService{
	@Autowired
	private CaseInfoReplyDAO caseInfoReplyDAO;

	//新增
	@Override
	public CaseInfoReplyEntity save(CaseInfoReplyEntity caseInfoReplyEntity) {
		Date date = new Date();
		caseInfoReplyEntity.setReplyDate(date);
		caseInfoReplyEntity.setStatus(1);
		CaseInfoCommentEntity caseInfoComment = new CaseInfoCommentEntity();
		caseInfoComment.setId(caseInfoReplyEntity.getCommentId());
		caseInfoReplyEntity.setCaseInfoCommentEntity(caseInfoComment);
		caseInfoReplyDAO.saveAndFlush(caseInfoReplyEntity);
		return caseInfoReplyEntity;
	}

	//修改
	@Override
	public void update(CaseInfoReplyEntity caseInfoReplyEntity) {
		caseInfoReplyDAO.saveAndFlush(caseInfoReplyEntity);
	}

	//分页模糊查询
	@Override
	public Page<CaseInfoReplyEntity> findListByName(String replyContent, String replyUserName, Pageable pageable) {
		String content = "%" + replyContent + "%";
		String userName = "%" + replyUserName + "%";
		return caseInfoReplyDAO.findListByName(content, userName, pageable);
	}

	//删除
	@Override
	public void delete(String id) {
		caseInfoReplyDAO.deleteById(id);
	}

	//通过评论id查询
	@Override
	public List<CaseInfoReplyEntity> findByCommentId(String commentId) {
		return caseInfoReplyDAO.findByCommentId(commentId);
	}

	// 修改状态
	@Override
	public CaseInfoReplyEntity updateEnable(CaseInfoReplyEntity caseInfoReplyEntity, boolean result) {
		return caseInfoReplyDAO.saveAndFlush(caseInfoReplyEntity);
	}

	//通过id查询
	@Override
	public CaseInfoReplyEntity findId(String id) {
		return caseInfoReplyDAO.findId(id);
	}

	//分页模糊搜索
	@Override
	public Page<List<Map<String, Object>>> findPageByParam(String name, String replyUserName, String replyContent,String commentId,
			Pageable pageable) {
		String caseName = "%" + name + "%";
		String userName = "%" + replyUserName + "%";
		String content = "%" + replyContent + "%";
		return caseInfoReplyDAO.findPageByParam(caseName, userName, content, commentId, pageable);
	}

	//通过回复人id查询
	@Override
	public List<CaseInfoReplyEntity> findByReplyId(String replyUserId) {
		return caseInfoReplyDAO.findByReplyId(replyUserId);
	}

	//评论id、用户id查询回复信息
	@Override
	public Page<List<Map<String, Object>>> findReplyByUserId(String commentId, String userId, Pageable pageable) {
		return caseInfoReplyDAO.findReplyByUserId(commentId, userId, pageable);
	}

}
