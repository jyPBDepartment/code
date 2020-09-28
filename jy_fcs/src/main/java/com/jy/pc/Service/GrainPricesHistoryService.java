package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.GrainPricesHistoryEntity;

public interface GrainPricesHistoryService {

	public GrainPricesHistoryEntity saveOrUpdate(GrainPricesHistoryEntity grainPricesHistoryEntity);

	public void delete(String id);

	public GrainPricesHistoryEntity findInfoById(String id);

	// 根据参数查询 分页
	public Page<GrainPricesHistoryEntity> findPageByParam(String startDate,String endDate,String operateType, Pageable pageable);
}
