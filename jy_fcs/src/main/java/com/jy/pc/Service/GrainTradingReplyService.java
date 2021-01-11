package com.jy.pc.Service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.GrainTradingReplyEntity;

public interface GrainTradingReplyService {
	// 搜索
	public Page<GrainTradingReplyEntity> findListByContent(String content, String user, Pageable pageable);

	// 根据贴子id返回分页信息
	public Page<GrainTradingReplyEntity> findByPostId(String agrId, Pageable pageable);

	// 添加
	public GrainTradingReplyEntity save(GrainTradingReplyEntity grainTradingReplyEntity);

	// 修改
	public void update(GrainTradingReplyEntity grainTradingReplyEntity);

	// 删除
	public void delete(String id);

	// 主鍵查詢
	public GrainTradingReplyEntity findId(String id);

	// 切换状态
	void enable(GrainTradingReplyEntity grainTradingReplyEntity, boolean result);

	public List<GrainTradingReplyEntity> findPostId(String postId);

	public Page<List<Map<String, Object>>> findPageByParam(String title, String name, String content,
			Pageable pageable);

	public Page<GrainTradingReplyEntity> findCommentPage(String cid, Pageable pageable);
}
