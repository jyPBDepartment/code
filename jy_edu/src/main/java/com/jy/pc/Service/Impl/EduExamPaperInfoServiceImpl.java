package com.jy.pc.Service.Impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jy.pc.DAO.EduExamPaperInfoDao;
import com.jy.pc.DAO.EduQuestionExamDao;
import com.jy.pc.Entity.EduExamPaperInfoEntity;
import com.jy.pc.Entity.EduQuestionExamLinkEntity;
import com.jy.pc.Entity.EduVocationInfoEntity;
import com.jy.pc.Service.EduExamPaperInfoService;
import com.jy.pc.Utils.DbLogUtil;

/**
 * 试卷信息表ServiceImpl
 * */
@Service
public class EduExamPaperInfoServiceImpl implements EduExamPaperInfoService{
	@Autowired
	EduExamPaperInfoDao eduExamPaperInfoDao;
	@Autowired
	EduQuestionExamDao eduQuestionExamDao;
	@Autowired
	DbLogUtil logger;

	//分页 查询
	@Override
	public Page<EduExamPaperInfoEntity> findListByName(String createBy, String status, Pageable pageable) {
		String createName = "%"+ createBy +"%";
		return eduExamPaperInfoDao.findListByName(createName, status, pageable);
	}

	//通过id查询
	@Override
	public EduExamPaperInfoEntity findId(String id) {
		return eduExamPaperInfoDao.findId(id);
	}

	//新增
	@Override
	public EduExamPaperInfoEntity save(EduExamPaperInfoEntity eduExamPaperInfoEntity) {
		logger.initAddLog(eduExamPaperInfoEntity);
		return eduExamPaperInfoDao.saveAndFlush(eduExamPaperInfoEntity);
	}

	//修改
	@Override
	public void update(EduExamPaperInfoEntity eduExamPaperInfoEntity) {
		logger.initUpdateLog(eduExamPaperInfoEntity);
		eduExamPaperInfoDao.saveAndFlush(eduExamPaperInfoEntity);
	}

	//删除
	@Override
	public void delete(String id) {
		logger.initDeleteLog(eduExamPaperInfoDao.findId(id));
		eduExamPaperInfoDao.deleteById(id);
	}

	//修改状态
	@Override
	public void enable(EduExamPaperInfoEntity eduExamPaperInfoEntity, boolean result) {
		logger.initEnableLog(eduExamPaperInfoEntity, result);
		eduExamPaperInfoDao.saveAndFlush(eduExamPaperInfoEntity);
	}

	//试卷新增
	@Transactional
	@Override
	public EduExamPaperInfoEntity saveQuest(HttpServletRequest res, HttpServletResponse req,
			EduExamPaperInfoEntity eduExamPaperInfoEntity, String questionId) {
		String[] question = null;
		if(questionId.indexOf(",")>-1) {
			question = questionId.split(",");
		}else {
			question = new String[1];
			question[0]=questionId;
		}
		String s = res.getParameter("eduExamPaperInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		eduExamPaperInfoEntity = jsonObject.toJavaObject(EduExamPaperInfoEntity.class);
		EduVocationInfoEntity vocation = new EduVocationInfoEntity();
		vocation.setId(eduExamPaperInfoEntity.getVocationId());
		eduExamPaperInfoEntity.setVocation(vocation);
		Date date = new Date();
		eduExamPaperInfoEntity.setCreateDate(date);
		eduExamPaperInfoEntity.setStatus(1);
		eduExamPaperInfoEntity.setQuestionNum(question.length);
		EduExamPaperInfoEntity examPaper = eduExamPaperInfoDao.save(eduExamPaperInfoEntity);
		for(int i=0;i<question.length;i++) {
			EduQuestionExamLinkEntity eduQuestionExamLinkEntity = new EduQuestionExamLinkEntity();
			eduQuestionExamLinkEntity.setExamId(examPaper.getId());
			eduQuestionExamLinkEntity.setQuestionId(question[i]);
			eduQuestionExamDao.save(eduQuestionExamLinkEntity);
		}
		logger.initAddLog(eduExamPaperInfoEntity);
		return eduExamPaperInfoEntity;
	}

	//试卷修改
	@Transactional
	@Override
	public EduExamPaperInfoEntity updateQuest(HttpServletRequest res, HttpServletResponse req,
			EduExamPaperInfoEntity eduExamPaperInfoEntity, String questionId) {
		String[] question = null;
		if(questionId.indexOf(",")>-1) {
			question = questionId.split(",");
		}else {
			question = new String[1];
			question[0]=questionId;
		}
		String s = res.getParameter("eduExamPaperInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		eduExamPaperInfoEntity = jsonObject.toJavaObject(EduExamPaperInfoEntity.class);
		EduVocationInfoEntity vocation = new EduVocationInfoEntity();
		vocation.setId(eduExamPaperInfoEntity.getVocationId());
		eduExamPaperInfoEntity.setVocation(vocation);
		Date date = new Date();
		eduExamPaperInfoEntity.setUpdateDate(date);
		eduExamPaperInfoEntity.setStatus(1);
		eduExamPaperInfoEntity.setQuestionNum(question.length);
		EduExamPaperInfoEntity examPaper = eduExamPaperInfoDao.save(eduExamPaperInfoEntity);
		List<EduQuestionExamLinkEntity> questExam =eduQuestionExamDao.findExamId(examPaper.getId());
		for(int i=0;i<questExam.size();i++) {
			eduQuestionExamDao.deleteById(questExam.get(i).getId());
		}
		for(int i=0;i<question.length;i++) {
			EduQuestionExamLinkEntity eduQuestionExamLinkEntity = new EduQuestionExamLinkEntity();
			eduQuestionExamLinkEntity.setExamId(examPaper.getId());
			eduQuestionExamLinkEntity.setQuestionId(question[i]);
			eduQuestionExamDao.save(eduQuestionExamLinkEntity);
		}
		logger.initUpdateLog(eduExamPaperInfoEntity);
		return eduExamPaperInfoEntity;
	}

	@Override
	public EduExamPaperInfoEntity findExamId(String id) {
		return eduExamPaperInfoDao.findExamId(id);
	}

	//试卷列表接口板
	@Override
	public List<EduExamPaperInfoEntity> getExamListByVocationId(String vocationId) {
		return eduExamPaperInfoDao.getExamListByVocationId(vocationId);
	}

}
