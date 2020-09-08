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

//字典码值表
@Entity
@Table(name = "sas_dict_data")
public class DictDataEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id
	@Column
	private String dictLabel;
	@Column
	private String dictType;
	@Column
	private String dictValue;
	@Column
	private int dictSort;
	@Column
	private String labelClass;
	@Column
	private String isDefault;
	@Column
	private String status;
	@Column
	private String remark;
	@Column
	private String createBy;
	@Column
	private String updateBy;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDictLabel() {
		return dictLabel;
	}

	public void setDictLabel(String dictLabel) {
		this.dictLabel = dictLabel;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public int getDictSort() {
		return dictSort;
	}

	public void setDictSort(int dictSort) {
		this.dictSort = dictSort;
	}

	public String getLabelClass() {
		return labelClass;
	}

	public void setLabelClass(String labelClass) {
		this.labelClass = labelClass;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
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

}
