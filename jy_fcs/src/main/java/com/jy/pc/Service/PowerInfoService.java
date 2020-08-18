package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.PowerInfoEntity;

public interface PowerInfoService {
	// 权限添加
	public PowerInfoEntity save(PowerInfoEntity powerInfoEntity);

	// 权限修改
	public PowerInfoEntity update(PowerInfoEntity powerInfoEntity);

	// 权限删除
	public void delete(String id);

	// 权限findById
	public PowerInfoEntity findBId(String id);

	// 权限分页与模糊查询
	public Page<PowerInfoEntity> findListByName(String jurName, String jurCode, Pageable pageable);

	// findAll
	public List<PowerInfoEntity> findAll();

	// 上级编码
	public List<PowerInfoEntity> findSubPowerList();

	// 账户关联
	public List<PowerInfoEntity> findCount();

	// 删除前验证
	public List<PowerInfoEntity> findAccountLink();

	// 查询是否有子菜单
	public boolean findJurCode(String subJurCode);
	
	//查询子菜单
	public List<PowerInfoEntity> findListById(String id);

	//切换启用、禁用状态
	PowerInfoEntity enable(PowerInfoEntity powerInfoEntity, boolean result);

}
