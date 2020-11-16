package com.jy.pc.Service;

import java.util.List;

import com.jy.pc.Entity.EduQuestionExamLinkEntity;
/**
 * 试卷试题关联表Service
 * */
public interface EduQuestionExamService {
	// 通过id查询
	public EduQuestionExamLinkEntity findId(String id);
	
	//添加
	public EduQuestionExamLinkEntity save(EduQuestionExamLinkEntity eduQuestionExamLinkEntity);
	
	//修改
	public void update(EduQuestionExamLinkEntity eduQuestionExamLinkEntity);
	
	//删除
	public void delete(String id);
	
	// 通过试卷id查询
	public List<EduQuestionExamLinkEntity> findExamId(String examId);
	
	//通过试题id查询
	public List<EduQuestionExamLinkEntity> findQuestionId(String questionId);
}
