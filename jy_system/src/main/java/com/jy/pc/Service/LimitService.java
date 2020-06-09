package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.LimitEntity;

public interface LimitService {
	// 添加
		public LimitEntity save(LimitEntity limit);

		// 修改
		public void update(LimitEntity limit);

		// 删除
		public void delete(String id);

		// 查询所有
		public List<LimitEntity> findAll();

		// 按条件查询
		public LimitEntity findId(String id);
		// 搜索
		public Page<LimitEntity> findListByName(String name,Pageable pageable);
		
		public List<LimitEntity> findAl();
}
