package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.ExplanationEntity;

public interface ExplanstionService {
	// 添加
	public ExplanationEntity save(ExplanationEntity explanation);

	// 搜索
	public Page<ExplanationEntity> findListByName(String name, String phoneNum, Pageable pageable);
}
