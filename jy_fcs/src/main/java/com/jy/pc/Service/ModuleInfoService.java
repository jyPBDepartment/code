package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.ModuleInfoEntity;

public interface ModuleInfoService {
	// 搜索
	public Page<ModuleInfoEntity> findListByName(String name,Pageable pageable);
	// 添加
	public ModuleInfoEntity save(ModuleInfoEntity moduleInfo);

	// 修改
	public void update(ModuleInfoEntity moduleInfo);

	// 删除
	public void delete(String id);
	//findbyid
	public ModuleInfoEntity findId(String id);
}
