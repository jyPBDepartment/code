package com.jy.pc.Service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.CommentReplyInfoEntity;

public interface CommentReplyInfoService {
	// 搜索
	public Page<List<Map<String,Object>>> findListByContent(String content, String user, String commentId,
			Pageable pageable);

	// 添加
	public CommentReplyInfoEntity save(CommentReplyInfoEntity moduleInfo);

	// 修改
	public void update(CommentReplyInfoEntity moduleInfo);

	// 删除
	public void delete(String id);

	// 主鍵查詢
	public CommentReplyInfoEntity findId(String id);

	void enable(CommentReplyInfoEntity commentReplyInfoEntity, boolean result);

//	public Page<CommentReplyInfoPO> findByCommentId(String commentId, Pageable pageable);

	// 条件查询帖子回复人ID
	public List<CommentReplyInfoEntity> findByUserReplyId(String replyUserId);

	// 根据id,userId查询回复信息
	public Page<List<Map<String, Object>>> findReplyByUserId(String commentId, String userId, Pageable pageable);

	//查询评论下所有回复
	public Page<List<Map<String, Object>>> findReplyPage(String commentId, String userId,Pageable pageable);

	public List<CommentReplyInfoEntity> findByCommentId(String commentId);

}
