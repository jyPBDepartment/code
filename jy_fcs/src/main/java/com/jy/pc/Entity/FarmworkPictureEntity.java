package com.jy.pc.Entity;
//农活预约图片关联表

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sas_farmwork_picture")
public class FarmworkPictureEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id
	@Column
	private String picId; // 图片Id
	@Column
	private String farmworkId; // 农活预约Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPicId() {
		return picId;
	}
	public void setPicId(String picId) {
		this.picId = picId;
	}
	public String getFarmworkId() {
		return farmworkId;
	}
	public void setFarmworkId(String farmworkId) {
		this.farmworkId = farmworkId;
	}

}
