package com.jy.pc.Service;

import java.util.List;

import com.jy.pc.Entity.ExclusiveCollectionEntity;

public interface ExclusiveCollectionService {

	// 条件查询--信息id和用户id
		public ExclusiveCollectionEntity findInfoByAll(String agrId, String userId);

		// 条件查询 -- 信息id
		public List<ExclusiveCollectionEntity> findInfoByAgr(String agrId);

		// 条件查询 -- 用户id
		public List<ExclusiveCollectionEntity> findInfoByUser(String userId);

		// 收藏
		public void collection(String agrId, String userId);

		// 取消收藏
		public void cancel(String agrId, String userId) throws Exception;

		// 保存
		public ExclusiveCollectionEntity save(ExclusiveCollectionEntity exclusiveCollectionEntity);
}
