package com.jy.pc.VO;

import com.jy.pc.Entity.EduLessonInfoEntity;

//课程列表页用VO
public class LessonListVO {
	// 主键ID
	private String id;
	// 标题
	private String title;
	// 主图路径
	private String url;
	// 课程时间
	private String lessonTime;

	public LessonListVO(EduLessonInfoEntity entity) {
		super();
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.url = entity.getUrl();
		this.lessonTime = entity.getLessonDate();
	}

	public LessonListVO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLessonTime() {
		return lessonTime;
	}

	public void setLessonTime(String lessonTime) {
		this.lessonTime = lessonTime;
	}

}
