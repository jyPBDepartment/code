package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.KeyWordEntity;

public interface KeyWordService {
	// 搜索
	public Page<KeyWordEntity> findListByName(String name,Pageable pageable);

	// 添加
	public KeyWordEntity save(KeyWordEntity keyWord);

	// 修改
	public void update(KeyWordEntity keyWord);

	// 删除
	public void delete(String id);

	// findbyid
	public KeyWordEntity findId(String id);

}
