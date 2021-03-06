package com.jy.pc.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 证书信息表
 */
@Entity
@Table(name = "edu_certificate_info")
public class EduCertificateInfoEntity extends BaseEntity {
	@Column(columnDefinition = "varchar(128) comment '证书名称'")
	private String name;
	@Column(columnDefinition = "varchar(255) comment '证书描述'")
	private String remark;
	@OneToOne
	@JoinColumn(name = "vocation_id", columnDefinition = "varchar(36) comment '职业类别ID'")
	private EduVocationInfoEntity vocation;
	@Transient
	private String vocationId;

	public String getVocationId() {
		return vocationId;
	}

	public void setVocationId(String vocationId) {
		this.vocationId = vocationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public EduVocationInfoEntity getVocation() {
		return vocation;
	}

	public void setVocation(EduVocationInfoEntity vocation) {
		this.vocation = vocation;
	}

}
