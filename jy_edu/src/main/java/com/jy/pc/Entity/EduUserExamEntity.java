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
 * 用户考试成绩关联表
 * 
 * @author Stephen
 * 
 */
@Entity
@Table(name = "edu_user_exam")
public class EduUserExamEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	private String id;// 主键Id
	@Column(columnDefinition = "varchar(36) default '' comment '试卷Id'")
	private String examId;// 试卷Id
	@Column(columnDefinition = "varchar(36) default '' comment '用户Id'")
	private String userId;// 用户Id
	@Column(columnDefinition = "int(3) default 0 comment '考试分数'")
	private int score;// 考试分数
	@Column(columnDefinition = "int(1) default 0 comment '是否通过，0否1是'")
	private int isPass;// 是否通过
	@Column(columnDefinition = "datetime comment '考试时间'")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date examDate;// 考试时间
	@Column(columnDefinition = "varchar(36) default '' comment '职业类别Id'")
	private String vocationId;

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

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getIsPass() {
		return isPass;
	}

	public void setIsPass(int isPass) {
		this.isPass = isPass;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

}
