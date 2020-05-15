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

import com.jy.pc.Entity.RespPageEntity;
import com.jy.pc.Entity.UserEntity;
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
		System.out.println(username + "登录系统" + password);
		
		
//		
//		UserEntity user = new UserEntity();
////		user.setUserId("111111");
//		user.setUserName("zgh");
//		user.setPhoneNo("18310801364");
//		user.setArea("吉林");
//		user.setUserPassword("123456");
//		userService.save(user);
		
		
//		UserEntity user = new UserEntity();
//		user.setUserName("zgh");
//		userService.delete(user);
		
		
		
		Boolean flag = userService.checkUser(username, password);
		if(flag) {
			map.put("status", "1");
			map.put("message", "登陆成功");
		}else {
			map.put("status", "0");
			map.put("message", "用户不存在");
		}

		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Cache-Control", "no-cache");
		return map;
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public static void save() {

		
		
		UserEntity user = new UserEntity();
		user.setUserName("zgh");
		user.setPhoneNo("18310801364");
//
//		res.setHeader("Access-Control-Allow-Origin", "*");
//		res.setHeader("Cache-Control", "no-cache");
//		return map;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public void delete() {
		
		UserEntity user = new UserEntity();
		user.setUserName("zgh");
		userService.delete(user);
		
//
//		res.setHeader("Access-Control-Allow-Origin", "*");
//		res.setHeader("Cache-Control", "no-cache");
//		return map;
	}
	
	public RespPageEntity getAllUserByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size) {
		
		return userService.getAllUserByPage(page, size);
	}
	
	public  static void main(String[] args) {
		
	}

}
