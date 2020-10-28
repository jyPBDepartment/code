package com.jy.pc.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jy.pc.Entity.MenuEntity;
import com.jy.pc.Entity.RoleMenuRelationEntity;
import com.jy.pc.POJO.NavMenuData;
import com.jy.pc.Service.MenuService;
import com.jy.pc.Service.RoleMenuRelationService;
import com.jy.pc.Utils.Aes;
import com.mysql.cj.util.StringUtils;

@Controller
@RequestMapping(value = "/menu")
@ResponseBody
public class MenuController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleMenuRelationService roleMenuRelationService;

	//为导航栏提供数据
	@RequestMapping(value = "/getNavData")
	public Map<String, Object> getNavData(HttpServletRequest res, HttpServletResponse req,
			String token,@RequestParam("roleId") String roleId) {
		//logintoken为预留参数，后续需根据用户角色返回不同菜单
		Map<String, Object> map = new HashMap<String, Object>();
		List<NavMenuData> navList = menuService.findNavData(roleId);
		map.put("status", "0");// 成功
		map.put("msg", "success");
		map.put("data", navList);
		return map;
	}
	
	// 根据菜单名称查询
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<MenuEntity> roleList = menuService.findListByName(name);
		map.put("status", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", roleList);
		return map;
	}

	// 主键查询
	@RequestMapping(value = "/findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		MenuEntity menuEntity = menuService.findId(id);
		if (menuEntity != null) {
			map.put("status", "0");
			map.put("data", menuEntity);
		} else {
			map.put("status", "1");
		}
		return map;
	}

	// 启用/禁用
	@RequestMapping(value = "/enable")
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") String status, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		MenuEntity menuEntity = menuService.findId(id);
		Date date = new Date();
		boolean result = true;
		if (status.equals("0")) {
			map.put("status", "0");
			map.put("message", "启用成功");
		} else if (status.equals("1")) {
			map.put("status", "1");
			map.put("message", "禁用成功");
			result = false;
		}
		menuEntity.setStatus(status);
		menuEntity.setUpdDate(date);
		menuService.enable(menuEntity, result);
		return map;
	}

	// 修改排序
	@RequestMapping(value = "/changeSort")
	public Map<String, String> changeMenuSort(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id, @RequestParam(name = "sort") int sort) {

		Map<String, String> map = new HashMap<String, String>();
		MenuEntity menuEntity = menuService.findId(id);
		Date date = new Date();
		menuEntity.setSort(sort);
		menuEntity.setUpdDate(date);
		menuService.changeSort(menuEntity);
		return map;
	}

	// 删除
	@RequestMapping(value = "/delete")
	public Map<String, String> deleteMenu(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, String> map = new HashMap<String, String>();
		// 判断是否存在子菜单
		if (menuService.hasSubMenu(id)) {
			map.put("status", "1");
			map.put("message", "该菜单下存在子菜单，不可直接删除！");
			return map;
		}
		// 判断该菜单是否已有角色挂载
		if (roleMenuRelationService.hasRelationByMenu(id)) {
			map.put("status", "2");
			map.put("message", "已有角色挂载该菜单，不可直接删除！");
			return map;
		}
		// 执行删除操作,删除该菜单
		menuService.delete(id);
		map.put("status", "0");
		map.put("message", "删除成功！");
		return map;
	}

	// 获取菜单树
	@RequestMapping(value = "/findTree")
	public Map<String, Object> findTree(HttpServletRequest res, HttpServletResponse req) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> menuTree = menuService.findTree();
		map.put("status", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", menuTree);
		return map;
	}

	/**
	 * 通过角色id获取菜单授权信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findRelaByRole")
	public Map<String, Object> findRelaByRole(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "roleId") String roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<RoleMenuRelationEntity> data = roleMenuRelationService.findRelationByRole(roleId);
		map.put("status", "0");// 成功
		map.put("data", data);
		return map;
	}

	// 保存角色与菜单关联关系
	@RequestMapping(value = "/saveRelation")
	public Map<String, String> saveRelation(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String roleId = res.getParameter("roleId");
		String idArr = res.getParameter("idArr");
		Aes aes = new Aes();
		try {
			idArr = aes.desEncrypt(idArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		roleMenuRelationService.batchSave(roleId, idArr);
		map.put("status", "0");
		map.put("message", "保存成功");
		return map;
	}

	// 保存菜单
	@RequestMapping(value = "/save")
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("menuEntity");
		Aes aes = new Aes();
		String a = "";
		try {
			a = aes.desEncrypt(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.parseObject(a);
		MenuEntity menuEntity = jsonObject.toJavaObject(MenuEntity.class);
		Date date = new Date();
		menuEntity.setUpdDate(date);
		if (StringUtils.isNullOrEmpty(menuEntity.getParentId())) {
			menuEntity.setLevel(0);
		} else {
			menuEntity.setLevel(menuService.findId(menuEntity.getParentId()).getLevel() + 1);
		}
		if (StringUtils.isNullOrEmpty(menuEntity.getId())) {
			menuEntity.setStatus("0");
			menuEntity.setAddDate(date);
			menuService.save(menuEntity);
		} else {
			menuEntity.setUpdDate(date);
			menuService.update(menuEntity);
		}
		map.put("status", "0");
		map.put("message", "保存成功");
		return map;
	}
}
