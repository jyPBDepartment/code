package com.jy.pc.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *	 学生实体类
 * 
 * */
@Entity
@Table(name="student")
public class StudentEntity {

	// 主键ID
	@Id
	@GeneratedValue
	private int id;
	// 名称
	@Column
	private String name;
	// 年龄
	@Column
	private String age;
	// 班级
	@Column
	private String classes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

}
