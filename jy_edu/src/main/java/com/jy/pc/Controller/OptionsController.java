package com.jy.pc.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jy.pc.Entity.EduVocationInfoEntity;
import com.jy.pc.Service.EduVocationInfoService;

/**
 * 选项型数据统一交互控制类 用于单选、多选、下拉选、tab标签等
 * 
 * @author admin
 *
 */
@RequestMapping(value = "option")
@RestController
public class OptionsController {
	@Autowired
	private EduVocationInfoService eduVocationInfoService;

	// 获取所有职业类别
	@RequestMapping(value = "/findVocation")
	public Map<String, Object> findVocation(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<EduVocationInfoEntity> list = eduVocationInfoService.findVocationId();
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", list);
		return map;
	}
}
