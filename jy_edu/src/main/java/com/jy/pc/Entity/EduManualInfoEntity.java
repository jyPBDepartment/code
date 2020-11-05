package com.jy.pc.Entity;

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
 * 手册信息表
 */
@Entity
@Table(name = "edu_manual_info")
public class EduManualInfoEntity {
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
	@Column(columnDefinition = "int(1) default 1 comment '0启用1禁用'")
	private int status;
//	@Column(columnDefinition = "varchar(36) comment '职业类别ID'")
//	private String vocationId;
	@OneToOne
	@JoinColumn(name = "vocation_id", columnDefinition = "varchar(36) comment '职业类别ID'")
	private EduVocationInfoEntity vocation;
	@Column(columnDefinition = "varchar(128) comment '手册标题'")
	private String title;
	@Column(columnDefinition = "int(1) comment '0文章1视频'")
	private int manualType;
	@Column(columnDefinition = "int(8) default 0 comment '学习人数'")
	private int studyNum;
	@Column(columnDefinition = " text comment '手册内容富文本'")
	private String content;
	@Column(columnDefinition = "varchar(255) default '' comment '主图图片路径'")
	private String url;
//	@Column(columnDefinition = "varchar(36) default '' comment '标签'")
//	private String labelId;
	@OneToOne
	@JoinColumn(name = "label_id", columnDefinition = "varchar(36) comment '标签ID'")
	private EduManualLabelInfoEntity label;
	@Column(columnDefinition = "varchar(200) default '' comment '导读'")
	private String guide;
	@Transient
	private String vocationId;
	@Transient
	private String labelId;

	public int getStudyNum() {
		return studyNum;
	}

	public void setStudyNum(int studyNum) {
		this.studyNum = studyNum;
	}

	public EduVocationInfoEntity getVocation() {
		return vocation;
	}

	public void setVocation(EduVocationInfoEntity vocation) {
		this.vocation = vocation;
	}

	public EduManualLabelInfoEntity getLabel() {
		return label;
	}

	public void setLabel(EduManualLabelInfoEntity label) {
		this.label = label;
	}

//	public String getVocationName() {
//		return vocationName;
//	}
//
//	public void setVocationName(String vocationName) {
//		this.vocationName = vocationName;
//	}
//
//	public String getLabelName() {
//		return labelName;
//	}
//
//	public void setLabelName(String labelName) {
//		this.labelName = labelName;
//	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
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

	public String getVocationId() {
		return vocationId;
	}

	public void setVocationId(String vocationId) {
		this.vocationId = vocationId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getManualType() {
		return manualType;
	}

	public void setManualType(int manualType) {
		this.manualType = manualType;
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

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

}
