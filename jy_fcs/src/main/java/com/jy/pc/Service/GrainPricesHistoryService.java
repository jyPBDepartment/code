package com.jy.pc.Service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.GrainPricesHistoryEntity;

public interface GrainPricesHistoryService {

	public GrainPricesHistoryEntity saveOrUpdate(GrainPricesHistoryEntity grainPricesHistoryEntity);

	public void delete(String id);

	public GrainPricesHistoryEntity findInfoById(String id);

	// 根据参数查询 分页
	public Map<String, Object> findByName(String name, String phone, Pageable pageable);
}
