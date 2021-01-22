package com.jy.pc.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 回复管理
 */
@Entity
@Table(name = "sas_comment_reply_info")
public class CommentReplyInfoEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	// 主键id
	private String id;

	// 回复内容
	@Column(columnDefinition = "varchar(255) default '' comment '帖子Id'")
	private String commentId;
	// 回复内容
	@Column
	private String replyContent;
	// 回复人
	@Column
	private String replyUserName;
	// 回复人ID
	@Column
	private String replyUserId;
	// 回复时间
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date replyDate;
	// 状态 - 1生效0系统禁用
	@Column(length = 10)
	private String status;

	@Column(columnDefinition = "varchar(255) default '' comment '回复人头像路径'")
	private String replyPic; // 回复人头像路径

	@Column(columnDefinition = "int(2) default 0 comment '是否匿名 '")
	private int isAnonymous; // 是否匿名，0否1是，默认0

	// 被回复人
	@Column
	private String receiveUserName;
	
	// 被回复人ID
	@Column
	private String receiveUserId;
	
	
	public String getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public String getReceiveUserName() {
		return receiveUserName;
	}

	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}

	public String getReplyPic() {
		return replyPic;
	}

	public void setReplyPic(String replyPic) {
		this.replyPic = replyPic;
	}

	public int getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(int isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public String getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(String replyUserId) {
		this.replyUserId = replyUserId;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyUserName() {
		return replyUserName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
