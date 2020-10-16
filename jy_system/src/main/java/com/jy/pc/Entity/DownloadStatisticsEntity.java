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

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 	app下载统计表
 * 
 * */
@Entity
@Table(name="w_download_statistics")
public class DownloadStatisticsEntity {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length=36)
	private String id;//主键ID
	@Column
	private int hnStatistics;// 慧农app下载统计
	@Column
	private int zgStatistics;//掌柜下载统计
	//数据生成时间
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getHnStatistics() {
		return hnStatistics;
	}
	public void setHnStatistics(int hnStatistics) {
		this.hnStatistics = hnStatistics;
	}
	public int getZgStatistics() {
		return zgStatistics;
	}
	public void setZgStatistics(int zgStatistics) {
		this.zgStatistics = zgStatistics;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
