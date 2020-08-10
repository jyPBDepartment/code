package com.jy.pc.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//农服图片关联表
@Entity
@Table(name="sas_agricultural_picture")
public class AgriculturalPictureEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length=36)
	private String id;//主键id
	@Column
	private String picId; //图片Id
	@Column
	private String agrId; //农服Id
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
	public String getAgrId() {
		return agrId;
	}
	public void setAgrId(String agrId) {
		this.agrId = agrId;
	}
	

}
