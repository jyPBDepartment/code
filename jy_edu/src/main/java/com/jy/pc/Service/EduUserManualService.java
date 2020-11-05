package com.jy.pc.Service;

import org.hibernate.service.spi.ServiceException;

import com.jy.pc.Entity.EduUserManualEntity;

public interface EduUserManualService {
	
	public void save(EduUserManualEntity eduUserManualEntity) throws ServiceException;

}
