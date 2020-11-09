package com.jy.pc.VO;

public class AnswerVO {
	// 用户答案
	private String answerCode;
	// 试题ID
	private String answerId;
	// 序号
	private int serialNumber;

	public String getAnswerCode() {
		return answerCode;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setAnswerCode(String answerCode) {
		this.answerCode = answerCode;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

}
