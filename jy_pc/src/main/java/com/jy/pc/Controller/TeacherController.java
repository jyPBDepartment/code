package com.jy.pc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jy.pc.Entity.TeacherEntity;
import com.jy.pc.Service.TeacherService;
@Controller
@RequestMapping(value="teacher")

public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	//新增教师
	@RequestMapping(value="add")
	private void add(HttpServletRequest req,HttpServletResponse res) 
	{
		System.out.println("添加方法");
		
		TeacherEntity t = new TeacherEntity();
		t.setName("stephen zhou");
		t.setAge(2);
		t.setTClass("一年级1班");
		t.setWage("4300");
		
		teacherService.add(t);
	}
	private void update(){
		
	}
}
