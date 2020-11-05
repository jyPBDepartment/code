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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jy.pc.Entity.EduFormworkEntity;
import com.jy.pc.Entity.EduVocationInfoEntity;
import com.jy.pc.Service.EduFormworkService;

/**
 * 证书模板相关
 * 
 * @author admin
 *
 */
@RequestMapping(value = "formwork")
@RestController
public class EduFormworkController {
	@Autowired
	private EduFormworkService eduFormworkService;

	// 分页条件查询
	@RequestMapping(value = "/findPage")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "createBy") String createBy, @RequestParam(name = "name") String name,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "status") String status,
			@RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<EduFormworkEntity> list = eduFormworkService.findListByParam(name, status, createBy,
				pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", list);
		return map;
	}

	// 根据id返回详情信息
	@RequestMapping(value = "/findById")
	@ResponseBody
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		EduFormworkEntity entity = eduFormworkService.findInfobyId(id);
		if (entity != null) {
			map.put("state", "0");
			map.put("data", entity);
		} else {
			map.put("state", "1");
		}
		return map;
	}

	// 根据id删除课程信息
	@RequestMapping(value = "/delete")
	public Map<String, String> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		eduFormworkService.delete(id);
		map.put("state", "0");
		map.put("message", "删除成功");
		return map;
	}

	// 切换生效状态
	@RequestMapping(value = "/enable")
	public Map<String, String> enable(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") int status, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		EduFormworkEntity entity = eduFormworkService.findInfobyId(id);
		Date date = new Date();
		entity.setUpdateDate(date);
		entity.setStatus(status);
		boolean result = true;
		if (status == 0) {
			map.put("status", "0");
			map.put("message", "启用成功");
		}
		if (status == 1) {
			map.put("status", "1");
			map.put("message", "禁用成功");
			result = false;
		}
		eduFormworkService.enable(entity, result);
		return map;
	}

	// 添加
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("entity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		EduFormworkEntity entity = jsonObject.toJavaObject(EduFormworkEntity.class);
		EduVocationInfoEntity vocation = new EduVocationInfoEntity();
		vocation.setId(entity.getVocationId());
		entity.setVocation(vocation);
		entity.setStatus(1);
		Date date = new Date();
		entity.setCreateDate(date);
		entity.setStatus(1);
		eduFormworkService.save(entity);
		map.put("state", "0");
		map.put("message", "添加成功");
		return map;
	}

	// 修改
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("entity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		EduFormworkEntity entity = jsonObject.toJavaObject(EduFormworkEntity.class);
		Date date = new Date();
		entity.setCreateDate(date);
		//entity.setStatus(1);
		eduFormworkService.update(entity);
		map.put("state", "0");
		map.put("message", "修改成功");
		return map;
	}
}
