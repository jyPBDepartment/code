package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.EduFormworkEntity;

public interface EduFormworkService {
	// 搜索
	public Page<EduFormworkEntity> findListByParam(String name, String status, String createBy, Pageable pageable);

	// 添加
	public EduFormworkEntity save(EduFormworkEntity entity);

	// 修改
	public void update(EduFormworkEntity entity);

	// 删除
	public void delete(String id);

	// 主鍵查詢
	public EduFormworkEntity findInfobyId(String id);

	// 切换生效状态
	public void enable(EduFormworkEntity entity, boolean result);
}
