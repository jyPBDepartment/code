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
import com.jy.pc.Entity.EduVocationInfoEntity;
import com.jy.pc.Service.EduVocationInfoService;

@Controller
@RequestMapping(value = "/vocationInfo")
public class EduVocationInfoController {
	@Autowired
	private EduVocationInfoService eduVocationInfoService;
	
	// 查询 分页
	@RequestMapping(value = "/findByName")
	@ResponseBody
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name,@RequestParam(name = "status") String status,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<EduVocationInfoEntity> vocationInfoList = eduVocationInfoService.findListByName(name, status, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", vocationInfoList);
		return map;
	}

	// 职业类别添加
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("eduVocationInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		EduVocationInfoEntity eduVocationInfoEntity = jsonObject.toJavaObject(EduVocationInfoEntity.class);
		Date date = new Date();
		eduVocationInfoEntity.setCreateDate(date);
		eduVocationInfoEntity.setStatus(1);
		eduVocationInfoEntity.setSort("5");
		eduVocationInfoService.save(eduVocationInfoEntity);
		map.put("state", "0");
		map.put("message", "添加成功");
		return map;
	}
		
	// 职业类别修改
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("eduVocationInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		EduVocationInfoEntity eduVocationInfoEntity = jsonObject.toJavaObject(EduVocationInfoEntity.class);
		Date date = new Date();
		eduVocationInfoEntity.setUpdateDate(date);
		eduVocationInfoService.update(eduVocationInfoEntity);
		map.put("state", "0");
		map.put("message", "修改成功");
		return map;
	}

	// 职业类别删除
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		eduVocationInfoService.delete(id);
		map.put("state", "0");
		map.put("message", "删除成功");
		return map;
	}
		
	//通过id查询
	@RequestMapping(value = "/findById")
	@ResponseBody
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		EduVocationInfoEntity eduVocationInfoEntity = eduVocationInfoService.findId(id);
		if (eduVocationInfoEntity != null) {
			map.put("state", "0");// 成功
			map.put("data", eduVocationInfoEntity);
		} else {
			map.put("state", "1");
		}
		return map;
	}
		
	// 启用/禁用
	@RequestMapping(value = "/enable")
	@ResponseBody
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") Integer status, @RequestParam(name = "id") String id,
			@RequestParam(name = "updateUser") String updateUser) {

		Date date = new Date();
		Map<String, String> map = new HashMap<String, String>();
		EduVocationInfoEntity eduVocationInfoEntity = eduVocationInfoService.findId(id);
		eduVocationInfoEntity.setStatus(status);
		eduVocationInfoEntity.setUpdateDate(date);
		eduVocationInfoEntity.setUpdateBy(updateUser);
		boolean result = true;
		if (status.equals(0)) {
			map.put("state", "0");
			map.put("message", "启用成功");
		}
		if (status.equals(1)) {
			map.put("state", "1");
			map.put("message", "禁用成功");
			result = false;
		}
		eduVocationInfoService.enable(eduVocationInfoEntity,result);
		return map;
	}
		
	// 修改排序
	@RequestMapping(value = "/changeSort")
	@ResponseBody
	public Map<String, String> changeSort(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id, @RequestParam(name = "sort") String sort) {

		Date date = new Date();
		Map<String, String> map = new HashMap<String, String>();
		EduVocationInfoEntity eduVocationInfoEntity = eduVocationInfoService.findId(id);
		List<EduVocationInfoEntity> vocationInfoList=eduVocationInfoService.findSort();
		for(int i=0;i<vocationInfoList.size();i++) {
			if(vocationInfoList.get(i).getSort().contains(sort) == true) {
				map.put("state", "1");
				map.put("message", "排序不能重复");
				return map;
			}
		}
		eduVocationInfoEntity.setSort(sort);
		eduVocationInfoEntity.setUpdateDate(date);
		eduVocationInfoService.changeSort(eduVocationInfoEntity);
		map.put("state", "0");
		map.put("message", "修改成功");
		return map;
	}
}
