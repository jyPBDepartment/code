package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.CommentReplyInfoEntity;

public interface CommentReplyInfoService {
	// 搜索
	public Page<CommentReplyInfoEntity> findListByContent(String content, String user,Pageable pageable);

	// 添加
	public CommentReplyInfoEntity save(CommentReplyInfoEntity moduleInfo);

	// 修改
	public void update(CommentReplyInfoEntity moduleInfo);

	// 删除
	public void delete(String id);

	// 主鍵查詢
	public CommentReplyInfoEntity findId(String id);

	void enable(CommentReplyInfoEntity commentReplyInfoEntity, boolean result);
}
