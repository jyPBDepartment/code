package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jy.pc.Entity.TeacherEntity;

public interface TeacherDao extends JpaRepository<TeacherEntity, Long>{
//	public void add(TeacherEntity teacher);
}
