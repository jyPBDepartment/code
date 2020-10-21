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
 *	 图片设置表
 */
@Entity
@Table(name = "edu_picture_info")
public class EduPictureInfoEntity {
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
	@Column(columnDefinition = "int(4) default 50 comment '排序'")
	private int sort;
	@Column(columnDefinition = "int(1) default 1 comment '0启用1禁用'")
	private int status;
	@Column(columnDefinition = "varchar(255) default '' comment '图片路径'")
	private String url;
	@Column(columnDefinition = "int(1) default 0 comment '0轮播图1学习手册2在线考试'")
	private int picType;

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

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPicType() {
		return picType;
	}

	public void setPicType(int picType) {
		this.picType = picType;
	}

}
