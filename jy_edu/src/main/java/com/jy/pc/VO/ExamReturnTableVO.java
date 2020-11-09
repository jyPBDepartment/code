package com.jy.pc.VO;

//考试结果类（题型统计部分）
public class ExamReturnTableVO {
	private int type;
	private int queNum;
	private int wrongNum;
	private int getGrade;

	public ExamReturnTableVO() {
		super();
	}

	public ExamReturnTableVO(int type, int queNum, int wrongNum, int getGrade) {
		super();
		this.type = type;
		this.queNum = queNum;
		this.wrongNum = wrongNum;
		this.getGrade = getGrade;
	}

	public void addGetGrade(int num) {
		this.getGrade += num;
	}

	public void addQueNum(int num) {
		this.queNum += num;
	}

	public void addWrongNum(int num) {
		this.wrongNum += num;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getQueNum() {
		return queNum;
	}

	public void setQueNum(int queNum) {
		this.queNum = queNum;
	}

	public int getWrongNum() {
		return wrongNum;
	}

	public void setWrongNum(int wrongNum) {
		this.wrongNum = wrongNum;
	}

	public int getGetGrade() {
		return getGrade;
	}

	public void setGetGrade(int getGrade) {
		this.getGrade = getGrade;
	}

}
