package com.jy.pc.Controller;

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

import com.jy.pc.Entity.LimitEntity;
import com.jy.pc.Entity.MenuEntity;
import com.jy.pc.Service.MenuService;

@Controller
@RequestMapping(value="/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	
	@RequestMapping(value="add")
	@ResponseBody
	public Map<String,Object> save(){
		Map<String,Object> map = new HashMap<String,Object>();
		return map;
		
	}
	
	@RequestMapping(value = "/findByName")
	@ResponseBody
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<MenuEntity> menuList = menuService.findListByName(name, pageable);
		map.put("status", "0");
		map.put("message", "查询成功");
		map.put("data", menuList);
		return map;
	}
	
	
	

}
