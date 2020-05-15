package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jy.pc.Entity.StudentEntity;

public interface StudentDao extends JpaRepository<StudentEntity,Long>{
	
//	public void add(StudentEntity student);

}
