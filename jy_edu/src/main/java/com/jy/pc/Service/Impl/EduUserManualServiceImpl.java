package com.jy.pc.Service.Impl;

import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.EduUserManualDao;
import com.jy.pc.Entity.EduUserManualEntity;
import com.jy.pc.Service.EduUserManualService;

@Service
@Transactional
public class EduUserManualServiceImpl implements EduUserManualService{
	
	@Autowired
	private EduUserManualDao eduUserManualDao;
	
	//保存用户手册关联表
	public void save(EduUserManualEntity eduUserManualEntity) throws ServiceException {
		
		eduUserManualDao.saveAndFlush(eduUserManualEntity);
	}

	@Override
	public void delete(String id) {
		eduUserManualDao.deleteById(id);
	}

	//通过userId查询
	@Override
	public EduUserManualEntity findUserId(String userId,String manualInfoId) {
		return eduUserManualDao.findUserId(userId,manualInfoId);
	}

}
