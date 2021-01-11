package com.jy.pc.Service;

import org.hibernate.service.spi.ServiceException;

import com.jy.pc.Entity.PostCollectionEntity;

public interface PostCollectionService {

	// 添加
	public void save(PostCollectionEntity postCollectionEntity) throws ServiceException;

	// 删除
	public void delete(String id);

	// 通过Id查询
	public PostCollectionEntity findUserId(String userId, String circleId);
}
