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
import com.jy.pc.Service.JurisdictionService;

@Controller
@RequestMapping(value = "/jurisdiction")
@ResponseBody
public class JusisdictionController {
	@Autowired
	private JurisdictionService jurisdictionService;

	// 权限添加
	@RequestMapping(value = "/add")
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "path") String path,
			@RequestParam(name = "type") Integer type) {
		JurisdictionEntity jurisdictionEntity = new JurisdictionEntity();
		Map<String, String> map = new HashMap<String, String>();
		jurisdictionEntity.setName(name);
		jurisdictionEntity.setPath(path);
		jurisdictionEntity.setType(type);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
		String time = DateFormat.getDateTimeInstance().format(new Date());
		try {
			jurisdictionEntity.setCreateTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jurisdictionService.save(jurisdictionEntity);
		map.put("message", "添加成功");
		return map;
	}

	// 权限修改
	@RequestMapping(value = "/update")
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("jurisdictionEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		JurisdictionEntity jurisdictionEntity = jsonObject.toJavaObject(JurisdictionEntity.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
		String time = DateFormat.getDateTimeInstance().format(new Date());
		try {
			jurisdictionEntity.setEditTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jurisdictionService.update(jurisdictionEntity);
		map.put("message", "修改成功");
		return map;
	}

	// 权限删除
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		jurisdictionService.delete(id);
		map.put("status", "0");
		map.put("message", "删除成功");
		return map;
	}

	// 查询所有
	@RequestMapping(value = "/findAll")
	public Map<String, Object> findAll(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<JurisdictionEntity> jurisdictionList = jurisdictionService.findAll();
		map.put("status", "0");
		map.put("message", "查询成功");
		map.put("data", jurisdictionList);
		return map;
	}

	// 条件查询
	@RequestMapping(value = "/findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		JurisdictionEntity jurisdictionEntity = jurisdictionService.findId(id);
		if (jurisdictionEntity != null) {
			map.put("status", "0");
			map.put("data", jurisdictionEntity);
		} else {
			map.put("status", "1");
		}
		return map;
	}

	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "type") Integer type,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<JurisdictionEntity> jurisdictionList = jurisdictionService.findListByName(name, type, pageable);
		map.put("status", "0");
		map.put("message", "查询成功");
		map.put("data", jurisdictionList);
		return map;
	}

	// 启用/禁用
	@RequestMapping(value = "/enable")
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "state") Integer state, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		JurisdictionEntity jurisdictionEntity = jurisdictionService.findId(id);
		jurisdictionEntity.setState(state);
		jurisdictionEntity.getState();
		if (state.equals(0)) {
			jurisdictionEntity.setState(1);
			map.put("status", "0");
			map.put("message", "禁用成功");
		} else if (state.equals(1)) {
			jurisdictionEntity.setState(0);
			map.put("status", "0");
			map.put("message", "启用成功");
		}
		jurisdictionService.update(jurisdictionEntity);
		return map;
	}
}
