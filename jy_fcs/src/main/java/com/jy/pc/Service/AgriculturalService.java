package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.AgriculturalEntity;

public interface AgriculturalService {
	// 农服关联Echart图
	public List<AgriculturalEntity> findAgrSum();

	// 农服添加
	public AgriculturalEntity save(AgriculturalEntity agriculturalEntity);

	// 农服findById
	public AgriculturalEntity findBId(String id);

	// 农服分页与模糊查询
	public Page<AgriculturalEntity> findListByName(String name, String status, Pageable pageable);

	// 农服状态修改
	public AgriculturalEntity update(AgriculturalEntity agriculturalEntity);

	// 农服查询标题名称
	public List<AgriculturalEntity> findListByAgrName(String name);

	// 农服查询最近三条
	public List<AgriculturalEntity> findListByTime();

	// 不同状态加载不同的发布
	public List<AgriculturalEntity> findStatusPass(String status);
	
	// 获取农服预约信息
	public List<AgriculturalEntity> findAppointment();

	//农服审核
	AgriculturalEntity audit(AgriculturalEntity agriculturalEntity,boolean result);
}
