package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.ModuleInfoEntity;

public interface ModuleInfoService {
	// 搜索
	public Page<ModuleInfoEntity> findListByName(String name, String status, Pageable pageable);

	// 添加
	public ModuleInfoEntity save(ModuleInfoEntity moduleInfo);

	// 修改
	public void update(ModuleInfoEntity moduleInfo);

	// 删除
	public void delete(String id);

	// findbyid
	public ModuleInfoEntity findId(String id);

	// 根据模块名称查询非禁用模块名称
	public List<ModuleInfoEntity> findModuleListByName(String name);

	// 查询模块表所有非禁用信息
	List<ModuleInfoEntity> findModuleOn();

	// 查询所有有效的模块信息
	public List<ModuleInfoEntity> findListByMobile();

	//农服状态切换
	void enable(ModuleInfoEntity moduleInfo, boolean result);
}
