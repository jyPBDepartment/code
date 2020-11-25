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
 * 粮食价格实体类
 * 
 * @author Stephen Zhou
 * @version 1.0
 * 
 */
//粮价信息表
@Entity
@Table(name = "sas_grain_prices_info")
public class GrainPricesEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	// 主键id
	private String id;
	// 价格
	@Column
	private String price;
	// 价格生成时间
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Temporal(TemporalType.DATE)
	private Date priceDate;
	// 价格定义类型
	@Column
	private String priceDefinedType;// 0.手动输入1.系统自动生成
	// 创建时间
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	// 修改时间
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	// 创建人
	@Column
	private String createUser;
	// 修改人
	@Column
	private String updateUser;

	@Column(columnDefinition = "varchar(36) comment '最低价格'")
	private String minPrice;

	@Column(columnDefinition = "varchar(36) comment '最高价格'")
	private String maxPrice;

	@Column(columnDefinition = "varchar(36) comment '所属区域最下级ID'")
	private String areaId;

	@Column(columnDefinition = "varchar(36) comment '所属区域'")
	private String area;

	@Column(columnDefinition = "varchar(36) comment '省'")
	private String province;

	@Column(columnDefinition = "varchar(36) comment '市'")
	private String city;

	@Column(columnDefinition = "varchar(36) comment '县'")
	private String district;

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Date getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}

	public String getPriceDefinedType() {
		return priceDefinedType;
	}

	public void setPriceDefinedType(String priceDefinedType) {
		this.priceDefinedType = priceDefinedType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	public String toString() {
		return "粮价走势 [粮价=" + price + ", 日期=" + priceDate + ", 操作人=" + createUser + "]";
	}

}
