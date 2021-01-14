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

//看图识病收藏表
@Entity
@Table(name="sas_case_info_collection")
public class CaseInfoCollectionEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length=36)
	private String id;//主键id
	@Column(length=128)
	private String collectionUserId; //收藏用户id
	@ManyToOne(optional = false)
	@JoinColumn(name = "caseId", referencedColumnName = "id")
	@NotFound(action = NotFoundAction.IGNORE)
	private CaseInfoEntity caseInfoEntity; //看图识病id 外键
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
	private Date collectionDate;//收藏时间
	@Transient
	private String caseId; // 看图识病id
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCollectionUserId() {
		return collectionUserId;
	}
	public void setCollectionUserId(String collectionUserId) {
		this.collectionUserId = collectionUserId;
	}
	public CaseInfoEntity getCaseInfoEntity() {
		return caseInfoEntity;
	}
	public void setCaseInfoEntity(CaseInfoEntity caseInfoEntity) {
		this.caseInfoEntity = caseInfoEntity;
	}
	public Date getCollectionDate() {
		return collectionDate;
	}
	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
}
