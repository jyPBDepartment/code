package com.jy.pc.VO;

import java.util.List;

//考试交卷VO
public class ExamResultVO {
	// 用户ID
	private String userId;
	// 试卷ID
	private String examId;
	// 职业类别ID
	private String vocationId;
	// 职业类别（考试）ID
	private String studyExamationId;
	// 答案集合
	private List<AnswerVO> answerList;

	public String getUserId() {
		return userId;
	}

	public String getVocationId() {
		return vocationId;
	}

	public void setVocationId(String vocationId) {
		this.vocationId = vocationId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getStudyExamationId() {
		return studyExamationId;
	}

	public void setStudyExamationId(String studyExamationId) {
		this.studyExamationId = studyExamationId;
	}

	public List<AnswerVO> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<AnswerVO> answerList) {
		this.answerList = answerList;
	}

}
