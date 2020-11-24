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
import com.jy.pc.Entity.SectionManageEntity;
import com.jy.pc.Service.SectionManageService;

/**
 * 版块管理controller
 */
@Controller
@RequestMapping(value = "/sectionManage")
public class SectionManageController {
	@Autowired
	private SectionManageService eduSectionManageService;

	// 查询版块管理分页
	@RequestMapping(value = "/findByName")
	@ResponseBody
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<SectionManageEntity> sectionManage = eduSectionManageService.findListByName(name, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", sectionManage);
		return map;
	}

	// 添加版块信息
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();

		String s = res.getParameter("eduSectionManageEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		SectionManageEntity eduSectionManageEntity = jsonObject.toJavaObject(SectionManageEntity.class);
		Date date = new Date();
		eduSectionManageEntity.setCreateDate(date);
		eduSectionManageEntity.setStatus(1);
		eduSectionManageService.save(eduSectionManageEntity);
		try {
			map.put("state", "0");
			map.put("message", "添加成功");
		} catch (Exception e) {
			map.put("state", "1");
			map.put("message", "添加失败");
		}
		return map;
	}

	// 修改版块信息
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("eduSectionManageEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		SectionManageEntity eduSectionManageEntity = jsonObject.toJavaObject(SectionManageEntity.class);
		Date date = new Date();
		eduSectionManageEntity.setUpdateDate(date);
		eduSectionManageEntity.setStatus(1);
		eduSectionManageService.update(eduSectionManageEntity);
		try {
			map.put("state", "0");
			map.put("message", "修改成功");
		} catch (Exception e) {
			map.put("state", "1");
			map.put("message", "修改失败");
		}
		return map;
	}

	// 删除版块信息
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		eduSectionManageService.delete(id);
		try {
			map.put("state", "0");
			map.put("message", "删除成功");
		} catch (Exception e) {
			map.put("state", "1");
			map.put("message", "删除失败");
		}
		return map;
	}

	// 通过id查询版块信息
	@RequestMapping(value = "/findById")
	@ResponseBody
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		SectionManageEntity eduSectionManageEntity = eduSectionManageService.findBId(id);
		map.put("state", "0");
		map.put("data", eduSectionManageEntity);
		return map;
	}

	// 启用/禁用版块信息
	@RequestMapping(value = "/enable")
	@ResponseBody
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") Integer status, @RequestParam(name = "id") String id,
			@RequestParam(name = "updateBy") String updateBy) {
		Date date = new Date();
		Map<String, String> map = new HashMap<String, String>();
		SectionManageEntity eduSectionManageEntity = eduSectionManageService.findBId(id);
		eduSectionManageEntity.setStatus(status);
		eduSectionManageEntity.setUpdateDate(date);
		eduSectionManageEntity.setUpdateBy(updateBy);
		boolean result = true;
		// 启用
		if (status.equals(0)) {
			map.put("state", "0");
			map.put("message", "启用成功");
		}
		// 禁用
		if (status.equals(1)) {
			map.put("state", "1");
			map.put("message", "禁用成功");
			result = false;
		}
		eduSectionManageService.enable(eduSectionManageEntity, result);
		return map;
	}

	// 动态获取版块管理下拉列表内容
	@RequestMapping(value = "/findListName")
	@ResponseBody
	public Map<String, Object> findListName(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SectionManageEntity> SectionMan = eduSectionManageService.findListName();
		map.put("state", "0");
		map.put("data", SectionMan);
		return map;
	}
}
