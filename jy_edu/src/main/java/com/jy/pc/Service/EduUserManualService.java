package com.jy.pc.Service;

import org.hibernate.service.spi.ServiceException;

import com.jy.pc.Entity.EduUserManualEntity;

public interface EduUserManualService {

	public void save(EduUserManualEntity eduUserManualEntity) throws ServiceException;
	
	//删除 
	public void delete(String id);
	
	//通过userId查询
	public EduUserManualEntity findUserId(String userId,String manualInfoId);

}
