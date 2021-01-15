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

//看图识病评论表
@Entity
@Table(name="sas_case_info_comment")
public class CaseInfoCommentEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length=36)
	private String id;//主键id
	@Column(columnDefinition = "varchar(255) default '' comment '评论内容'")
	private String commentContent; //评论内容
	@Column(columnDefinition = "varchar(255) default '' comment '评论人昵称'")
	private String commentUserName; //评论人昵称
	@Column(columnDefinition = "varchar(255) default '' comment '评论人头像路径'")
	private String commentUserPic; //评论人头像路径
	@Column(columnDefinition = "varchar(255) default '' comment '评论人id'")
	private String commentUserId; //评论人id
	@Column(columnDefinition = "int(1) default 0 comment '是否匿名1是0否")
	private int isAnonymous; //是否匿名
	@Column(columnDefinition = "datetime comment '评论时间'")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date commentDate; //评论时间
	@Column(columnDefinition = "int(1) default 1 comment '0启用1禁用'")
	private int status; //状态
	@ManyToOne(optional = false)
	@JoinColumn(name = "caseId", referencedColumnName = "id")
	@NotFound(action = NotFoundAction.IGNORE)
	private CaseInfoEntity caseInfoEntity; //看图识病id 外键
	@Transient
	private String caseId;  // 看图识病id
	@Transient
	private String cropsTypeCode; // 农作物种类
	@Transient
	private String dipTypeCode;  //病虫害种类

	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getCropsTypeCode() {
		return cropsTypeCode;
	}
	public void setCropsTypeCode(String cropsTypeCode) {
		this.cropsTypeCode = cropsTypeCode;
	}
	public String getDipTypeCode() {
		return dipTypeCode;
	}
	public void setDipTypeCode(String dipTypeCode) {
		this.dipTypeCode = dipTypeCode;
	}
	public CaseInfoEntity getCaseInfoEntity() {
		return caseInfoEntity;
	}
	public void setCaseInfoEntity(CaseInfoEntity caseInfoEntity) {
		this.caseInfoEntity = caseInfoEntity;
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
	public String getCommentUserPic() {
		return commentUserPic;
	}
	public void setCommentUserPic(String commentUserPic) {
		this.commentUserPic = commentUserPic;
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
	
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
}
