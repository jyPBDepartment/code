package com.jy.pc.Service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.ExclusiveReplyEntity;

public interface ExclusiveReplyService {
	// 搜索
		public Page<List<Map<String, Object>>> findReplyPageByParam(String commentId,String content, String user, Pageable pageable);

		// 根据贴子id返回分页信息
		public Page<ExclusiveReplyEntity> findByPostId(String agrId, Pageable pageable);

		// 添加
		public ExclusiveReplyEntity save(ExclusiveReplyEntity exclusiveReplyEntity);

		// 修改
		public void update(ExclusiveReplyEntity exclusiveReplyEntity);

		// 删除
		public void delete(String id);

		// 主鍵查詢
		public ExclusiveReplyEntity findId(String id);

		// 切换状态
		void enable(ExclusiveReplyEntity exclusiveReplyEntity, boolean result);

		public List<ExclusiveReplyEntity> findPostId(String postId);
		
		public Page<List<Map<String,Object>>> findReplyByUserId(String commmentId,String userId,Pageable pageable);
}
