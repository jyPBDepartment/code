package com.jy.pc.VO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jy.pc.Entity.EduLessonStudentRelationEntity;

//课程报名列表页用VO
public class LessonEnrollVO {
	// 关联表ID
	private String id;
	// 用户ID
	private String userId;
	// 课程时间
	private String lessonTime;
	// 职业类别名称
	private String vocationName;
	// 课程ID
	private String lessonId;
	// 课程名称
	private String lessonName;
	// 上课地址
	private String address;
	// 课程状态0未开始1已开始2已结束
	private int status;
	// 课程本身状态0启用1禁用
	private int lessonEnableState;
	// 用户姓名
	private String userName;

	public LessonEnrollVO(EduLessonStudentRelationEntity entity) throws ParseException {
		super();
		this.id = entity.getId();
		this.userId = entity.getUserCode();
		this.lessonTime = entity.getLesson().getLessonDate();
		this.vocationName = entity.getLesson().getVocation().getName();
		this.lessonId = entity.getLesson().getId();
		this.lessonName = entity.getLesson().getTitle();
		this.address = entity.getLesson().getAddress();
		this.lessonEnableState = entity.getLesson().getStatus();
		this.userName = entity.getUserName();
		// 以课程时间进行判断
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String lessonDay = dayFormat.format(entity.getLesson().getLessonDay());
		String beginDate = lessonDay + " " + entity.getLesson().getBeginDate();
		String endDate = lessonDay + " " + entity.getLesson().getEndDate();
		Date begin = dateFormat.parse(beginDate);
		Date end = dateFormat.parse(endDate);
		Date now = new Date();
		if (now.before(begin)) {
			// 当前时间在课程开始之前
			this.status = 0;
		}
		if (now.after(begin) && now.before(end)) {
			// 当前时间在课程中
			this.status = 1;
		}
		if (now.after(end)) {
			// 当前时间在课程结束之后1
			this.status = 2;
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getLessonEnableState() {
		return lessonEnableState;
	}

	public void setLessonEnableState(int lessonEnableState) {
		this.lessonEnableState = lessonEnableState;
	}

	public LessonEnrollVO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLessonTime() {
		return lessonTime;
	}

	public void setLessonTime(String lessonTime) {
		this.lessonTime = lessonTime;
	}

	public String getVocationName() {
		return vocationName;
	}

	public void setVocationName(String vocationName) {
		this.vocationName = vocationName;
	}

	public String getLessonId() {
		return lessonId;
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
