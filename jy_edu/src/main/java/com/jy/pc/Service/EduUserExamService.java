package com.jy.pc.Service;

import java.util.List;
import java.util.Map;

import org.hibernate.service.spi.ServiceException;

import com.jy.pc.Entity.EduUserExamEntity;

public interface EduUserExamService {
	
	public void save(EduUserExamEntity eduUserExamEntity) throws ServiceException;
	
	public List<Map<String,Object>> getExamResultByUserId(String userId) throws ServiceException;

}
