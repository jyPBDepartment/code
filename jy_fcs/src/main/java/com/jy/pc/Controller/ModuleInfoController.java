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
import com.jy.pc.Entity.ModuleInfoEntity;
import com.jy.pc.Service.ModuleInfoService;

@Controller
@RequestMapping(value = "/moduleInfo")
@ResponseBody
public class ModuleInfoController {
	@Autowired
	private ModuleInfoService moduleInfoService;

	// 查询 分页
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "status") String status,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<ModuleInfoEntity> accountInfoList = moduleInfoService.findListByName(name, status, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", accountInfoList);
		return map;
	}

	// 模块添加
	@RequestMapping(value = "/add")
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("moduleInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		ModuleInfoEntity moduleInfoEntity = jsonObject.toJavaObject(ModuleInfoEntity.class);
		Date date = new Date();
		moduleInfoEntity.setCreateDate(date);
		moduleInfoEntity.setStatus("1");
		moduleInfoService.save(moduleInfoEntity);
		map.put("status", "0");
		map.put("message", "添加成功");
		return map;
	}

	// 模块修改
	@RequestMapping(value = "/update")
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("moduleInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		ModuleInfoEntity moduleInfoEntity = jsonObject.toJavaObject(ModuleInfoEntity.class);
		Date date = new Date();
		moduleInfoEntity.setUpdateDate(date);
		moduleInfoService.update(moduleInfoEntity);
		map.put("status", "0");
		map.put("message", "修改成功");
		return map;
	}

	// 模块删除
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		moduleInfoService.delete(id);
		map.put("status", "0");
		map.put("message", "删除成功");
		return map;
	}

	// 条件查询
	@RequestMapping(value = "/findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		ModuleInfoEntity moduleInfoEntity = moduleInfoService.findId(id);
		if (moduleInfoEntity != null) {
			map.put("status", "0");
			map.put("data", moduleInfoEntity);
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
		ModuleInfoEntity moduleInfoEntity = moduleInfoService.findId(id);
		moduleInfoEntity.setStatus(status);
		moduleInfoEntity.getStatus();
		Date date = new Date();
		boolean result = true;
		if (status.equals("0")) {
			moduleInfoEntity.setStatus("0");
			moduleInfoEntity.setUpdateDate(date);
			map.put("state", "0");
			map.put("message", "启用成功");
		} else if (status.equals("1")) {
			moduleInfoEntity.setStatus("1");
			moduleInfoEntity.setUpdateDate(date);
			map.put("state", "1");
			map.put("message", "禁用成功");
			result = false;
		}
		moduleInfoService.enable(moduleInfoEntity,result);
		return map;
	}
	
	/**
	 *     
	       接口
	 * @param res
	 * @param req
	 * @param name
	 * @return
	 */
	//根据模块名称查询非禁用模块信息
	@RequestMapping(value = "findModuleListByName")
	public Map<String, Object> findModuleListByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name) {

		Map<String, Object> map = new HashMap<String, Object>();// 接收数据容器
		List<ModuleInfoEntity> moduleInfoEntity = moduleInfoService.findModuleListByName(name);
		map.put("status", "0");
		map.put("message", "查询成功");
		map.put("data", moduleInfoEntity);
		return map;
	}
	
	//查询模块表所有非禁用信息
	@RequestMapping(value = "findModuleOn")
	public Map<String, Object> findModuleOn(HttpServletRequest res, HttpServletResponse req) {

		Map<String, Object> map = new HashMap<String, Object>();// 接收数据容器
		List<ModuleInfoEntity> moduleInfoEntity = moduleInfoService.findModuleOn();
		if(moduleInfoEntity!=null) {
			map.put("status", "0");
			map.put("message", "查询成功");
			map.put("data", moduleInfoEntity);
		}
		return map;
	}
	// 查询所有有效的模块信息
		@RequestMapping(value = "/findListByMobile")
		public Map<String, Object> findListByMobile(HttpServletRequest res, HttpServletResponse req) {

			Map<String, Object> map = new HashMap<String, Object>();
			List<ModuleInfoEntity> effect = moduleInfoService.findListByMobile();
			for(int i=0; i<effect.size();i++) {				
				map.put("data", effect);
			}
			map.put("state", "0");// 成功
			map.put("message", "查询成功");
			return map;
		}

}
