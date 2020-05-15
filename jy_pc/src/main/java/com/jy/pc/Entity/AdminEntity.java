package com.jy.pc.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="admin")
public class AdminEntity {
	@Id
	@GeneratedValue(generator="uuid")    
	@GenericGenerator(strategy = "uuid", name = "uuid")  
	private String adminId;
	@Column
	private String adminLoginName;
	@Column
	private String adminPassword;
	@Column
	private int adminStatic;
	@Column
	private String adminPhone;
	@Column
	private String adminName;
	@Column
	private String adminIDCard;
	@Column
	private String adminRole;
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminLoginName() {
		return adminLoginName;
	}
	public void setAdminLoginName(String adminLoginName) {
		this.adminLoginName = adminLoginName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public int getAdminStatic() {
		return adminStatic;
	}
	public void setAdminStatic(int adminStatic) {
		this.adminStatic = adminStatic;
	}
	public String getAdminPhone() {
		return adminPhone;
	}
	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminIDCard() {
		return adminIDCard;
	}
	public void setAdminIDCard(String adminIDCard) {
		this.adminIDCard = adminIDCard;
	}
	public String getAdminRole() {
		return adminRole;
	}
	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}
	
}
