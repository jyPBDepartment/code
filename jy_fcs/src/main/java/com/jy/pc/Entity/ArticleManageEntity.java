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
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 文章管理表
 */
@Entity
@Table(name = "sas_article_manage")
public class ArticleManageEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id
	@Column(columnDefinition = "varchar(36) default '' comment '文章标题'")
	private String title;
	@Column(columnDefinition = " text comment '文章概述富文本'")
	private String content;
	@Column(columnDefinition = " text comment '文章危害富文本'")
	private String contentA;
	@Column(columnDefinition = " text comment '文章传播途径/发病条件富文本'")
	private String contentB;
	@Column(columnDefinition = " text comment '文章防治技术富文本'")
	private String contentC;
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
	@Column(columnDefinition = "varchar(255) default '' comment '主图图片路径'")
	private String url;
	@Column(columnDefinition = "int(1) default 1 comment '0启用1禁用'")
	private int status;
	@OneToOne
	@JoinColumn(name = "section_id", columnDefinition = "varchar(36) comment '版块ID'")
	private SectionManageEntity section;
	@Transient
	private String sectionId;
	@Column(columnDefinition = "int(11) default 0 comment '收藏数，根据用户收藏表自动更新，默认0'")
	private int collectionNum;
	@Column(columnDefinition = "int(11) default 0 comment '评论数，根据用户评论表自动更新，默认0'")
	private int commentNum;
	@Column(columnDefinition = "int(11) default 0 comment '点赞数，根据用户点赞表自动更新，默认0'")
	private int praiseNum;
	@Column(columnDefinition = "int(1) default 0 comment '是否精选1是0否，默认0'")
	private int isSelected;
	@Column(columnDefinition = "int(11) default 0 comment '浏览数，根据用户点赞表自动更新，默认0'")
	private int viewNum;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date selectTime;

	public SectionManageEntity getSection() {
		return section;
	}

	public void setSection(SectionManageEntity section) {
		this.section = section;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCollectionNum() {
		return collectionNum;
	}

	public void setCollectionNum(int collectionNum) {
		this.collectionNum = collectionNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

	public int getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(int isSelected) {
		this.isSelected = isSelected;
	}

	public int getViewNum() {
		return viewNum;
	}

	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}

	public String getContentA() {
		return contentA;
	}

	public void setContentA(String contentA) {
		this.contentA = contentA;
	}

	public String getContentB() {
		return contentB;
	}

	public void setContentB(String contentB) {
		this.contentB = contentB;
	}

	public String getContentC() {
		return contentC;
	}

	public void setContentC(String contentC) {
		this.contentC = contentC;
	}

	public Date getSelectTime() {
		return selectTime;
	}

	public void setSelectTime(Date selectTime) {
		this.selectTime = selectTime;
	}
	
	
	
}
