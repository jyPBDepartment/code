package com.jy.pc.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.alibaba.fastjson.JSONObject;
import com.jy.pc.Entity.JurisdictionEntity;
import com.jy.pc.Entity.RolesEntity;
import com.jy.pc.Service.RolesService;

@Controller
@RequestMapping(value = "/role")
@ResponseBody
public class RolesController {
	@Autowired
	private RolesService rolesService;

	// 角色添加
	@RequestMapping(value = "/add")
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "roleName") String roleName, @RequestParam(name = "roleType") Integer roleType) {
		RolesEntity rolesEntity = new RolesEntity();
		rolesEntity.setRoleName(roleName);
		rolesEntity.setRoleType(roleType);
		//rolesEntity.setLimitId(limitId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );// 格式化时间		
		String time=DateFormat.getDateTimeInstance().format(new Date());
		try {
			rolesEntity.setCreateTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		rolesService.save(rolesEntity);
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "添加成功");
		req.setHeader("Access-Control-Allow-Origin", "*");
		req.setHeader("Cache-Control", "no-cache");
		return map;
	}
	
	//角色修改
	@RequestMapping(value = "/update")
	public Map<String, String> update(HttpServletRequest res,HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("rolesEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		RolesEntity rolesEntity = jsonObject.toJavaObject(RolesEntity.class);
		System.out.println("打印："+rolesEntity.getId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );// 格式化时间		
		String time=DateFormat.getDateTimeInstance().format(new Date());
		try {
			rolesEntity.setEditTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		rolesService.update(rolesEntity);
		map.put("message","修改成功");
		req.setHeader("Access-Control-Allow-Origin", "*");
		req.setHeader("Cache-Control", "no-cache");
		return map;
	}

	//角色删除
	@RequestMapping(value = "/delete")
	public Map<String,Object> delete(HttpServletRequest res,HttpServletResponse req,
			@RequestParam(name="id")String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		rolesService.delete(id);
		map.put("status", "0");
		map.put("message", "删除成功");
		req.setHeader("Access-Control-Allow-Origin", "*");
		req.setHeader("Cache-Control", "no-cache");
		return map;
	}

	//查询所有
	@RequestMapping(value = "/findAll")
	public Map<String,Object> findAll(HttpServletRequest res,HttpServletResponse req) {

		Map<String,Object> map = new HashMap<String,Object>();
		List<RolesEntity> rolesList = rolesService.findAll();
		map.put("status", "0");
		map.put("message", "查询成功");
		map.put("data", rolesList);
		req.setHeader("Access-Control-Allow-Origin", "*");
		req.setHeader("Cache-Control", "no-cache");
		return map;
	}

	//条件查询
	@RequestMapping(value = "/findById")
	public Map<String,Object> findById(HttpServletRequest res,HttpServletResponse req,
			@RequestParam(name="id")String id) {

		Map<String,Object> map = new HashMap<String,Object>();
		RolesEntity rolesEntity = rolesService.findId(id);
		if(rolesEntity!=null) {
			map.put("status", "0");
			map.put("data", rolesEntity);
		}else {
			map.put("status", "1");
		}
		req.setHeader("Access-Control-Allow-Origin", "*");
		req.setHeader("Cache-Control", "no-cache");
		return map;
	}
	
	//查询
	@RequestMapping(value = "/findByName")
	public Map<String,Object> findByName(HttpServletRequest res,HttpServletResponse req,
			@RequestParam(name="roleName")String roleName,
			@RequestParam(name="roleType")Integer roleType,
			@RequestParam(name="page")Integer page,
			@RequestParam(name="size")Integer size) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		Pageable pageable = new PageRequest(page-1,size);
		Page<RolesEntity> roleList=  rolesService.findListByName(roleName, roleType,pageable);

		map.put("status", "0");//成功
		map.put("message","查询成功");
		map.put("data", roleList);
		req.setHeader("Access-Control-Allow-Origin", "*");
		req.setHeader("Cache-Control", "no-cache");
		return map;
	}
	
	//启用/禁用
	@RequestMapping(value="/enable")
	public Map<String, String> opensulf(HttpServletRequest res,HttpServletResponse req,
			@RequestParam(name="state")Integer state,@RequestParam(name="id")String id) {
		
		Map<String, String> map = new HashMap<String, String>();
		RolesEntity roleEntity = rolesService.findId(id);
		roleEntity.setState(state);
		roleEntity.getState();
		System.out.println("禁用");
		if(state.equals(0)) {
			roleEntity.setState(1);
			map.put("status", "0");
			map.put("message","禁用成功");
			
		}
		else if(state.equals(1)){
			roleEntity.setState(0);
			map.put("status", "0");
			map.put("message","启用成功");
		}
		rolesService.update(roleEntity);
		req.setHeader("Access-Control-Allow-Origin", "*");
		req.setHeader("Cache-Control", "no-cache");
		return map;
	}
}
