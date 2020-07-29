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

import com.jy.pc.Entity.MenuEntity;
import com.jy.pc.Service.MenuService;

@Controller
@RequestMapping(value = "/menu")
@ResponseBody
public class MenuController {
	@Autowired
	private MenuService menuService;
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<MenuEntity> roleList = menuService.findListByName(name, pageable);
		map.put("status", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", roleList);
		return map;
	}

	// 主键查询
	@RequestMapping(value = "/findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		MenuEntity menuEntity = menuService.findId(id);
		if (menuEntity != null) {
			map.put("status", "0");
			map.put("data", menuEntity);
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
		MenuEntity menuEntity  = menuService.findId(id);
		Date date = new Date();
		if (status.equals("0")) {
			map.put("status", "0");
			map.put("message", "启用成功");
		} else if (status.equals("1")) {
			map.put("status", "1");
			map.put("message", "禁用成功");
		}
		menuEntity.setStatus(status);
		menuEntity.setUpdDate(date);
		menuService.update(menuEntity);
		return map;
	}
}
