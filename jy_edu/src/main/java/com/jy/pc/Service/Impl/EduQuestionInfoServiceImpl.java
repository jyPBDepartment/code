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
import com.jy.pc.DAO.EduOptionInfoDao;
import com.jy.pc.DAO.EduQuestionInfoDao;
import com.jy.pc.Entity.EduOptionInfoEntity;
import com.jy.pc.Entity.EduQuestionInfoEntity;
import com.jy.pc.Entity.EduVocationInfoEntity;
import com.jy.pc.Service.EduQuestionInfoService;
import com.jy.pc.Utils.DbLogUtil;

/**
 * 试题表ServiceImpl
 * */
@Service
public class EduQuestionInfoServiceImpl implements EduQuestionInfoService{
	@Autowired
	EduQuestionInfoDao eduQuestionInfoDao;
	@Autowired
	EduOptionInfoDao eduOptionInfoDao;
	@Autowired
	DbLogUtil logger;

	//分页与查询
	@Override
	public Page<EduQuestionInfoEntity> findListByName(String createBy, String quType, String status,
			Pageable pageable) {
		String createName = "%"+ createBy +"%";
		return eduQuestionInfoDao.findListByName(createName, quType, status, pageable);
	}

	// 通过id查询
	@Override
	public EduQuestionInfoEntity findId(String id) {
		return eduQuestionInfoDao.findId(id);
	}

	//添加
	@Override
	public EduQuestionInfoEntity save(EduQuestionInfoEntity eduQuestionInfoEntity) {
		logger.initAddLog(eduQuestionInfoEntity);
		return eduQuestionInfoDao.saveAndFlush(eduQuestionInfoEntity);
	}

	//修改
	@Override
	public void update(EduQuestionInfoEntity eduQuestionInfoEntity) {
		logger.initUpdateLog(eduQuestionInfoEntity);
		eduQuestionInfoDao.saveAndFlush(eduQuestionInfoEntity);
	}

	//删除
	@Override
	public void delete(String id) {	
		logger.initDeleteLog(eduQuestionInfoDao.findId(id));
		eduQuestionInfoDao.deleteById(id);
	}

	//修改状态
	@Override
	public void enable(EduQuestionInfoEntity eduQuestionInfoEntity, boolean result) {
		logger.initEnableLog(eduQuestionInfoEntity, result);
		eduQuestionInfoDao.saveAndFlush(eduQuestionInfoEntity);
	}

	//添加试题选项
	@Transactional
	@Override
	public EduQuestionInfoEntity saveOption(HttpServletRequest res, HttpServletResponse req,EduQuestionInfoEntity eduQuestionInfoEntity, String addOption,String addName) {
		String[] option = null;
		if(addOption.indexOf(",")>-1) {
			option = addOption.split(",");
		}else {
			option[0]= addOption;
		}
		String[] optionName = null;
		if(addName.indexOf(",")>-1) {
			optionName = addName.split(",");
		}else {
			optionName[0]= addName;
		}
		String s = res.getParameter("eduQuestionInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		eduQuestionInfoEntity = jsonObject.toJavaObject(EduQuestionInfoEntity.class);
		EduVocationInfoEntity vocation = new EduVocationInfoEntity();
		vocation.setId(eduQuestionInfoEntity.getVocationId());
		eduQuestionInfoEntity.setVocation(vocation);
		Date date = new Date();
		eduQuestionInfoEntity.setCreateDate(date);
		eduQuestionInfoEntity.setStatus(1);
		EduQuestionInfoEntity question = eduQuestionInfoDao.save(eduQuestionInfoEntity);
		for(int i=0;i<option.length;i++) {
			EduOptionInfoEntity eduOptionInfoEntity = new EduOptionInfoEntity();
			eduOptionInfoEntity.setQuId(question.getId());
			eduOptionInfoEntity.setTitle(optionName[i]);
			eduOptionInfoEntity.setContent(option[i]);
			eduOptionInfoDao.save(eduOptionInfoEntity);
		}
		logger.initAddLog(eduQuestionInfoEntity);
		return eduQuestionInfoEntity;
	}

	//修改试题
	@Transactional
	@Override
	public void updateOption(HttpServletRequest res, HttpServletResponse req,EduQuestionInfoEntity eduQuestionInfoEntity, String addOption, String addName) {
		String[] option = null;
		if(addOption.indexOf(",")>-1) {
			option = addOption.split(",");
		}else {
			option[0]= addOption;
		}
		String[] optionName = null;
		if(addName.indexOf(",")>-1) {
			optionName = addName.split(",");
		}else {
			optionName[0]= addName;
		}
		String s = res.getParameter("eduQuestionInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		eduQuestionInfoEntity = jsonObject.toJavaObject(EduQuestionInfoEntity.class);
		EduVocationInfoEntity vocation = new EduVocationInfoEntity();
		vocation.setId(eduQuestionInfoEntity.getVocationId());
		eduQuestionInfoEntity.setVocation(vocation);
		Date date = new Date();
		eduQuestionInfoEntity.setUpdateDate(date);
		EduQuestionInfoEntity question = eduQuestionInfoDao.saveAndFlush(eduQuestionInfoEntity);
		List<EduOptionInfoEntity> optionInfo = eduOptionInfoDao.findquestionId(question.getId());
		if(option.length > optionInfo.size()) {
			for(int i=0;i<option.length;i++) {
				if(i>1) {
					EduOptionInfoEntity OptionInfoEntity = new EduOptionInfoEntity();
					OptionInfoEntity.setTitle(optionName[i]);
					OptionInfoEntity.setContent(option[i]);
					eduOptionInfoDao.save(OptionInfoEntity);
				}else {
					EduOptionInfoEntity eduOptionInfoEntity = eduOptionInfoDao.findId(optionInfo.get(i).getId());
					eduOptionInfoEntity.setTitle(optionName[i]);
					eduOptionInfoEntity.setContent(option[i]);
					eduOptionInfoDao.saveAndFlush(eduOptionInfoEntity);
				}
			}
		}
		if(option.length < optionInfo.size()) {
			for(int j=0;j<optionInfo.size(); j++) {
				if(j>1) {
					eduOptionInfoDao.deleteById(optionInfo.get(j).getId());
				}else {
					EduOptionInfoEntity eduOptionInfoEntity = eduOptionInfoDao.findId(optionInfo.get(j).getId());
					eduOptionInfoEntity.setTitle(optionName[j]);
					eduOptionInfoEntity.setContent(option[j]);
					eduOptionInfoDao.saveAndFlush(eduOptionInfoEntity);
				}
			}
		}
		if(option.length == optionInfo.size()) {
			for(int k=0;k<optionInfo.size(); k++) {
				EduOptionInfoEntity eduOptionInfoEntity = eduOptionInfoDao.findId(optionInfo.get(k).getId());
				eduOptionInfoEntity.setTitle(optionName[k]);
				eduOptionInfoEntity.setContent(option[k]);
				eduOptionInfoDao.saveAndFlush(eduOptionInfoEntity);
			}
		}
		logger.initUpdateLog(eduQuestionInfoEntity);
	}

	//查询分页(启用)
	@Override
	public Page<EduQuestionInfoEntity> findQuestion(String quType, String voationId, Pageable pageable) {
		return eduQuestionInfoDao.findQuestion(quType, voationId, pageable);
	}
	
	public List<EduQuestionInfoEntity> findListByIds(List<String> ids){
		
		return eduQuestionInfoDao.findListByIds(ids);
	}
}
