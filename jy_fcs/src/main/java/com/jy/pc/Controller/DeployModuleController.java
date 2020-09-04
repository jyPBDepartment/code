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
import com.jy.pc.Entity.DeployModuleEntity;
import com.jy.pc.Service.DeployModuleService;

@Controller
@ResponseBody
@RequestMapping(value = "deployModuleInfo")
public class DeployModuleController {
	@Autowired
	private DeployModuleService deployModuleService;

	// 模糊查询与分页
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "deployModuleName") String deployModuleName, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<DeployModuleEntity> deployModuleList = deployModuleService.findListByName(deployModuleName, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", deployModuleList);
		return map;
	}

	// 添加
	@RequestMapping(value = "/save")
	public Map<String, Object> save(HttpServletRequest res, HttpServletResponse req) {
		String s = res.getParameter("deployModuleEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		DeployModuleEntity deployModuleEntity = jsonObject.toJavaObject(DeployModuleEntity.class);
		Date date = new Date();
		deployModuleEntity.setCreateDate(date); // 创建时间
		deployModuleEntity.setStatus("1");// 初始化值为禁用
		Map<String, Object> map = new HashMap<String, Object>();
		deployModuleService.save(deployModuleEntity);
		map.put("state", "0");
		map.put("data", deployModuleEntity);
		map.put("message", "添加成功");
		return map;
	}

	// 修改
	@RequestMapping(value = "/update")
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("deployModuleEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		DeployModuleEntity deployModuleEntity = jsonObject.toJavaObject(DeployModuleEntity.class);
		Date date = new Date();
		deployModuleEntity.setUpdateDate(date);
		deployModuleService.update(deployModuleEntity);
		map.put("status", "0");
		map.put("message", "修改成功");
		return map;
	}

	// 账户删除
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();// 接收数据容器
		deployModuleService.delete(id);
		map.put("state", "0");
		map.put("message", "删除成功");
		return map;
	}

	// findById
	@RequestMapping(value = "/findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		DeployModuleEntity deployModuleEntity = deployModuleService.findBId(id);
		if (deployModuleEntity != null) {
			map.put("state", "0");// 成功
			map.put("data", deployModuleEntity);
		} else {
			map.put("state", "1");
		}
		return map;
	}

	// 启用/禁用
	@RequestMapping(value = "/enable")
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") String status, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		DeployModuleEntity deployModuleEntity = deployModuleService.findBId(id);
		deployModuleEntity.setStatus(status);
		deployModuleEntity.getStatus();
		Date date = new Date();
		boolean result = true;
		if (status.equals("0")) {
			deployModuleEntity.setStatus("0");
			deployModuleEntity.setUpdateDate(date);
			map.put("status", "0");
			map.put("message", "启用成功");
		} else if (status.equals("1")) {
			deployModuleEntity.setStatus("1");
			deployModuleEntity.setUpdateDate(date);
			map.put("status", "1");
			map.put("message", "禁用成功");
			result = false;
		}
		deployModuleService.enable(deployModuleEntity, result);
		return map;
	}

	/**
	 * 
	 * 接口 查询所有有效模块信息
	 */

	// 查询所有有效模块信息
	@RequestMapping(value = "/findAllDeployModuleInfo")
	public Map<String, Object> findAllDeployModuleInfo(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<DeployModuleEntity> deployModuleList = deployModuleService.findAllDeployModuleInfo();
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", deployModuleList);
		return map;
	}

}
