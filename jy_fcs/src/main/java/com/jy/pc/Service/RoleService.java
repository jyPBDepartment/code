package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.RoleEntity;

public interface RoleService {
	// 搜索
	public Page<RoleEntity> findListByName(String name, Pageable pageable);

	// 添加
	public RoleEntity save(RoleEntity roleEntity);

	// 修改
	public void update(RoleEntity roleEntity);

	// 删除
	public void delete(String id);

	// 主鍵查詢
	public RoleEntity findId(String id);

	// 切换启用禁用状态
	void enable(RoleEntity roleEntity, boolean result);
}
