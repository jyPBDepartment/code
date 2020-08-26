package com.jy.pc.Service;

import java.util.List;

import com.jy.pc.Entity.FarmworkEntity;

public interface FarmworkService {
	// 农活预约总数
	public List<FarmworkEntity> findSum();

	// 农活预约添加
	public FarmworkEntity save(FarmworkEntity farmworkEntity);

	//根据id获取实体
	public FarmworkEntity findById(String id);

}
