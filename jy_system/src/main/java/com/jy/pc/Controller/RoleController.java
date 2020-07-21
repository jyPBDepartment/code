package com.jy.pc.Controller;

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
import com.jy.pc.Entity.LimitEntity;
import com.jy.pc.Entity.RoleEntity;
import com.jy.pc.Service.LimitService;
import com.jy.pc.Service.RoleService;

@Controller
@RequestMapping(value = "/role")
@ResponseBody
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private LimitService limitService;
	
	// 角色添加
	@RequestMapping(value = "/add")
	
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("roleEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		RoleEntity roleEntity = jsonObject.toJavaObject(RoleEntity.class);
		Date date = new Date();
		roleEntity.setCreateTime(date);
		LimitEntity limitEntity = new LimitEntity();
		limitEntity = limitService.findId(roleEntity.getLimitId());
		roleEntity.setLimitName(limitEntity.getName());
		roleService.save(roleEntity);
		map.put("message", "添加成功");
		return map;
	}
	
	// 角色修改
	@RequestMapping(value = "/update")
	
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {
		
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("roleEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		RoleEntity roleEntity = jsonObject.toJavaObject(RoleEntity.class);
		Date date = new Date();
		roleEntity.setEditTime(date);
		LimitEntity limitEntity = new LimitEntity();
		limitEntity = limitService.findId(roleEntity.getLimitId());
		roleEntity.setLimitName(limitEntity.getName());
		roleService.update(roleEntity);
		map.put("message", "添加成功");
		return map;
	}
	
	
	
	//角色删除
	@RequestMapping(value = "/delete")
	 public Map<String,Object> delete(HttpServletRequest res,HttpServletResponse req,
	   @RequestParam(name="id")String id) {
	  
	  Map<String,Object> map = new HashMap<String,Object>();
	  List<RoleEntity> rolel = roleService.findRoleLink();
		RoleEntity roleEntity = roleService.findId(id);
		for(int i = 0;i < rolel.size();i++){
			RoleEntity a = rolel.get(i);
		    a.getId();
		    if(roleEntity.getId().equals(a.getId())){
		    	roleService.delete(id);
				map.put("status", "0");
				map.put("message", "删除成功");
			} 
		    else {
		  
			}
		}
		if(rolel.size()<=0) {
		}
		return map;
	 }

	//查询所有
	@RequestMapping(value = "/findAll")
	public Map<String,Object> findAll(HttpServletRequest res,HttpServletResponse req) {

		Map<String,Object> map = new HashMap<String,Object>();
		List<RoleEntity> rolesList = roleService.findAl();
		map.put("status", "0");
		map.put("message", "查询成功");
		map.put("data", rolesList);
		return map;
	}

	//条件查询
	@RequestMapping(value = "/findById")
	public Map<String,Object> findById(HttpServletRequest res,HttpServletResponse req,
			@RequestParam(name="id")String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		RoleEntity roleEntity = roleService.findId(id);
		if(roleEntity!=null) {
			map.put("status", "0");
			map.put("data", roleEntity);
		}else {
			map.put("status", "1");
		}
		return map;
	}
	
	//查询
	@RequestMapping(value = "/findByName")
	public Map<String,Object> findByName(HttpServletRequest res,HttpServletResponse req,
			@RequestParam(name="name")String name,
			@RequestParam(name="page")Integer page,
			@RequestParam(name="size")Integer size) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		Pageable pageable = new PageRequest(page-1,size);
		Page<RoleEntity> roleList=  roleService.findListByName(name,pageable);
		map.put("status", "0");//成功
		map.put("message","查询成功");
		map.put("data", roleList);
		return map;
	}
	
	//启用/禁用
	@RequestMapping(value="/enable")
	public Map<String, String> opensulf(HttpServletRequest res,HttpServletResponse req,
			@RequestParam(name="state")String state,@RequestParam(name="id")String id) {
		
		Map<String, String> map = new HashMap<String, String>();
		RoleEntity roleEntity = roleService.findId(id);
		roleEntity.setState(state);
		roleEntity.getState();
		if (state.equals("0")) {
			roleEntity.setState("0");
			map.put("status", "0");
			map.put("message", "启用成功");
		} else if (state.equals("1")) {
			roleEntity.setState("1");
			map.put("status", "0");
			map.put("message", "禁用成功");
		}
		roleService.update(roleEntity);
		return map;
	}
}
