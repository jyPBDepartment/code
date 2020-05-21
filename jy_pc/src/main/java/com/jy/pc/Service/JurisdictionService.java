package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.JurisdictionEntity;

public interface JurisdictionService {
	// 添加
	public void save(JurisdictionEntity jurisdiction);

	// 修改
	public void update(JurisdictionEntity jurisdiction);

	// 删除
	public void delete(String id);

	// 查询所有
	public List<JurisdictionEntity> findAll();

	// 按条件查询
	public JurisdictionEntity findId(String id);

	// 搜索
	public List<JurisdictionEntity> findListByName(String name, Integer type,Pageable pageable);
}
