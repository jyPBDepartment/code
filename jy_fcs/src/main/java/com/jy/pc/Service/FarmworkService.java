package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.AccountInfoEntity;
import com.jy.pc.Entity.AgriculturalEntity;
import com.jy.pc.Entity.FarmworkEntity;

public interface FarmworkService {
	// 农活预约总数
	public List<FarmworkEntity> findSum();

	// 农活预约添加
	public FarmworkEntity save(FarmworkEntity farmworkEntity);

	//根据id获取实体
	public FarmworkEntity findById(String id);

	public Page<FarmworkEntity> findMyFarm(String userId,String status, String user, Pageable pageable);

	public Page<FarmworkEntity> findFarmForMe(String userId,String status, String user, Pageable pageable);
	
	//干活天数
	public String findDay(String id);
	
	// 修改
	public void update(FarmworkEntity farmworkEntity);
	

}
