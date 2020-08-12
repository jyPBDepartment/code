package com.jy.pc.POJO;

import java.util.Date;
import java.util.List;


public class PostCommentInfoPO {
	private String id;

	// 评论内容
	private String commentContent;
	// 评论人
	private String commentUserName;
	// 评论时间
	private Date commentDate;
	// 状态 - 0生效1系统禁用
	private String status;
	// 回复列表
	private List<CommentReplyInfoPO> replyList;
	//回复数量
	private int replySize;
	public int getReplySize() {
		return replySize;
	}

	public void setReplySize(int replySize) {
		this.replySize = replySize;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentUserName() {
		return commentUserName;
	}

	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CommentReplyInfoPO> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<CommentReplyInfoPO> replyList) {
		this.replyList = replyList;
	}

	public PostCommentInfoPO(String id, String commentContent, String commentUserName, Date commentDate, String status) {
		super();
		this.id = id;
		this.commentContent = commentContent;
		this.commentUserName = commentUserName;
		this.commentDate = commentDate;
		this.status = status;
	}

	public PostCommentInfoPO() {
		super();
	}

}
