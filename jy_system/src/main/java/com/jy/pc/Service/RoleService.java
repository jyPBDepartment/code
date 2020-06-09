package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.LimitEntity;
import com.jy.pc.Entity.RoleEntity;

public interface RoleService {
	// 添加
	public RoleEntity save(RoleEntity role);

	// 修改
	public void update(RoleEntity role);

	// 删除
	public void delete(String id);

	// 查询所有
	public List<RoleEntity> findAll();

	// 按条件查询
	public RoleEntity findId(String id);

	// 搜索
	public Page<RoleEntity> findListByName(String name, Pageable pageable);
	
	public List<RoleEntity> findAl();
}
