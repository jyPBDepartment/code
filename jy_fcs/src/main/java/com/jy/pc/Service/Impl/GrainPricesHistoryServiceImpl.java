package com.jy.pc.Service.Impl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.GrainPricesDAO;
import com.jy.pc.DAO.GrainPricesHistoryDAO;
import com.jy.pc.Entity.GrainPricesHistoryEntity;
import com.jy.pc.Service.GrainPricesHistoryService;

@Service
@Transactional
public class GrainPricesHistoryServiceImpl implements GrainPricesHistoryService{

	@Autowired
	private GrainPricesHistoryDAO grainPricesHistoryDAO;
	
	public GrainPricesHistoryEntity saveOrUpdate(GrainPricesHistoryEntity grainPricesEntity){
		return grainPricesHistoryDAO.save(grainPricesEntity);
	}
	
	
	public void delete(String id){
		grainPricesHistoryDAO.deleteById(id);
		
	}
	
	
	public GrainPricesHistoryEntity findInfoById(String id){
		return grainPricesHistoryDAO.findInfoById(id);
	}
	
	// 根据参数查询 分页
	public Page<GrainPricesHistoryEntity> findPageByParam(String operateType, Pageable pageable){

		return grainPricesHistoryDAO.findPageByParam(operateType,pageable);
	}
}
