package com.jy.pc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jy.pc.Entity.StudentEntity;
import com.jy.pc.Service.StudentService;
import com.jy.pc.Service.impl.StudentServiceImpl;

/**
 * 学生控制层
 */
@Controller
@RequestMapping(value="/student")
public class StudentController {
	
	
	private StudentService studentService;
	
	//新增学生
	@RequestMapping(value="/add")
	public void add(HttpServletRequest req, HttpServletResponse res,@RequestAttribute(name="student")StudentEntity student) {
		studentService = new StudentServiceImpl();
		studentService.add(student);
		
	}
	
	//修改学生
	@RequestMapping(value="update")
	public void update() {

	}

	//删除学生
	public void delete() {

	}

	// 查询所有
	public void findAll() {

	}
	
	//根据条件查询所有列表信息
	public void findAllById() {

	}
}
