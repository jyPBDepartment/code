package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.EduManualInfoDao;
import com.jy.pc.Entity.EduManualInfoEntity;
import com.jy.pc.Service.EduManualInfoService;
import com.jy.pc.Utils.DbLogUtil;
/**
 * 手册ServiceImpl
 * */
@Service
public class EduManualInfoServiceImpl implements EduManualInfoService{
	@Autowired
	EduManualInfoDao eduManualInfoDao;
	@Autowired
	DbLogUtil logger;

	// 分页与模糊查询
	@Override
	public Page<EduManualInfoEntity> findListByName(String title, String createBy, String vocationId, String labelId,
			Pageable pageable) {
		String manualTitle = "%"+ title +"%";
		String createName = "%"+ createBy +"%";
		return eduManualInfoDao.findListByName(manualTitle, createName, vocationId, labelId, pageable);
	}

	// 通过id查询
	@Override
	public EduManualInfoEntity findId(String id) {
		return eduManualInfoDao.findId(id);
	}

	// 新增
	@Override
	public EduManualInfoEntity save(EduManualInfoEntity eduManualInfoEntity) {
		logger.initAddLog(eduManualInfoEntity);
		return eduManualInfoDao.saveAndFlush(eduManualInfoEntity);
	}

	// 修改
	@Override
	public void update(EduManualInfoEntity eduManualInfoEntity) {
		logger.initUpdateLog(eduManualInfoEntity);
		eduManualInfoDao.saveAndFlush(eduManualInfoEntity);
	}

	// 删除
	@Override
	public void delete(String id) {
		logger.initDeleteLog(eduManualInfoDao.findId(id));
		eduManualInfoDao.deleteById(id);
	}

	// 修改状态
	@Override
	@Transactional
	public void enable(EduManualInfoEntity eduManualInfoEntity, boolean result) {
		logger.initEnableLog(eduManualInfoEntity, result);
		eduManualInfoDao.saveAndFlush(eduManualInfoEntity);
	}
	
	//app我的收藏/学习记录
	public List<EduManualInfoEntity> getManualListByUserId(String userId,int isCollection) throws ServiceException{
		
		return eduManualInfoDao.getManualListByUserId(userId,isCollection);
	}

}
