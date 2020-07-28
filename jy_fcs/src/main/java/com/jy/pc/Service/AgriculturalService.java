package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.AgriculturalEntity;
public interface AgriculturalService {
	//echart
	public List<AgriculturalEntity> findAgrSum();
	//农服添加
	public AgriculturalEntity save(AgriculturalEntity agriculturalEntity);
	//农服findById
	public AgriculturalEntity findBId(String id);
	//农服分页与模糊查询
	public Page<AgriculturalEntity> findListByName(String name,String status,Pageable pageable);
	//农服状态修改
	public AgriculturalEntity update(AgriculturalEntity agriculturalEntity);
	
}
