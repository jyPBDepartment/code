package com.jy.pc.Service;

import java.util.List;

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
	
	//根据分类代码查询关键字
	public List<KeyWordEntity> findListByClass(String classCode);

	//启用 禁用 状态切换
	void enable(KeyWordEntity keyWord, boolean result);

}
