package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.EduOptionInfoDao;
import com.jy.pc.Entity.EduOptionInfoEntity;
import com.jy.pc.Service.EduOptionInfoService;

/**
 * 选项信息表ServiceImpl
 * */
@Service
public class EduOptionInfoServiceImpl implements EduOptionInfoService{
	@Autowired
	EduOptionInfoDao optionInfoDao;

	//通过id查询
	@Override
	public EduOptionInfoEntity findId(String id) {
		return optionInfoDao.findId(id);
	}

	//添加
	@Override
	public EduOptionInfoEntity save(EduOptionInfoEntity eduOptionInfoEntity) {
		return optionInfoDao.saveAndFlush(eduOptionInfoEntity);
	}

	//修改
	@Override
	public void update(EduOptionInfoEntity eduOptionInfoEntity) {
		optionInfoDao.saveAndFlush(eduOptionInfoEntity);
	}

	//删除
	@Override
	public void delete(String id) {
		optionInfoDao.deleteById(id);
	}

	//通过试题id查询
	@Override
	public List<EduOptionInfoEntity> findquestionId(String questionId) {
		return optionInfoDao.findquestionId(questionId);
	}

}
