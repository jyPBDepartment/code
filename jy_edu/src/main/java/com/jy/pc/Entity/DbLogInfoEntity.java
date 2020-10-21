package com.jy.pc.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="sas_db_log_info")
public class DbLogInfoEntity {
	//主键ID
	@Id
	@GeneratedValue(generator="uuid")    
	@GenericGenerator(strategy = "uuid", name = "uuid")  
	private String id;
	//操作时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date logDate;
	//操作 -- 功能模块(Controller)
	@Column
	private String module;
	//操作 -- 实际动作(Method)
	@Column
	private String action;
	//操作对象
	@Column(columnDefinition="TEXT")
	private String actObj;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getActObj() {
		return actObj;
	}
	public void setActObj(String actObj) {
		this.actObj = actObj;
	}
	
}
