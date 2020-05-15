package com.jy.pc.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Teacher")
public class TeacherEntity {
	
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String name;
	private int age;
	private String tClass;
	private String wage;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTClass() {
		return tClass;
	}
	public void setTClass(String tClass) {
		this.tClass = tClass;
	}
	public String getWage() {
		return wage;
	}
	public void setWage(String wage) {
		this.wage = wage;
	}
	
}
