package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jy.pc.Entity.RoleEntity;

public interface RoleDao extends JpaRepository<RoleEntity, String>{

}
