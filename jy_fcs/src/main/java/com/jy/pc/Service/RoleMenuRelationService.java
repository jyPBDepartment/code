package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.RoleMenuRelationEntity;

public interface RoleMenuRelationService {

	// 添加
	public RoleMenuRelationEntity save(RoleMenuRelationEntity roleMenuRelationEntity);

	// 修改
	public void update(RoleMenuRelationEntity roleMenuRelationEntity);

	// 删除
	public void delete(String id);

	// 主鍵查詢
	public RoleMenuRelationEntity findId(String id);

	// 根据角色查询
	public List<RoleMenuRelationEntity> findRelationByRole(String roleId);

	// 根据菜单查询
	public List<RoleMenuRelationEntity> findRelationByMenu(String menuId);

	// 查询关联表数据合法性
	public int checkRealtion(String roleId, String menuId);
}
