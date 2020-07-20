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
import com.jy.pc.Service.LimitService;

@Controller
@RequestMapping(value = "/limit")
@ResponseBody
public class LimitController {
	@Autowired
	private LimitService limitService;

	// 权限添加
	@RequestMapping(value = "/add")
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("limitEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		LimitEntity limitEntity = jsonObject.toJavaObject(LimitEntity.class);
		Date date = new Date();
		limitEntity.setCreateTime(date);
		limitService.save(limitEntity);
		map.put("message", "添加成功");
		return map;
	}

	// 权限修改
	@RequestMapping(value = "/update")
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("limitEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		LimitEntity limitEntity = jsonObject.toJavaObject(LimitEntity.class);
		Date date = new Date();
		limitEntity.setEditTime(date);
		limitService.update(limitEntity);
		map.put("message", "修改成功");
		return map;
	}

	// 权限删除
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		LimitEntity limitEntity = limitService.findId(id);
		List<LimitEntity> limit = limitService.findLimitId();
		for(int i = 0;i < limit.size();i++){
			LimitEntity a = limit.get(i);
		    a.getId();
		    if(limitEntity.getId().equals(a.getId())){
				limitService.delete(id);
				map.put("status", "0");
				map.put("message", "删除成功");
			} 
		    else {
//				map.put("message", "删除失败，请先删除关联关系");
			}
		}
		if(limit.size()<=0) {
//			map.put("message", "删除失败，请先删除关联关系");
		}
		return map;
	}

	// 查询所有
	@RequestMapping(value = "/findAll")
	public Map<String, Object> findAll(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String,Object>();
		List<LimitEntity> limitList = limitService.findAl();
		map.put("status", "0");
		map.put("message", "查询成功");
		map.put("data", limitList);
		return map;
	}

	// 条件查询
	@RequestMapping(value = "/findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		LimitEntity limitEntity = limitService.findId(id);
		if (limitEntity != null) {
			map.put("status", "0");
			map.put("data", limitEntity);
		} else {
			map.put("status", "1");
		}
		return map;
	}

	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<LimitEntity> limitList = limitService.findListByName(name, pageable);
		map.put("status", "0");
		map.put("message", "查询成功");
		map.put("data", limitList);
		return map;
	}

	// 启用/禁用
	@RequestMapping(value = "/enable")
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "state") String state, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		LimitEntity limitEntity = limitService.findId(id);
		limitEntity.setState(state);
		limitEntity.getState();
		if (state.equals("0")) {
			limitEntity.setState("0");
			map.put("status", "0");
			map.put("message", "启用成功");
		} else if (state.equals("1")) {
			limitEntity.setState("1");
			map.put("status", "0");
			map.put("message", "禁用成功");
		}
		limitService.update(limitEntity);
		return map;
	}
}
