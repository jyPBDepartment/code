package com.jy.pc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@RequestMapping(value = "/login")
	@ResponseBody
	public Map<String, String> login(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {

		Map<String, String> map = new HashMap<String, String>();
		System.out.println(username + "登录系统" + password);
		map.put("status", "1");
		map.put("message", "登陆成功");

		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Cache-Control", "no-cache");
		return map;
	}

}
