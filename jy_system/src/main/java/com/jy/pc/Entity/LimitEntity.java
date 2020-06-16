package com.jy.pc.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "w_limit")
public class LimitEntity {
		// 主键Id
		@Id
		@GeneratedValue(generator = "uuid")
		@GenericGenerator(strategy = "uuid", name = "uuid")
		private String id;
		// 权限名称
		@Column
		private String name;
		// 权限路径类
		@Column
		private String path;
		//权限类型
		@Column
		private int type;
		//权限状态
		@Column
		private String state;
		//创建时间
		@Column
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	    @Temporal(TemporalType.TIMESTAMP)
		private Date createTime;
		//修改时间
		@Column
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	    @Temporal(TemporalType.TIMESTAMP)
		private Date editTime;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public Date getEditTime() {
			return editTime;
		}
		public void setEditTime(Date editTime) {
			this.editTime = editTime;
		}
		
}
