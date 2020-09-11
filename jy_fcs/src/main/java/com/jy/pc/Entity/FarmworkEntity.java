package com.jy.pc.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

//农服预约表
@Entity
@Table(name = "sas_farmwork_appointment_info")
public class FarmworkEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id
	@Column(length = 36)
	private String agriculturalId;//外键，农服信息id
	@Column(length = 12)
	private String bookingPerson;// 预约人
	@Column(length = 12)
	private String operateUserId;// 预约人ID
	@Column(length = 255)
	private String ClassiOperateType;// 分类的id
	@Column(length = 36)
	private String operateType;// 分类编码
	@Column(length = 2)
	private String status;// 状态（0.待确认1.已确认2.已完成3已取消）
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	
	private Date beginDate; // 干活开始时间
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate; // 干活结束时间
	@Column
	private String area; // 面积
	@Column
	private String workArea; // 干活地点
	@Column(length = 36)
	private double workPrice; // 农活价格
	@Column(length = 36)
	private String days; // 干活天数
	@Column
	private String contactUser; // 联系人
	@Column
	private String contactPhone; // 联系方式
	@Column
	private String url; // 图片
	@Column
	private String reason; // 原因
	@Transient
	private String agriName;
	
	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAgriName() {
		return agriName;
	}

	public void setAgriName(String agriName) {
		this.agriName = agriName;
	}

	public String getAgriculturalId() {
		return agriculturalId;
	}

	public void setAgriculturalId(String agriculturalId) {
		this.agriculturalId = agriculturalId;
	}

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

	public String getOperateUserId() {
		return operateUserId;
	}

	public void setOperateUserId(String operateUserId) {
		this.operateUserId = operateUserId;
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

	public String getBookingPerson() {
		return bookingPerson;
	}

	public void setBookingPerson(String bookingPerson) {
		this.bookingPerson = bookingPerson;
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
