package com.jy.pc.VO;

import java.util.List;

//考试结果类(问题部分)
public class ExamReturnQueVO {
	// 类型 0：单选 1：判断
	private int queType;
	// 序号（来自前端）
	private int queNum;
	// 分数
	private int queScore;
	// 正确答案
	private String correctAnswers;
	// 问题描述
	private String queName;
	// 该题是否正确 1：正确 2：错误
	private int queIsCorrect;
	//
	private List<ExamReturnAnsVO> queAnalysis;
	// 用户选择
	private String queChoose;

	public String getQueChoose() {
		return queChoose;
	}

	public void setQueChoose(String queChoose) {
		this.queChoose = queChoose;
	}

	public ExamReturnQueVO() {
		super();
	}

	public ExamReturnQueVO(int queType, int queNum, int queScore, String correctAnswers, String queName,
			int queIsCorrect) {
		super();
		this.queType = queType;
		this.queNum = queNum;
		this.queScore = queScore;
		this.correctAnswers = correctAnswers;
		this.queName = queName;
		this.queIsCorrect = queIsCorrect;
	}

	public int getQueIsCorrect() {
		return queIsCorrect;
	}

	public void setQueIsCorrect(int queIsCorrect) {
		this.queIsCorrect = queIsCorrect;
	}

	public int getQueType() {
		return queType;
	}

	public void setQueType(int queType) {
		this.queType = queType;
	}

	public int getQueNum() {
		return queNum;
	}

	public void setQueNum(int queNum) {
		this.queNum = queNum;
	}

	public int getQueScore() {
		return queScore;
	}

	public void setQueScore(int queScore) {
		this.queScore = queScore;
	}

	public String getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(String correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public String getQueName() {
		return queName;
	}

	public void setQueName(String queName) {
		this.queName = queName;
	}

	public List<ExamReturnAnsVO> getQueAnalysis() {
		return queAnalysis;
	}

	public void setQueAnalysis(List<ExamReturnAnsVO> queAnalysis) {
		this.queAnalysis = queAnalysis;
	}

}
