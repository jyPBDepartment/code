package com.jy.pc.VO;

//考试结果类(答案部分)
public class ExamReturnAnsVO {
	// 选项（ABCD）
	private String queIndex;
	// 答案描述
	private String queSelectAnswer;
	// 用户选择
	private String queChoose;

	public String getQueChoose() {
		return queChoose;
	}

	public void setQueChoose(String queChoose) {
		this.queChoose = queChoose;
	}

	public String getQueIndex() {
		return queIndex;
	}

	public void setQueIndex(String queIndex) {
		this.queIndex = queIndex;
	}

	public String getQueSelectAnswer() {
		return queSelectAnswer;
	}

	public void setQueSelectAnswer(String queSelectAnswer) {
		this.queSelectAnswer = queSelectAnswer;
	}

}
