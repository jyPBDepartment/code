package com.jy.pc.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.StudentDao;
import com.jy.pc.Entity.StudentEntity;
import com.jy.pc.Service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentDao studentDao;
	
	public void add(StudentEntity student) {
		
		studentDao.saveAndFlush(student);
		
	}

}
