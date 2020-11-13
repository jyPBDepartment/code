package com.jy.pc.Service;

import java.util.List;

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
	
	//根据职业类别和通过状态获取信息
	public List<EduIssueInfoEntity> findInfoByVocation(String vocationId,String status);
	
	public List<EduIssueInfoEntity> findInfoByUserId(String userId);
}
