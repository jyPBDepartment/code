package com.jy.pc.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.pc.Service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login")
	@ResponseBody
	public Map<String, String> login(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {

		Map<String, String> map = new HashMap<String, String>();
		Boolean flag = userService.checkUser(username, password);
		if (flag) {
			map.put("status", "1");
			map.put("message", "登陆成功");
		} else {
			map.put("status", "0");
			map.put("message", "用户不存在或密码错误");
		}
		return map;
	}
}
