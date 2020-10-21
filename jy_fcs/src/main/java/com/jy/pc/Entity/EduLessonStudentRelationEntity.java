package com.jy.pc.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 线下课程报名表
 */
@Entity
@Table(name = "edu_lesson_student_relation")
public class EduLessonStudentRelationEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id

	@Column(columnDefinition = "varchar(36) comment '课程ID'")
	private String lessonId;
	@Column(columnDefinition = "varchar(36) comment '客户代码'")
	private String userCode;
	@Column(columnDefinition = "varchar(36) comment '客户名称'")
	private String userName;
	@Column(columnDefinition = "varchar(36) comment '客户联系方式'")
	private String userTel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLessonId() {
		return lessonId;
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

}
