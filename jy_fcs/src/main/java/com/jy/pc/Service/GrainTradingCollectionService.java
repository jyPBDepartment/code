package com.jy.pc.Service;

import java.util.List;

import com.jy.pc.Entity.GrainTradingCollectionEntity;

public interface GrainTradingCollectionService {
	// 条件查询--信息id和用户id
	public GrainTradingCollectionEntity findInfoByAll(String agrId, String userId);

	// 条件查询 -- 信息id
	public List<GrainTradingCollectionEntity> findInfoByAgr(String agrId);

	// 条件查询 -- 用户id
	public List<GrainTradingCollectionEntity> findInfoByUser(String userId);

	// 收藏
	public void collection(String agrId, String userId);

	// 取消收藏
	public void cancel(String agrId, String userId) throws Exception;

	// 保存
	public GrainTradingCollectionEntity save(GrainTradingCollectionEntity grainTradingCollentionEntity);
}
