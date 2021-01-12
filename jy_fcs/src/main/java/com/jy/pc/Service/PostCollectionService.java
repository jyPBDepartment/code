package com.jy.pc.Service;

import java.util.List;
import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.PostCollectionEntity;

public interface PostCollectionService {

	// 添加
	public void save(PostCollectionEntity postCollectionEntity) throws ServiceException;

	// 删除
	public void delete(String id);

	// 通过Id查询
	public PostCollectionEntity findUserId(String userId, String circleId);
	
	
	/**
	 * 我的-我的收藏
	 * 
	 * @Param userId 用户Id
	 */
	public Page<List<Map<String, Object>>> findPageByParam(String userId, Pageable pageable);
}
