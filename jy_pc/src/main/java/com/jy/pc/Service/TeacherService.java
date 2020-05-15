package com.jy.pc.Service;

import org.springframework.stereotype.Service;

import com.jy.pc.Entity.TeacherEntity;
@Service
public interface TeacherService {
	
	public void add(TeacherEntity teacher);
}
