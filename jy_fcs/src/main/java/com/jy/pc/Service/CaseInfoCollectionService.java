package com.jy.pc.Service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.CaseInfoCollectionEntity;

// 看图识病收藏表 Service
public interface CaseInfoCollectionService {
	
	// 添加
	public CaseInfoCollectionEntity save(CaseInfoCollectionEntity caseInfoCollection);
	// 删除
	public void delete(String id);
	//根据点赞用户id查询
	public Page<CaseInfoCollectionEntity> findUserId(String collectionUserId, Pageable pageable);
	
	//根据点赞用户 看图识病id查询
	public CaseInfoCollectionEntity findCaseUserId(String caseId, String collectionUserId);
	
	public Page<List<Map<String, Object>>> findPageByParam(String collectionUserId, Pageable pageable);

}
