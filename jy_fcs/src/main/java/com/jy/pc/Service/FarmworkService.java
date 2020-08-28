package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.FarmworkEntity;

public interface FarmworkService {
	// 农活预约总数
	public List<FarmworkEntity> findSum();

	// 农活预约添加
	public FarmworkEntity save(FarmworkEntity farmworkEntity);

	//根据id获取实体
	public FarmworkEntity findById(String id);

	public Page<FarmworkEntity> findMyFarm(String userId, String user, Pageable pageable);

	public Page<FarmworkEntity> findFarmForMe(String userId, String user, Pageable pageable);

}
