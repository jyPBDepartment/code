package com.jy.pc.Service;

import org.hibernate.service.spi.ServiceException;

import com.jy.pc.Entity.EduUserExamEntity;

public interface EduUserExamService {
	
	public void save(EduUserExamEntity eduUserExamEntity) throws ServiceException;

}
