package com.jy.pc.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 证书颁发表
 */
@Entity
@Table(name = "edu_issue_info")
public class EduIssueInfoEntity extends BaseEntity {
	@OneToOne
	@JoinColumn(name = "user_id", columnDefinition = "varchar(128) comment '用户ID'",referencedColumnName = "id")
	@NotFound(action = NotFoundAction.IGNORE)
	private SysLocalUserEntity user;
	@OneToOne
	@JoinColumn(name = "certificate_id", columnDefinition = "varchar(36) comment '证书ID'",referencedColumnName = "id")
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

	public SysLocalUserEntity getUser() {
		return user;
	}

	public void setUser(SysLocalUserEntity user) {
		this.user = user;
	}

	public EduCertificateInfoEntity getCertificate() {
		return certificate;
	}

	public void setCertificate(EduCertificateInfoEntity certificate) {
		this.certificate = certificate;
	}

}
