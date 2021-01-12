package com.jy.pc.Service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.CaseInfoCommentDAO;
import com.jy.pc.Entity.CaseInfoCommentEntity;
import com.jy.pc.Entity.CaseInfoEntity;
import com.jy.pc.Service.CaseInfoCommentService;

//看图识病评论表 ServiceImpl
@Service
public class CaseInfoCommentServiceImpl implements CaseInfoCommentService{
	@Autowired
	private CaseInfoCommentDAO caseInfoCommentDao;

	//保存
	@Override
	public CaseInfoCommentEntity save(CaseInfoCommentEntity caseInfoComment) {
		return caseInfoCommentDao.saveAndFlush(caseInfoComment);
	}

	//修改
	@Override
	public void update(CaseInfoCommentEntity caseInfoComment) {
		caseInfoCommentDao.saveAndFlush(caseInfoComment);
	}

	//分页模糊查询
	@Override
	public Page<CaseInfoCommentEntity> findListByName(String commentContent, String commentUserName, String caseId, Pageable pageable) {
		String content = "%" + commentContent + "%";
		String userName = "%" + commentUserName + "%";
		String name = "%" + caseId + "%";
		return caseInfoCommentDao.findListByName(content, userName, name, pageable);
	}

	//findById
	@Override
	public CaseInfoCommentEntity findId(String id) {
		return caseInfoCommentDao.findBId(id);
	}

	//删除
	@Override
	public void delete(String id) {
		caseInfoCommentDao.deleteById(id);
	}

	//新增评论
	@Override
	public CaseInfoCommentEntity saveCaseInfo(CaseInfoCommentEntity caseInfoComment) {
		Date date = new Date();
		caseInfoComment.setCommentDate(date);
		caseInfoComment.setStatus(1);
		CaseInfoEntity caseInfo =  new CaseInfoEntity();
		caseInfo.setId(caseInfoComment.getCaseId());
		caseInfoComment.setCaseInfoEntity(caseInfo);
		caseInfoCommentDao.saveAndFlush(caseInfoComment);
		return caseInfoComment;
	}

	//修改状态
	@Override
	public CaseInfoCommentEntity updateEnable(CaseInfoCommentEntity caseInfoCommentEntity, boolean result) {
		return caseInfoCommentDao.saveAndFlush(caseInfoCommentEntity);
	}

}