package com.jy.pc.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

//看图识病表
@Entity
@Table(name="sas_case_info")
public class CaseInfoEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length=36)
	private String id;//主键id
	@Column(length=255)
	private String name;//名称
	@Column
	private String url;//图片
	@Column(length=36)
	private String classiCode;//农作物种类编码分类
	@Column
	private String cropsTypeCode;//农作物种类编码
	@Column(length=36)
	private String dipTypeCode;//病虫害种类编码
	@Column
	private String classiDipCode;//农作物种类编码分类
	@Column(columnDefinition="TEXT")
	private String describetion;//描述
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
	@Transient
	@ElementCollection(targetClass=KeyWordEntity.class)
	private List<KeyWordEntity> keyCodes;//关键字关联
	@Column(columnDefinition = "int(10) default 0 comment '收藏数'")
	private int collectionNum; //收藏数量
	@Column(columnDefinition = "int(10) default 0 comment '评论数'")
	private int commentNum;  //评论数量
	@Column(columnDefinition = "int(10) default 0 comment '点赞数'")
	private int praiseNum;   //点赞数量
	@Column(columnDefinition = "int(5) default 0 comment '是否精选1是0否'")
	private int isSelected;  //是否精选
	@Column(columnDefinition = "int(10) default 0 comment '差评数量'")
	private int negativeNum;  //差评数量
	@Column(columnDefinition = "int(20) default 0 comment '浏览数'")
	private int browseNum;  //浏览数量
	@Column(columnDefinition = " text comment '危害富文本'")
	private String harm; //危害
	@Column(columnDefinition = " text comment '传播途径/发病条件富文本'")
	private String channel; //传播途径/发病条件
	@Column(columnDefinition = " text comment '防治技术富文本'")
	private String controlTechnology; //防治技术
	@Column(columnDefinition = "int(20) default 0 comment '与我无关数量'")
	private int irrelevantNum;  //与我无关数量
	public int getIrrelevantNum() {
		return irrelevantNum;
	}
	public void setIrrelevantNum(int irrelevantNum) {
		this.irrelevantNum = irrelevantNum;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public int getNegativeNum() {
		return negativeNum;
	}
	public void setNegativeNum(int negativeNum) {
		this.negativeNum = negativeNum;
	}
	public int getBrowseNum() {
		return browseNum;
	}
	public void setBrowseNum(int browseNum) {
		this.browseNum = browseNum;
	}
	public String getHarm() {
		return harm;
	}
	public void setHarm(String harm) {
		this.harm = harm;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getControlTechnology() {
		return controlTechnology;
	}
	public void setControlTechnology(String controlTechnology) {
		this.controlTechnology = controlTechnology;
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
	public List<KeyWordEntity> getKeyCodes() {
		return keyCodes;
	}
	public void setKeyCodes(List<KeyWordEntity> keyCodes) {
		this.keyCodes = keyCodes;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCropsTypeCode() {
		return cropsTypeCode;
	}
	public void setCropsTypeCode(String cropsTypeCode) {
		this.cropsTypeCode = cropsTypeCode;
	}
	public String getDipTypeCode() {
		return dipTypeCode;
	}
	public void setDipTypeCode(String dipTypeCode) {
		this.dipTypeCode = dipTypeCode;
	}

	public String getDescribetion() {
		return describetion;
	}
	public void setDescribetion(String describetion) {
		this.describetion = describetion;
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
	public String getClassiCode() {
		return classiCode;
	}
	public void setClassiCode(String classiCode) {
		this.classiCode = classiCode;
	}
	public String getClassiDipCode() {
		return classiDipCode;
	}
	public void setClassiDipCode(String classiDipCode) {
		this.classiDipCode = classiDipCode;
	}
	
	
}
