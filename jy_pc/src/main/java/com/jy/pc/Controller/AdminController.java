package com.jy.pc.Controller;

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
import com.jy.pc.Entity.AdminEntity;
import com.jy.pc.Service.AdminService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@RequestMapping(value="/login")
	@ResponseBody
	public Map<String, String> login(HttpServletRequest res,HttpServletResponse req,@RequestParam(name="loginName")String loginName,@RequestParam(name="password")String password) {
		
		System.out.println("登录");
		Map<String, String> map = new HashMap<String, String>();
		//验证登录信息
		Boolean flag = adminService.checkLogin(loginName,password);
		
		if(flag) {
			map.put("status", "0");
			map.put("message","登录成功");
		}else {
			map.put("status", "1");
			map.put("message","用户验证失败，无法登录！");
		}
		
		
		
//		req.setHeader("Access-Control-Allow-Origin", "*");
//		req.setHeader("Cache-Control", "no-cache");
		return map;
	}
	
	
	@RequestMapping(value="/save")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest res,HttpServletResponse req,@RequestParam(name="loginName")String loginName,@RequestParam(name="password")String password) {
		
		System.out.println("注册");
		AdminEntity adminEntity = new AdminEntity();
		
		adminEntity.setAdminName(loginName);
		adminEntity.setAdminPassword(password);
		adminService.save(adminEntity);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("message","注册成功");
		req.setHeader("Access-Control-Allow-Origin", "*");
		req.setHeader("Cache-Control", "no-cache");
		return map;
	}

	@RequestMapping(value = "update")
	@ResponseBody
	public Map<String, String> update(HttpServletRequest res,HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s= res.getParameter("adminEntity");
		
		JSONObject jsonObject = JSONObject.parseObject(s);
		AdminEntity adminEntity = jsonObject.toJavaObject(AdminEntity.class);
		System.out.println("打印："+adminEntity.getAdminId());
		adminService.update(adminEntity);
		map.put("message","修改成功");
		
		req.setHeader("Access-Control-Allow-Origin", "*");
		req.setHeader("Cache-Control", "no-cache");
		return map;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String,Object> delete(HttpServletRequest res,HttpServletResponse req,@RequestParam(name="adminId")String adminId) {
		Map<String,Object> map = new HashMap<String,Object>();//接收数据容器
		
		adminService.delete(adminId);
		map.put("status", "0");
		map.put("message", "删除成功");
		
		req.setHeader("Access-Control-Allow-Origin", "*");
		req.setHeader("Cache-Control", "no-cache");
		return map;
	}

	@RequestMapping(value = "findAll")
	@ResponseBody
	public Map<String,Object> findAll(HttpServletRequest res,HttpServletResponse req) {

		Map<String,Object> map = new HashMap<String,Object>();//接收数据容器
		List<AdminEntity> adminList = adminService.findAll();//查询所有数据方法
		map.put("status", "0");
		map.put("message", "查询成功");
		
		map.put("data", adminList);
		req.setHeader("Access-Control-Allow-Origin", "*");
		req.setHeader("Cache-Control", "no-cache");
		return map;
	}

	@RequestMapping(value = "findById")
	@ResponseBody
	public Map<String,Object> findById(HttpServletRequest res,HttpServletResponse req,@RequestParam(name="adminId")String adminId) {

		Map<String,Object> map = new HashMap<String,Object>();
		AdminEntity admin = adminService.findById(adminId);
		if(admin!=null) {
			map.put("status", "0");//查询数据成功
			map.put("data", admin);
		}else {
			map.put("status", "1");//查询数据失败
		}
		
		
		req.setHeader("Access-Control-Allow-Origin", "*");
		req.setHeader("Cache-Control", "no-cache");
		return map;
	}
	
	@RequestMapping(value = "findByName")
	@ResponseBody
	public Map<String,Object> findByName(HttpServletRequest res,HttpServletResponse req,@RequestParam(name="adminName")String adminName,@RequestParam(name="adminPhone")String adminPhone,@RequestParam(name="adminStatic")String adminStatic) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<AdminEntity> adminList=  adminService.findListByName(adminName,adminPhone,adminStatic);
		
		map.put("status", "0");//成功
		map.put("message","查询成功");
		
		map.put("data", adminList);
		
		req.setHeader("Access-Control-Allow-Origin", "*");
		req.setHeader("Cache-Control", "no-cache");
		
		return map;
	}
}
