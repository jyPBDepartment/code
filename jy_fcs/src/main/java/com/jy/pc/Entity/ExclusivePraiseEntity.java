package com.jy.pc.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//独家点评点赞表
@Entity
@Table(name = "sas_exclusive_praise")
public class ExclusivePraiseEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id
	@Column(columnDefinition = "varchar(36) default '' comment '文章点评id'")
	private String artId;
	@Column(columnDefinition = "varchar(36) default '' comment '点赞用户id'")
	private String praiseUserId;// 点赞用户id,本地缓存获取userId
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArtId() {
		return artId;
	}
	public void setArtId(String artId) {
		this.artId = artId;
	}
	public String getPraiseUserId() {
		return praiseUserId;
	}
	public void setPraiseUserId(String praiseUserId) {
		this.praiseUserId = praiseUserId;
	}
	
}
