package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.SectionManageEntity;

/**
 * 版块管理Service
 */
public interface SectionManageService {

	// 分页与模糊查询（版块信息）
	public Page<SectionManageEntity> findListByName(String name, Pageable pageable);

	// 通过id查询版块信息
	public SectionManageEntity findBId(String id);

	// 添加版块信息
	public SectionManageEntity save(SectionManageEntity eduSectionManageEntity);

	// 修改版块信息
	public void update(SectionManageEntity eduSectionManageEntity);

	// 删除版块信息
	public void delete(String id);

	// 调整版块信息状态
	void enable(SectionManageEntity eduSectionManageEntity, boolean result);
	
	//动态获取版块管理下拉列表内容
	public List<SectionManageEntity> findListName();
}
