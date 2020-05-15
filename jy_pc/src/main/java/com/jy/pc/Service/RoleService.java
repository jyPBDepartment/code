package com.jy.pc.Service;

import java.util.List;

import com.jy.pc.Entity.RoleEntity;

public interface RoleService {

	public void add(RoleEntity role);
	
	public void update(RoleEntity role);
	
	public void delete(RoleEntity role);
	
	public List<RoleEntity> findAll(RoleEntity role);
	
	public RoleEntity findById(String id);
}
