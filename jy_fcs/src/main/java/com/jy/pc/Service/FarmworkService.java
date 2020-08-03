package com.jy.pc.Service;

import java.util.List;

import com.jy.pc.Entity.FarmworkEntity;

public interface FarmworkService {
	// 农活预约总数
	public List<FarmworkEntity> findSum();

}
