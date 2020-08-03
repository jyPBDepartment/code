package com.jy.pc.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jy.pc.Entity.ClassificationEntity;
import com.jy.pc.Entity.KeyWordEntity;
import com.jy.pc.Entity.RoleEntity;
import com.jy.pc.Service.RoleMenuRelationService;
import com.jy.pc.Service.RoleService;
import com.jy.pc.Utils.Aes;
import com.mysql.cj.util.StringUtils;

@Controller
@RequestMapping(value = "/role")
@ResponseBody
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleMenuRelationService roleMenuRelationService;
	// 分页查询
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<RoleEntity> roleList = roleService.findListByName(name, pageable);
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
		RoleEntity roleEntity = roleService.findId(id);
		if (roleEntity != null) {
			map.put("status", "0");
			map.put("data", roleEntity);
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
		RoleEntity roleEntity = roleService.findId(id);
		Date date = new Date();
		if ("0".equals(status)) {
			map.put("status", "0");
			map.put("message", "启用成功");
		} 
		if ("1".equals(status)) {
			map.put("status", "1");
			map.put("message", "禁用成功");
		}
		roleEntity.setStatus(status);
		roleEntity.setUpdDate(date);
		roleService.update(roleEntity);
		return map;
	}

	// 角色保存
	@RequestMapping(value = "/save")
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("roleEntity");
		Aes aes = new Aes();
		String a = "";
		try {
			a = aes.desEncrypt(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.parseObject(a);
		RoleEntity roleEntity = jsonObject.toJavaObject(RoleEntity.class);
		Date date = new Date();
		roleEntity.setUpdDate(date);
		if(StringUtils.isNullOrEmpty(roleEntity.getId())) {
			roleEntity.setStatus("0");
			roleEntity.setAddDate(date);
		}
		roleService.save(roleEntity);
		map.put("status", "0");
		map.put("message", "保存成功");
		return map;
	}

	// 关键字删除
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		//判断该菜单是否已有角色挂载
		if(roleMenuRelationService.hasRelationByRole(id)) {
			map.put("status", "2");
			map.put("message", "该角色存在授权菜单，不可直接删除！");
			return map;
		}
		roleService.delete(id);
		map.put("status", "0");
		map.put("message", "删除成功");
		return map;
	}
}
