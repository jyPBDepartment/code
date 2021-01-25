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

//粮食买卖回复信息表
@Entity
@Table(name = "sas_grain_trading_reply")
public class GrainTradingReplyEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id
	@Column(columnDefinition = "varchar(36) default '' comment '关联粮食买卖评论id'")
	private String commentId;
	@Column(columnDefinition = "varchar(255) default '' comment '回复内容'")
	private String replyContent;
	@Column(columnDefinition = "varchar(36) default '' comment '回复人'")
	private String replyUserName;// 通过本地缓存获取用户昵称
	@Column(columnDefinition = "varchar(36) default '' comment '被回复人昵称'")
	private String receiveUserName;
	@Column(columnDefinition = "varchar(36) default '' comment '被回复人ID'")
	private String receiveUserId;
	@Column(columnDefinition = "varchar(255) default '' comment '回复人头像路径'")
	private String replyPic;// 通过本地缓存获取图片路径
	@Column(columnDefinition = "varchar(36) default '' comment '回复人ID'")
	private String replyUserId;// 通过本地缓存获取图片路径
	@Column(columnDefinition = "int(1) default 0 comment '匿名0否1是，默认为0'")
	private int isAnonymous;
	@Column(columnDefinition = "varchar(2) default 1 comment '0正常 1审核中 -1已删除，默认1'")
	private String status;
	@Column(columnDefinition = "datetime comment '回复时间'")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date replyDate;

	public String getReceiveUserName() {
		return receiveUserName;
	}

	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getReplyContent() {
		return replyContent;
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

	public String getReplyPic() {
		return replyPic;
	}

	public void setReplyPic(String replyPic) {
		this.replyPic = replyPic;
	}

	public String getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(String replyUserId) {
		this.replyUserId = replyUserId;
	}

	public int getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(int isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

}
