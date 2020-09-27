package com.jy.pc.Service.Impl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Map<String, Object> findByName(String name, String phone, Pageable pageable) {

		Map<String, Object> map = new HashMap<String, Object>();
//		Page<AccountInfoEntity> accountInfoList = accountInfoService.findListByName(name, phone, auditStatus, pageable);
		map.put("status", "0");// 成功
		map.put("message", "查询成功");
//		map.put("data", accountInfoList);
		return map;
	}
}
