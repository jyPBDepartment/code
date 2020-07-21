package com.jy.pc.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jy.pc.Entity.WebsiteInfoEntity;
import com.jy.pc.Service.WebsiteInfoService;

@Controller
@RequestMapping(value = "/websiteInfo")
@ResponseBody
public class WebsiteInfoController {
	@Autowired
	private WebsiteInfoService websiteInfoService;
	//编辑网站信息
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("websiteInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		WebsiteInfoEntity websiteInfoEntity = jsonObject.toJavaObject(WebsiteInfoEntity.class);
		websiteInfoService.update(websiteInfoEntity);
		map.put("message", "修改成功");
		return map;
	}
		
	@RequestMapping(value = "/findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		WebsiteInfoEntity websiteInfoEntity=new WebsiteInfoEntity();
		websiteInfoEntity = websiteInfoService.findId();
		if (websiteInfoEntity != null) {
			map.put("status", "0");
			map.put("data", websiteInfoEntity);
		} else {
			map.put("status", "1");
		}
		return map;
	}
}
