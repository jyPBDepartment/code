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

import com.alibaba.fastjson.JSONObject;
import com.jy.pc.Entity.AdminEntity;

import com.jy.pc.Entity.RoleEntity;
import com.jy.pc.Service.AdminService;
import com.jy.pc.Service.RoleService;


@Controller
@RequestMapping(value="admin")
@ResponseBody
public class AdminController<RolesService> {
	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;
	//管理员添加

	@RequestMapping(value="/save")
	
	public Map<String, String> save(HttpServletRequest res,HttpServletResponse req) {
		
		String s = res.getParameter("adminEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
	
		//自动获取系统时间
		
//		String createDateTime = DateFormat.getDateTimeInstance().format(new Date());
//		adminEntity.setCreateDateTime(createDateTime);
//		adminEntity.setUpdateTime(createDateTime);
		Date date = new Date();

		AdminEntity adminEntity = jsonObject.toJavaObject(AdminEntity.class);
	
		adminEntity.setCreateDateTime(date);
			
		RoleEntity roleEntity = new RoleEntity();
		roleEntity = roleService.findId(adminEntity.getRoleId());
		adminEntity.setRoleName(roleEntity.getName());
		adminService.save(adminEntity);
		Map<String, String> map = new HashMap<String, String>();
		map.put("message","添加成功");
		
		return map;
	}
	// 管理员修改前查询
		@RequestMapping(value = "findById")
		public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
				@RequestParam(name = "id") String id) {
			Map<String, Object> map = new HashMap<String, Object>();
			AdminEntity admin = adminService.findBId(id);
			if (admin != null) {
				map.put("status", "0");// 查询数据成功
				map.put("data", admin);
			} else {
				map.put("status", "1");// 查询数据失败
			}
			return map;
		}
		// 管理员修改
		@RequestMapping(value = "update")
		public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {
			Map<String, String> map = new HashMap<String, String>();
			String s = res.getParameter("adminEntity");
			JSONObject jsonObject = JSONObject.parseObject(s);
			Date date = new Date();

			AdminEntity adminEntity = jsonObject.toJavaObject(AdminEntity.class);
		
			adminEntity.setUpdateTime(date);
				
			adminService.update(adminEntity);
			map.put("message", "修改成功");
			return map;
		}
		// 管理员删除
		@RequestMapping(value = "delete")
		public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
				@RequestParam(name = "id") String id) {
			Map<String, Object> map = new HashMap<String, Object>();// 接收数据容器
			adminService.delete(id);
			map.put("status", "0");
			map.put("message", "删除成功");
			return map;
		}
		// 管理员模糊查询与分页
		@RequestMapping(value = "findByName")
		public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
				@RequestParam(name = "loginName") String loginName, @RequestParam(name = "page") Integer page,
				@RequestParam(name = "size") Integer size) {
			Map<String, Object> map = new HashMap<String, Object>();
			Pageable pageable = new PageRequest(page - 1, size);
			Page<AdminEntity> adminList = adminService.findListByName(loginName, pageable);
			map.put("status", "0");// 成功
			map.put("message", "查询成功");
			map.put("data", adminList);
			
			return map;
		}

}
