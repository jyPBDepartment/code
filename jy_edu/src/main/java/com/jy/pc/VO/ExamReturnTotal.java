package com.jy.pc.VO;

import java.util.List;

//考试结果类
public class ExamReturnTotal {
	private List<ExamReturnTableVO> examTableInfo;
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
