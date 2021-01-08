package com.jy.pc.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//粮食买卖点赞表
@Entity
@Table(name = "sas_grain_trading_praise")
public class GrainTradingPraiseEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id
	@Column(columnDefinition = "varchar(36) default '' comment '粮食买卖id'")
	private String argId;
	@Column(columnDefinition = "varchar(36) default '' comment '点赞用户id'")
	private String praiseUserId;// 点赞用户id,本地缓存获取userId

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArgId() {
		return argId;
	}

	public void setArgId(String argId) {
		this.argId = argId;
	}

	public String getPraiseUserId() {
		return praiseUserId;
	}

	public void setPraiseUserId(String praiseUserId) {
		this.praiseUserId = praiseUserId;
	}

}
