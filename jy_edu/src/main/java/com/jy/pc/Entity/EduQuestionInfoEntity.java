package com.jy.pc.Entity;

import java.util.Date;
import java.util.List;

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
 * 试题信息表
 */
@Entity
@Table(name = "edu_question_info")
public class EduQuestionInfoEntity {
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
	@Column(columnDefinition = "int(5) default 0 comment '分数'")
	private int score;
	@Column(columnDefinition = "int(1) default 0 comment '0单选题1判断题'")
	private int quType;
	@Column(columnDefinition = "varchar(5) default '' comment '选择题答案简写（即ABCD）判断题1正确0错误'")
	private String answer;
	@Column(columnDefinition = "varchar(255) default '' comment '问题描述'")
	private String quContent;
	@OneToOne
	@JoinColumn(name = "vocation_id", columnDefinition = "varchar(36) comment '职业类别ID'")
	private EduVocationInfoEntity vocation;
	@Transient
	private String vocationId;
	@Transient
	private List<EduOptionInfoEntity> optionList;

	public EduVocationInfoEntity getVocation() {
		return vocation;
	}

	public void setVocation(EduVocationInfoEntity vocation) {
		this.vocation = vocation;
	}

	public String getVocationId() {
		return vocationId;
	}

	public void setVocationId(String vocationId) {
		this.vocationId = vocationId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<EduOptionInfoEntity> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<EduOptionInfoEntity> optionList) {
		this.optionList = optionList;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getQuType() {
		return quType;
	}

	public void setQuType(int quType) {
		this.quType = quType;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuContent() {
		return quContent;
	}

	public void setQuContent(String quContent) {
		this.quContent = quContent;
	}

}
