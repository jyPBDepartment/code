package com.jy.pc.POJO;

import java.util.ArrayList;
import java.util.List;

import com.jy.pc.Entity.MenuEntity;

/**
 * 用于为后台管理系统提供导航栏菜单,字段取自按照前端代码逻辑
 * 
 * @author admin
 *
 */
public class NavMenuData {
	// 主键
	private String menuid;
	// 图标
	private String icon;
	// 名称
	private String menuname;
	// 根据前端代码逻辑推测为"是否有三级菜单"，目前暂无场景，默认为"N"
	private String hasThird;
	// 菜单路径
	private String url;
	// 根据前端代码逻辑推测为"是否为独立菜单"，"Y"代表独立菜单
	private String only;
	// 子菜单,无则为null
	private List<NavMenuData> menus;

	public NavMenuData() {
		super();
	}

	public NavMenuData(MenuEntity menuEntity) {
		super();
		this.menuid = menuEntity.getId();
		this.menuname = menuEntity.getName();
		this.icon = menuEntity.getIcon();
		this.url = menuEntity.getUrl();
		this.hasThird = "N";
	}

	public NavMenuData(MenuEntity menuEntity, List<MenuEntity> menus) {
		super();
		this.menuid = menuEntity.getId();
		this.menuname = menuEntity.getName();
		this.only = menuEntity.getOnly();
		this.icon = menuEntity.getIcon();
		this.url = menuEntity.getUrl();
		this.hasThird = "N";
		List<NavMenuData> temp = new ArrayList<NavMenuData>();
		for (MenuEntity menu : menus) {
			NavMenuData data = new NavMenuData(menu);
			temp.add(data);
		}
		this.menus = temp;
	}

	public NavMenuData(String menuid, String menuname, String url, String icon, String hasThird, String only,
			List<NavMenuData> menus) {
		super();
		this.menuid = menuid;
		this.menuname = menuname;
		this.url = url;
		this.icon = icon;
		this.hasThird = hasThird;
		this.only = only;
		this.menus = menus;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getHasThird() {
		return hasThird;
	}

	public void setHasThird(String hasThird) {
		this.hasThird = hasThird;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOnly() {
		return only;
	}

	public void setOnly(String only) {
		this.only = only;
	}

	public List<NavMenuData> getMenus() {
		return menus;
	}

	public void setMenus(List<NavMenuData> menus) {
		this.menus = menus;
	}

}
