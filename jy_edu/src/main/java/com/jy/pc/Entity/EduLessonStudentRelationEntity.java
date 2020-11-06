package com.jy.pc.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@OneToOne
	@JoinColumn(name = "lesson_id", columnDefinition = "varchar(36) comment '课程ID'")
	private EduLessonInfoEntity lesson;
	@OneToOne
	@JoinColumn(name = "user_code", columnDefinition = "varchar(36) comment '客户ID'")
	private SysLocalUserEntity user;
	@Column(columnDefinition = "varchar(36) comment '客户id'")
	private String userName;
	@Column(columnDefinition = "varchar(36) comment '客户联系方式'")
	private String userTel;
	@Column(columnDefinition = "datetime comment '创建时间'")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EduLessonInfoEntity getLesson() {
		return lesson;
	}

	public void setLesson(EduLessonInfoEntity lesson) {
		this.lesson = lesson;
	}

	public SysLocalUserEntity getUser() {
		return user;
	}

	public void setUser(SysLocalUserEntity user) {
		this.user = user;
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
