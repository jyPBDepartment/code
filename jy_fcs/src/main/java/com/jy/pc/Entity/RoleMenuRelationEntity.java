package com.jy.pc.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sas_role_menu_relation")
public class RoleMenuRelationEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length=36)
	private String id;//主键id
	@ManyToOne
	@JoinColumn(name="roleId", referencedColumnName = "id")
	private RoleEntity role;//角色信息，不做级联
	@ManyToOne
	@JoinColumn(name="menuId", referencedColumnName = "id")
	private MenuEntity menu;//菜单信息，不做级联
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public RoleEntity getRole() {
		return role;
	}
	public void setRole(RoleEntity role) {
		this.role = role;
	}
	public MenuEntity getMenu() {
		return menu;
	}
	public void setMenu(MenuEntity menu) {
		this.menu = menu;
	}
	
}
