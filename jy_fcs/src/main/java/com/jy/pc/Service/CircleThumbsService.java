package com.jy.pc.Service;

import org.hibernate.service.spi.ServiceException;

import com.jy.pc.Entity.CircleThumbsEntity;

public interface CircleThumbsService {
	//添加	
	public void save(CircleThumbsEntity circleThumbsEntity) throws ServiceException;

	// 删除
	public void delete(String id);

	// 通过Id查询
	public CircleThumbsEntity findUserId(String thumbsUserId, String circleId);
}
