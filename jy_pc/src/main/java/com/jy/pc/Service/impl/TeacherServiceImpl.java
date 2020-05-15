package com.jy.pc.Service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.TeacherDao;
import com.jy.pc.Entity.TeacherEntity;
import com.jy.pc.Service.TeacherService;
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService{
	@Autowired
	private TeacherDao teacherDao;
	
	public void add(TeacherEntity teacher) {
		teacherDao.save(teacher);
	}
}
