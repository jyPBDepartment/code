package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.DbLogInfoEntity;

public interface DbLogInfoService {
	// 搜索
	public Page<DbLogInfoEntity> findListByContent(String module, String action, Pageable pageable);

	// 添加
	public DbLogInfoEntity save(DbLogInfoEntity dbLogInfoEntity);

	// 修改
	public void update(DbLogInfoEntity moduleInfo);

	// 删除
	public void delete(String id);

	// 主鍵查詢
	public DbLogInfoEntity findId(String id);
}
