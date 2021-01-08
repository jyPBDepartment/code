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

//粮食买卖评论表
@Entity
@Table(name = "sas_grain_trading_comment")
public class GrainTradingCommentEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id
	@Column(columnDefinition = "varchar(36) default '' comment '关联粮食买卖Id'")
	private String aid;
	@Column(columnDefinition = "varchar(255) default '' comment '评论内容'")
	private String commentContent;
	@Column(columnDefinition = "varchar(36) default '' comment '评论人'")
	private String commentUserName;// 通过本地缓存获取用户昵称
	@Column(columnDefinition = "varchar(255) default '' comment '评论人头像路径'")
	private String commentPic;// 通过本地缓存获取图片路径
	@Column(columnDefinition = "varchar(36) default '' comment '评论人ID'")
	private String commentUserId;// 通过本地缓存获取图片路径
	@Column(columnDefinition = "int(1) default 0 comment '匿名0否1是，默认为0'")
	private int isAnonymous;
	@Column(columnDefinition = "int(1) default 1 comment '0正常 1审核中 -1已删除，默认1'")
	private int status;
	@Column(columnDefinition = "datetime comment '评论时间'")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date commentDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
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

	public String getCommentPic() {
		return commentPic;
	}

	public void setCommentPic(String commentPic) {
		this.commentPic = commentPic;
	}

	public String getCommentUserId() {
		return commentUserId;
	}

	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}

	public int getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(int isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

}
