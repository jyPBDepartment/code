package com.jy.pc.Service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.ExclusiveCommentEntity;

public interface ExclusiveCommentService {
	// 根据贴子id返回分页信息
		public Page<ExclusiveCommentEntity> findByPostId(String agrId, Pageable pageable);
		
		// 添加
		public ExclusiveCommentEntity save(ExclusiveCommentEntity exclusiveCommentEntity);

		// 修改
		public void update(ExclusiveCommentEntity moduleInfo);

		// 删除
		public void delete(String id);

		// 主鍵查詢
		public ExclusiveCommentEntity findId(String id);

		// 切换状态
		void enable(ExclusiveCommentEntity exclusiveCommentEntity, boolean result);

		public List<ExclusiveCommentEntity> findPostId(String postId);

		public Page<List<Map<String, Object>>> findPageByParam(String title, String name, String content,
				Pageable pageable);
}
