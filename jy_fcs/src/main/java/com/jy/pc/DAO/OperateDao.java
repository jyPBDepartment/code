package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jy.pc.Entity.OperateEntity;

public interface OperateDao extends JpaRepository<OperateEntity, String> {
	
}
