package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.EduQuestionExamDao;
import com.jy.pc.Entity.EduQuestionExamLinkEntity;
import com.jy.pc.Service.EduQuestionExamService;

/**
 * 试卷试题关联表ServiceImpl
 * */
@Service
public class EduQuestionExamServiceImpl implements EduQuestionExamService{
	@Autowired
	EduQuestionExamDao eduQuestionExamDao;

	//通过id查询
	@Override
	public EduQuestionExamLinkEntity findId(String id) {
		return eduQuestionExamDao.findId(id);
	}

	//添加
	@Override
	public EduQuestionExamLinkEntity save(EduQuestionExamLinkEntity eduQuestionExamLinkEntity) {
		return eduQuestionExamDao.saveAndFlush(eduQuestionExamLinkEntity);
	}

	//修改
	@Override
	public void update(EduQuestionExamLinkEntity eduQuestionExamLinkEntity) {
		eduQuestionExamDao.saveAndFlush(eduQuestionExamLinkEntity);
	}

	//删除
	@Override
	public void delete(String id) {
		eduQuestionExamDao.deleteById(id);
	}

	//通过试卷ID查询
	@Override
	public List<EduQuestionExamLinkEntity> findExamId(String examId) {
		return eduQuestionExamDao.findExamId(examId);
	}

}
