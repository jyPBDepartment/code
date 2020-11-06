package com.jy.pc.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.EduExamPaperInfoEntity;

/**
 * 试卷信息表Service
 * */
public interface EduExamPaperInfoService {
	// 分页与模糊查询
	public Page<EduExamPaperInfoEntity> findListByName(String createBy, String status, Pageable pageable);

	// 通过id查询
	public EduExamPaperInfoEntity findId(String id);
	
	//添加
	public EduExamPaperInfoEntity save(EduExamPaperInfoEntity eduExamPaperInfoEntity);
	
	//修改
	public void update(EduExamPaperInfoEntity eduExamPaperInfoEntity);
	
	//删除
	public void delete(String id);
	
	//调整状态
	void enable(EduExamPaperInfoEntity eduExamPaperInfoEntity,boolean result);
	
	//添加试题
	public EduExamPaperInfoEntity saveQuest(HttpServletRequest res, HttpServletResponse req,EduExamPaperInfoEntity eduExamPaperInfoEntity,String questionId);
	//修改试题
	public EduExamPaperInfoEntity updateQuest(HttpServletRequest res, HttpServletResponse req,EduExamPaperInfoEntity eduExamPaperInfoEntity,String questionId);

	public EduExamPaperInfoEntity findExamId(String id);
}
