package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.PostCommentInfoEntity;

public interface PostCommentInfoService {
	// 搜索
	public Page<PostCommentInfoEntity> findListByContent(String content, Pageable pageable);

	// 添加
	public PostCommentInfoEntity save(PostCommentInfoEntity moduleInfo);

	// 修改
	public void update(PostCommentInfoEntity moduleInfo);

	// 删除
	public void delete(String id);

	// 主鍵查詢
	public PostCommentInfoEntity findId(String id);
}
