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
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

//农服信息表
@Entity
@Table(name = "sas_publication_info")
public class AgriculturalEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id
	@Column(length = 255)
	private String name;// 标题名称
	@Column
	private String descrip;// 描述
	@Column(length = 8)
	private String transactionTypeCode;// 农服交易类型
	@Column(length = 8)
	private String transactionCategoryCode;// 农服交易类别
	@Column(length = 255)
	private float price;// 收购价格
	@Column(length = 255)
	private String address;// 区域
	@Column(length = 255)
	private String contactsUser;// 联系人
	@Column(length = 11)
	private String contactsPhone;// 联系方式
	@Column
	private String url;// 图片
	@Column(length = 255)
	private String classiCode;// 机器类型
	@Column(length = 8)
	private String machineType;// 机器类型
	@Column(length = 255)
	private String model;// 型号
	@Column(length = 255)
	private String articleNumber;// 货号
	@Column(length = 255)
	private String labelCode;// 标签编码
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date purchaseDate;// 购买时间
	@Column(length = 1)
	private String status;// 状态(0:待审核,1:审核通过,2:审核拒绝)
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;// 发布时间
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;// 修改时间
	@Column(length = 255)
	private String createUser;// 发布人
	@Column(length = 255)
	private String updateUser;// 审核人
	@Column(length = 255)
	private String examineReason;// 审核拒绝理由
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginDate; // 干活开始时间
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate; // 干活结束时间
	@Column
	private String days; // 天数
	@Column
	private String machineNum; // 农机台数
	@Column(length = 1)
	private String isFace; // 是否面议(0是1否)
	@Column(length = 1)
	private String farmingMode; // 农活类型(0整活1零活)
	@Column(length = 36)
	private String createUserId; // 客户id
	@Column(length = 1)
	private String identityCode; // 身份标识码 1.农户 2.粮贩 3.粮库
	@Column(length = 255)
	private String reason; // 取消发布理由
	@Column(length = 36)
	private String accId; // 云信accId
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

	public int getViewNum() {
		return viewNum;
	}

	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
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

	public String getAccId() {
		return accId;
	}

	public void setAccId(String accId) {
		this.accId = accId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getIdentityCode() {
		return identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getMachineNum() {
		return machineNum;
	}

	public void setMachineNum(String machineNum) {
		this.machineNum = machineNum;
	}

	public String getIsFace() {
		return isFace;
	}

	public void setIsFace(String isFace) {
		this.isFace = isFace;
	}

	public String getFarmingMode() {
		return farmingMode;
	}

	public void setFarmingMode(String farmingMode) {
		this.farmingMode = farmingMode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getTransactionTypeCode() {
		return transactionTypeCode;
	}

	public void setTransactionTypeCode(String transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}

	public String getTransactionCategoryCode() {
		return transactionCategoryCode;
	}

	public void setTransactionCategoryCode(String transactionCategoryCode) {
		this.transactionCategoryCode = transactionCategoryCode;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactsUser() {
		return contactsUser;
	}

	public void setContactsUser(String contactsUser) {
		this.contactsUser = contactsUser;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	public String getLabelCode() {
		return labelCode;
	}

	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getExamineReason() {
		return examineReason;
	}

	public void setExamineReason(String examineReason) {
		this.examineReason = examineReason;
	}

	public String getClassiCode() {
		return classiCode;
	}

	public void setClassiCode(String classiCode) {
		this.classiCode = classiCode;
	}
}
