package com.jy.pc.Service;

import java.util.List;

import com.jy.pc.Entity.GrainTradingPraiseEntity;

public interface GrainTradingPraiseService {
	// 条件查询--信息id和用户id
	public GrainTradingPraiseEntity findInfoByAll(String agrId, String userId);

	// 条件查询 -- 信息id
	public List<GrainTradingPraiseEntity> findInfoByAgr(String agrId);

	// 条件查询 -- 用户id
	public List<GrainTradingPraiseEntity> findInfoByUser(String userId);

	// 收藏
	public void praise(String agrId, String userId);

	// 取消收藏
	public void cancel(String agrId, String userId) throws Exception;

	// 保存
	public GrainTradingPraiseEntity save(GrainTradingPraiseEntity grainTradingCollentionEntity);
}
