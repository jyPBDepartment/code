package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.GrainPricesEntity;

public interface GrainPricesService {

	public GrainPricesEntity saveOrUpdate(GrainPricesEntity grainPricesEntity);

	public void delete(String id);

	public GrainPricesEntity findInfoById(String id);

	// 根据参数查询 分页
	public Page<GrainPricesEntity> findPageByParam(String priceDefinedType,Pageable pageable);
	
	public List<GrainPricesEntity> findListByType(String type);
	
	

}
