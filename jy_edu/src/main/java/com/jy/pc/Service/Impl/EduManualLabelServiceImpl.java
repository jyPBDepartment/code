package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.EduManualLabelDao;
import com.jy.pc.Entity.EduManualLabelInfoEntity;
import com.jy.pc.Service.EduManualLabelService;
import com.jy.pc.Utils.DbLogUtil;

@Service
public class EduManualLabelServiceImpl implements EduManualLabelService{
	@Autowired
	EduManualLabelDao eduManualLabelDao;
	@Autowired
	DbLogUtil logger;

	//分页模糊查询
	@Override
	public Page<EduManualLabelInfoEntity> findListByName(String createBy, String name, String status, Pageable pageable) {
		String createName="%"+ createBy +"%";
		String labelName="%"+ name +"%";
		return eduManualLabelDao.findListByName(createName, labelName, status, pageable);
	}

	//新增
	@Override
	public EduManualLabelInfoEntity save(EduManualLabelInfoEntity eduManualLabelInfoEntity) {
		logger.initAddLog(eduManualLabelInfoEntity);
		return eduManualLabelDao.saveAndFlush(eduManualLabelInfoEntity);
	}

	//修改
	@Override
	public void update(EduManualLabelInfoEntity eduManualLabelInfoEntity) {
		logger.initUpdateLog(eduManualLabelInfoEntity);
		eduManualLabelDao.saveAndFlush(eduManualLabelInfoEntity);
	}

	//删除
	@Override
	public void delete(String id) {
		logger.initDeleteLog(eduManualLabelDao.findId(id));
		eduManualLabelDao.deleteById(id);
	}

	//通过id查询
	@Override
	public EduManualLabelInfoEntity findId(String id) {
		return eduManualLabelDao.findId(id);
	}

	//修改状态
	@Override
	@Transactional
	public void enable(EduManualLabelInfoEntity eduManualLabelInfoEntity, boolean result) {
		logger.initEnableLog(eduManualLabelInfoEntity, result);
		eduManualLabelDao.saveAndFlush(eduManualLabelInfoEntity);
	}

	@Override
	public List<EduManualLabelInfoEntity> findManualLabelId() {
		return eduManualLabelDao.findManualLabelId();
	}
}
