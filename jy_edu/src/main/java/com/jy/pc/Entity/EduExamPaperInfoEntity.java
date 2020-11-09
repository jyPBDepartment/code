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
 * 试卷信息表
 * */
@Entity
@Table(name = "edu_exam_paper_info")
public class EduExamPaperInfoEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id
	@Column(columnDefinition = "varchar(255) default '' comment '试卷名称'")
	private String name;
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
	@Column(columnDefinition = "DECIMAL(10,1) default '' comment '总分值'")
	private Double totalScore;
	@Column(columnDefinition = "DECIMAL(10,1) default '' comment '及格分数'")
	private Double passScore;
	@Column(columnDefinition = "int(4) default '' comment '答题时间'")
	private int answerTime;
	@Column(columnDefinition = "int(10) comment '试题数量'")
	private int questionNum;
	@OneToOne
	@JoinColumn(name = "vocation_id", columnDefinition = "varchar(36) comment '职业类别ID'")
	private EduVocationInfoEntity vocation;
	@Transient
	private String vocationId;
	
	public int getQuestionNum() {
		return questionNum;
	}
	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
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
	public Double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}
	public Double getPassScore() {
		return passScore;
	}
	public void setPassScore(Double passScore) {
		this.passScore = passScore;
	}
	public int getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(int answerTime) {
		this.answerTime = answerTime;
	}
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
	
}
