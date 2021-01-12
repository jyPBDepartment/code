package com.jy.pc.Service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.CaseInfoCollectionDAO;
import com.jy.pc.Entity.CaseInfoCollectionEntity;
import com.jy.pc.Service.CaseInfoCollectionService;

//看图识病收藏表 ServiceImpl
@Service
public class CaseInfoCollectionServiceImpl implements CaseInfoCollectionService{
	@Autowired
	private CaseInfoCollectionDAO caseInfoCollectionDAO;

	//添加
	@Override
	public CaseInfoCollectionEntity save(CaseInfoCollectionEntity caseInfoCollection) {
		return caseInfoCollectionDAO.saveAndFlush(caseInfoCollection);
	}

	//删除
	@Override
	public void delete(String id) {
		caseInfoCollectionDAO.deleteById(id);
	}

	//根据看图识病 点在用户id查询
	@Override
	public CaseInfoCollectionEntity findCaseUserId(String caseId, String collectionUserId) {
		return caseInfoCollectionDAO.findCaseUserId(caseId, collectionUserId);
	}

	//根据点赞用户id查询
	@Override
	public Page<CaseInfoCollectionEntity> findUserId(String collectionUserId, Pageable pageable) {
		return caseInfoCollectionDAO.findUserId(collectionUserId, pageable);
	}

	@Override
	public Page<List<Map<String, Object>>> findPageByParam(String collectionUserId, Pageable pageable) {
		return caseInfoCollectionDAO.findPageByParam(collectionUserId, pageable);
	}

}
