package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.EduIssueInfoEntity;

public interface EduIssueService {
	// 搜索
	public Page<EduIssueInfoEntity> findListByParam(String name, String status, String createBy, Pageable pageable);

	// 主鍵查詢
	public EduIssueInfoEntity findInfobyId(String id);

	public Page<EduIssueInfoEntity> findMgtByParam(String userName, String card, String vocationId, Pageable pageable);
	
	//保存
	public void save(EduIssueInfoEntity entity);
}
