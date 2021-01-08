package com.jy.pc.Service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.GrainTradingCommentEntity;

public interface GrainTradingCommentService {

	// 根据贴子id返回分页信息
	public Page<GrainTradingCommentEntity> findByPostId(String agrId, Pageable pageable);

	// 添加
	public GrainTradingCommentEntity save(GrainTradingCommentEntity grainTradingCommentEntity);

	// 修改
	public void update(GrainTradingCommentEntity moduleInfo);

	// 删除
	public void delete(String id);

	// 主鍵查詢
	public GrainTradingCommentEntity findId(String id);

	// 切换状态
	void enable(GrainTradingCommentEntity grainTradingCommentEntity, boolean result);

	public List<GrainTradingCommentEntity> findPostId(String postId);

	public Page<List<Map<String, Object>>> findPageByParam(String title, String name, String content,
			Pageable pageable);
}
