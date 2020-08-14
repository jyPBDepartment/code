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

//权限信息表
@Entity
@Table(name="sas_power_info")
public class PowerInfoEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length=36)
	private String id;//主键id
	@Column(length=255)
	private String jurName;//权限名称
	@Column(length=255)
	private String jurCode;//权限编码
	@Column(length=255)
	private String subJurCode;//上级权限编码
	@Column(length=1)
	private String auditStatus;//状态（0禁用1启用）
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
	private Date createDate;//创建时间
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;//修改时间
	@Column(length=255)
	private String createUser;//创建人
	@Column(length=255)
	private String updateUser;//修改人
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJurName() {
		return jurName;
	}
	public void setJurName(String jurName) {
		this.jurName = jurName;
	}
	public String getJurCode() {
		return jurCode;
	}
	public void setJurCode(String jurCode) {
		this.jurCode = jurCode;
	}
	public String getSubJurCode() {
		return subJurCode;
	}
	public void setSubJurCode(String subJurCode) {
		this.subJurCode = subJurCode;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	
}
