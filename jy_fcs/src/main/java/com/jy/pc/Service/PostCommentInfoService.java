package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.PostCommentInfoEntity;
import com.jy.pc.POJO.PostCommentInfoPO;

public interface PostCommentInfoService {
	// 搜索
	public Page<PostCommentInfoEntity> findListByContent(String content, String user,Pageable pageable);

	//根据贴子id返回分页信息
	public Page<PostCommentInfoPO> findByPostId(String postId,Pageable pageable);
	
	// 添加
	public PostCommentInfoEntity save(PostCommentInfoEntity moduleInfo);

	// 修改
	public void update(PostCommentInfoEntity moduleInfo);

	// 删除
	public void delete(String id);

	// 主鍵查詢
	public PostCommentInfoEntity findId(String id);

	//切换状态
	void enable(PostCommentInfoEntity postCommentInfoEntity, boolean result);
	
	public List<PostCommentInfoEntity> findPostId(String postId);
}
