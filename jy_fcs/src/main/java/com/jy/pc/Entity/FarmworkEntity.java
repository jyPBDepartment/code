package com.jy.pc.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
//农服预约表
@Entity
@Table(name="sas_farmwork_appointment_info")
public class FarmworkEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length=36)
	private String id;//主键id
	@Column(length=12)
	private String operateUser;//预约人
	@Column(length=255)
	private String ClassiOperateType;//农服信息ID(分类表编码)
	@Column(length=36)
	private String operateType;//农服信息ID(分类表编码)
	@Column(length=2)
	private String status;//状态（0.预约1.完成）
	@Column
	private Date beginDate; //干活开始时间
	@Column
	private Date endDate;   //干活结束时间
	@Column
	private String area;     //面积
	@Column
	private String workArea;  //干活地点
	@Column(length=36)
	private double workPrice;   //农活价格
	@Column
	private String contactUser; //联系人
	@Column
	private String contactPhone; //联系方式
	
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getWorkArea() {
		return workArea;
	}
	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}
	public double getWorkPrice() {
		return workPrice;
	}
	public void setWorkPrice(double workPrice) {
		this.workPrice = workPrice;
	}
	public String getContactUser() {
		return contactUser;
	}
	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClassiOperateType() {
		return ClassiOperateType;
	}
	public void setClassiOperateType(String classiOperateType) {
		ClassiOperateType = classiOperateType;
	}
	
	
}
