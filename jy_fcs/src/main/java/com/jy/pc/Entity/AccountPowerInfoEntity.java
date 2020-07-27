package com.jy.pc.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sas_account_power_info")
public class AccountPowerInfoEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	// 主键id
	private String id;
	// 账户id
	@Column
	private String accountId;
	// 权限编码
	@Column
	private String jurCodel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getJurCodel() {
		return jurCodel;
	}

	public void setJurCodel(String jurCodel) {
		this.jurCodel = jurCodel;
	}

}
