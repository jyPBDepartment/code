package com.jy.pc.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "sas_menu")
public class MenuEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id

	private String only;// 仅用于目录，"Y"代表

	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date addDate;// 新增时间
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updDate;// 修改时间
	@Column(length = 8)
	private String name;// 菜单名称
	@Column(length = 1)
	private String status;// 状态 - 0启用1禁用
	@Column(length = 255)
	private String icon;// 图标
	@Column
	private int sort;// 排序 - 数值越小优先级越高
	@Column(length = 255)
	private String url;// 菜单链接
	@Column(length = 64)
	private String perssions;// 权限标识 - 用于按钮级
	@Column
	private int menuType;// 类型 - 1目录2菜单3按钮
	@Column(length = 36)
	private String parentId;// 父级id
	@Column
	private int level;// 菜单级别 - 目录类级别固定为0，按钮类级别固定为-1
	@OneToMany(mappedBy = "role")
	private List<RoleMenuRelationEntity> relations;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOnly() {
		return only;
	}

	public void setOnly(String only) {
		this.only = only;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPerssions() {
		return perssions;
	}

	public void setPerssions(String perssions) {
		this.perssions = perssions;
	}

	public int getMenuType() {
		return menuType;
	}

	public void setMenuType(int menuType) {
		this.menuType = menuType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<RoleMenuRelationEntity> getRelations() {
		return relations;
	}

	public void setRelations(List<RoleMenuRelationEntity> relations) {
		this.relations = relations;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public MenuEntity(String id) {
		super();
		this.id = id;
	}

	public MenuEntity() {
		super();
	}
}
