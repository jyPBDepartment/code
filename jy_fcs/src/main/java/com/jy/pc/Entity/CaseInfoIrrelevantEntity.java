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

//看图识病与我无关表
@Entity
@Table(name="sas_case_info_irrelevant")
public class CaseInfoIrrelevantEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length=36)
	private String id;//主键id
	@Column(length=128)
	private String irrelevantnUserId; //用户id
	@ManyToOne(optional = false)
	@JoinColumn(name = "caseId", referencedColumnName = "id")
	@NotFound(action = NotFoundAction.IGNORE)
	private CaseInfoEntity caseInfoEntity; //看图识病id 外键
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
	private Date irrelevantDate;//时间
	@Transient
	private String caseId; // 看图识病id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIrrelevantnUserId() {
		return irrelevantnUserId;
	}
	public void setIrrelevantnUserId(String irrelevantnUserId) {
		this.irrelevantnUserId = irrelevantnUserId;
	}
	public CaseInfoEntity getCaseInfoEntity() {
		return caseInfoEntity;
	}
	public void setCaseInfoEntity(CaseInfoEntity caseInfoEntity) {
		this.caseInfoEntity = caseInfoEntity;
	}
	public Date getIrrelevantDate() {
		return irrelevantDate;
	}
	public void setIrrelevantDate(Date irrelevantDate) {
		this.irrelevantDate = irrelevantDate;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
}
