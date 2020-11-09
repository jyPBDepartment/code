package com.jy.pc.VO;

import java.util.List;

//考试结果类(问题部分)
public class ExamReturnQueVO {
	private int queType;
	private int queNum;
	private int queScore;
	private String correctAnswers;
	private String queName;
	private int queIsCorrect;
	private List<ExamReturnAnsVO> queAnalysis;

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
