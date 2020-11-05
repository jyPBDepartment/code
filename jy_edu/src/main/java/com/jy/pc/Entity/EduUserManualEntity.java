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
 * 用户手册关联表
 * @author Stephen
 * 
 * */
@Entity
@Table(name = "edu_user_manual")
public class EduUserManualEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	private String id;// 主键Id
	@Column(columnDefinition = "varchar(36) default '' comment '手册表Id'")
	private String manualInfoId;// 手册表Id
	@Column(columnDefinition = "varchar(36) default '' comment '用户Id'")
	private String userId;// 用户Id
	@Column(columnDefinition = "int(1) default 0 comment '0否1是，默认为0'")
	private int isCollection;// 是否收藏
	@Column(columnDefinition = "datetime comment '收藏时间'")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date collectionDate;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getManualInfoId() {
		return manualInfoId;
	}
	public void setManualInfoId(String manualInfoId) {
		this.manualInfoId = manualInfoId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getIsCollection() {
		return isCollection;
	}
	public void setIsCollection(int isCollection) {
		this.isCollection = isCollection;
	}
	public Date getCollectionDate() {
		return collectionDate;
	}
	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

}
