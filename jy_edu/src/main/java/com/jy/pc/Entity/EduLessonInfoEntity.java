package com.jy.pc.Entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 线下课程信息表
 */
@Entity
@Table(name = "edu_lesson_info")
public class EduLessonInfoEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id
	@Column(columnDefinition = "varchar(36) default '' comment '创建人'")
	private String createBy;
	@Column(columnDefinition = "datetime comment '创建时间'")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Column(columnDefinition = "varchar(36) default '' comment '修改人'")
	private String updateBy;
	@Column(columnDefinition = "datetime comment '修改时间'")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	@Column(columnDefinition = "int(1) default 1 comment '状态0启用1禁用'")
	private int status;
	@Column(columnDefinition = "int(1) default 0 comment '报名状态0允许报名1报名结束'")
	private int enrollStatus;
	@OneToOne
	@JoinColumn(name = "vocation_id", columnDefinition = "varchar(36) comment '职业类别ID'")
	private EduVocationInfoEntity vocation;
	@Column(columnDefinition = "varchar(128) comment '课程标题'")
	private String title;
	@Column(columnDefinition = "varchar(255) comment '课程介绍'")
	private String content;
	@Column(columnDefinition = "varchar(255) default '' comment '主图路径'")
	private String url;
	@Column(columnDefinition = "datetime comment '课程日期'")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lessonDay;
	@Column(columnDefinition = "datetime comment '开始时间'")
	@JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginDate;
	@Column(columnDefinition = "datetime comment '结束时间'")
	@JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	@Column(columnDefinition = "int(5) default 30 comment '人数限制'")
	private int stuLimit;
	@Column(columnDefinition = "varchar(255) default '' comment '地址'")
	private String address;
	@Column(columnDefinition = "varchar(255) default '' comment '参加指南'")
	private String remark;
	@Transient
	private String lessonDate;
	@Transient
	private String vocationId;

	public String getVocationId() {
		return vocationId;
	}

	public void setVocationId(String vocationId) {
		this.vocationId = vocationId;
	}

	private String castTime() {
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		return dayFormat.format(lessonDay) + " " + timeFormat.format(beginDate) + "--" + timeFormat.format(endDate);
	}

	public String getLessonDate() {
		return this.castTime();
	}

	public void setLessonDate(String lessonDate) {
		this.lessonDate = lessonDate;
	}

	public Date getLessonDay() {
		return lessonDay;
	}

	public void setLessonDay(Date lessonDay) {
		this.lessonDay = lessonDay;
	}

	public EduVocationInfoEntity getVocation() {
		return vocation;
	}

	public void setVocation(EduVocationInfoEntity vocation) {
		this.vocation = vocation;
	}

	public int getEnrollStatus() {
		return enrollStatus;
	}

	public void setEnrollStatus(int enrollStatus) {
		this.enrollStatus = enrollStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public int getStuLimit() {
		return stuLimit;
	}

	public void setStuLimit(int stuLimit) {
		this.stuLimit = stuLimit;
	}

}
