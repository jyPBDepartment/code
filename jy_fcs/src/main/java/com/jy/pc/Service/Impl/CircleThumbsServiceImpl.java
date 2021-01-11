package com.jy.pc.Service.Impl;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.CircleThumbsDao;
import com.jy.pc.Entity.CircleThumbsEntity;
import com.jy.pc.Service.CircleThumbsService;

@Service
public class CircleThumbsServiceImpl implements CircleThumbsService {

	@Autowired
	private CircleThumbsDao circleThumbsDao;

	//添加
	@Override
	public void save(CircleThumbsEntity circleThumbsEntity) throws ServiceException {
		circleThumbsDao.saveAndFlush(circleThumbsEntity);

	}

	//刪除
	@Override
	public void delete(String id) {

		circleThumbsDao.deleteById(id);
	}

	//根據ID查詢
	@Override
	public CircleThumbsEntity findUserId(String thumbsUserId, String circleId) {

		return circleThumbsDao.findUserId(thumbsUserId, circleId);

	}

}
