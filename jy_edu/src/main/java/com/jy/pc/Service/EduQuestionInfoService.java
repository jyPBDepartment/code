package com.jy.pc.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.EduQuestionInfoEntity;
/**
 * 试题表Service
 * */
public interface EduQuestionInfoService {
	// 分页与模糊查询
	public Page<EduQuestionInfoEntity> findListByName(String createBy,String quType, String status, Pageable pageable);

	// 通过id查询
	public EduQuestionInfoEntity findId(String id);
	
	//添加
	public EduQuestionInfoEntity save(EduQuestionInfoEntity eduQuestionInfoEntity);
	
	//修改
	public void update(EduQuestionInfoEntity eduQuestionInfoEntity);
	
	//删除
	public void delete(String id);
	
	//调整状态
	void enable(EduQuestionInfoEntity eduQuestionInfoEntity,boolean result);
	
	//添加试题选项
	public EduQuestionInfoEntity saveOption(HttpServletRequest res, HttpServletResponse req,EduQuestionInfoEntity eduQuestionInfoEntity,String addOption,String addName);
	
	//修改试题选项
	public void updateOption(HttpServletRequest res, HttpServletResponse req,EduQuestionInfoEntity eduQuestionInfoEntity,String addOption,String addName);
	
	//查询启用的试卷
	public Page<EduQuestionInfoEntity> findQuestion(String quType,String voationId, Pageable pageable);
}
