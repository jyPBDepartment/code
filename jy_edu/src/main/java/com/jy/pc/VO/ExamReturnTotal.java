package com.jy.pc.VO;

import java.util.List;

//考试结果类
public class ExamReturnTotal {
	// 统计部分
	private List<ExamReturnTableVO> examTableInfo;
	// 试题及答案部分
	private List<ExamReturnQueVO> examTableList;

	public List<ExamReturnTableVO> getExamTableInfo() {
		return examTableInfo;
	}

	public void setExamTableInfo(List<ExamReturnTableVO> examTableInfo) {
		this.examTableInfo = examTableInfo;
	}

	public List<ExamReturnQueVO> getExamTableList() {
		return examTableList;
	}

	public void setExamTableList(List<ExamReturnQueVO> examTableList) {
		this.examTableList = examTableList;
	}

}
