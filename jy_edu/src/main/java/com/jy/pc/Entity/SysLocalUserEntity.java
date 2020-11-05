package com.jy.pc.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户信息本地表
 */
@Entity
@Table(name = "sys_local_user")
public class SysLocalUserEntity {
	@Id
	@Column(columnDefinition = "varchar(128) comment '主键id,通过金网接口获取，不使用自动成成策略'")
	private String id;
	@Column(columnDefinition = "varchar(128) comment '用户姓名'")
	private String userName;
	@Column(columnDefinition = "varchar(128) comment '用户编码，系统生成'")
	private String userCode;
	@Column(columnDefinition = "varchar(128) comment '用户手机号'")
	private String userTel;
	@Column(columnDefinition = "varchar(20) comment '用户身份证号'")
	private String userCard;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserCard() {
		return userCard;
	}

	public void setUserCard(String userCard) {
		this.userCard = userCard;
	}

}
