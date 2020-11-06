package com.jy.pc.Service;

import org.hibernate.service.spi.ServiceException;

import com.jy.pc.Entity.EduUserManualEntity;

public interface EduUserManualService {

	public void save(EduUserManualEntity eduUserManualEntity) throws ServiceException;

	// 通过手册id查询
	public EduUserManualEntity findManualInfoId(String manualInfoId);
	
	//删除 
	public void delete(String id);

}
