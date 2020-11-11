package com.jy.pc.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 证书颁发表
 */
@Entity
@Table(name = "edu_issue_info")
public class EduIssueInfoEntity extends BaseEntity {
	@Column(columnDefinition = "varchar(36) default '' comment '用户ID'")
	private String userId;
	@Column(columnDefinition = "varchar(36) default '' comment '用户联系方式'")
	private String userTel;
	@Column(columnDefinition = "varchar(36) default '' comment '用户身份证号'")
	private String userCard;
	@Column(columnDefinition = "varchar(36) default '' comment '用户姓名'")
	private String userName;
	@OneToOne
	@JoinColumn(name = "certificate_id", columnDefinition = "varchar(36) comment '证书ID'", referencedColumnName = "id")
	@NotFound(action = NotFoundAction.IGNORE)
	private EduCertificateInfoEntity certificate;
	@Column(columnDefinition = "datetime comment '颁发时间'")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date issueDate;
	@Column(columnDefinition = "int(1) default 0 comment '处理状态0待处理1已完成'")
	private int issueState;
	@Column(columnDefinition = "varchar(36) default '' comment '处理人'")
	private String issueBy;
	@OneToOne
	@JoinColumn(name = "formwork_id", columnDefinition = "varchar(36) comment '模板ID'", referencedColumnName = "id")
	@NotFound(action = NotFoundAction.IGNORE)
	private EduFormworkEntity eduFormworkEntity;
	@Column(columnDefinition = "varchar(36) default '' comment '证书图片路径'")
	private String url;
	@Transient
	private String certificateId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}

	public EduFormworkEntity getEduFormworkEntity() {
		return eduFormworkEntity;
	}

	public void setEduFormworkEntity(EduFormworkEntity eduFormworkEntity) {
		this.eduFormworkEntity = eduFormworkEntity;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIssueBy() {
		return issueBy;
	}

	public void setIssueBy(String issueBy) {
		this.issueBy = issueBy;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public int getIssueState() {
		return issueState;
	}

	public void setIssueState(int issueState) {
		this.issueState = issueState;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserCard() {
		return userCard;
	}

	public void setUserCard(String userCard) {
		this.userCard = userCard;
	}

	public EduCertificateInfoEntity getCertificate() {
		return certificate;
	}

	public void setCertificate(EduCertificateInfoEntity certificate) {
		this.certificate = certificate;
	}

}
