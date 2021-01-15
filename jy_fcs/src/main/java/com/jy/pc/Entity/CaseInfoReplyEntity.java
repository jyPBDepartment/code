package com.jy.pc.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;

//看图识病回复表
@Entity
@Table(name = "sas_case_info_reply")
public class CaseInfoReplyEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id
	@Column(columnDefinition = "varchar(255) default '' comment '回复内容'")
	private String replyContent; // 回复内容
	@Column(columnDefinition = "varchar(255) default '' comment '回复人昵称'")
	private String replyUserName; // 回复人昵称
	@Column(columnDefinition = "varchar(255) default '' comment '回复人头像路径'")
	private String replyUserPic; // 回复人头像路径
	@Column(columnDefinition = "varchar(255) default '' comment '回复人id'")
	private String replyUserId; // 回复人id
	@Column(columnDefinition = "int(1) default 0 comment '是否匿名1是0否'")
	private int isAnonymous; // 是否匿名
	@Column(columnDefinition = "datetime comment '回复时间'")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date replyDate; // 回复时间
	@Column(columnDefinition = "int(1) default 1 comment '0启用1禁用'")
	private int status; // 状态
	@ManyToOne(optional = false)
	@JoinColumn(name = "commentId", referencedColumnName = "id")
	@NotFound(action = NotFoundAction.IGNORE)
	private CaseInfoCommentEntity caseInfoCommentEntity; // 关联编号 评论id 外键
	@Transient
	private String commentId; // 评论id

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public CaseInfoCommentEntity getCaseInfoCommentEntity() {
		return caseInfoCommentEntity;
	}

	public void setCaseInfoCommentEntity(CaseInfoCommentEntity caseInfoCommentEntity) {
		this.caseInfoCommentEntity = caseInfoCommentEntity;
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

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyUserName() {
		return replyUserName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}

	public String getReplyUserPic() {
		return replyUserPic;
	}

	public void setReplyUserPic(String replyUserPic) {
		this.replyUserPic = replyUserPic;
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

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
