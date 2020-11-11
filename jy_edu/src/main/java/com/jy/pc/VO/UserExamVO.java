package com.jy.pc.VO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserExamVO {

	// 职业类别名称
	private String vocationName;
	// 考试时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date examDate;
	// 试卷名称
	private String examPaperName;
	// 通过分数
	private int passScore;
	// 得分数
	private int score;

	public String getVocationName() {
		return vocationName;
	}

	public void setVocationName(String vocationName) {
		this.vocationName = vocationName;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getExamPaperName() {
		return examPaperName;
	}

	public void setExamPaperName(String examPaperName) {
		this.examPaperName = examPaperName;
	}

	public int getPassScore() {
		return passScore;
	}

	public void setPassScore(int passScore) {
		this.passScore = passScore;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
