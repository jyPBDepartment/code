package com.jy.pc.Service.Impl;


import java.util.List;
import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.PostCollectionDao;
import com.jy.pc.Entity.PostCollectionEntity;
import com.jy.pc.Service.PostCollectionService;

@Service
public class PostCollectionServiceImpl implements PostCollectionService {

	@Autowired
	public PostCollectionDao postCollectionDao;

	// 添加
	@Override
	public void save(PostCollectionEntity postCollectionEntity) throws ServiceException {
		postCollectionDao.saveAndFlush(postCollectionEntity);
	}

	// 刪除
	@Override
	public void delete(String id) {
		postCollectionDao.deleteById(id);
	}

	// 根據ID查詢
	@Override
	public PostCollectionEntity findUserId(String userId, String circleId) {

		return postCollectionDao.findUserId(userId, circleId);
	}

	/**
	 * 我的-我的收藏
	 * 
	 * @Param userId 用户Id
	 */

	@Override
	public Page<List<Map<String, Object>>> findPageByParam(String userId, Pageable pageable) {
	
		return postCollectionDao.findPageByParam(userId, pageable);
	}

	

	
}
