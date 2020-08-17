package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.CaseInfoDao;
import com.jy.pc.DAO.CaseKeyDAO;
import com.jy.pc.DAO.KeyWordDao;
import com.jy.pc.Entity.CaseInfoEntity;
import com.jy.pc.Entity.CaseKeyEntity;
import com.jy.pc.Entity.KeyWordEntity;
import com.jy.pc.Service.CaseInfoService;
import com.mysql.cj.util.StringUtils;

@Service
public class CaseInfoServiceImpl implements CaseInfoService {

	@Autowired
	public CaseInfoDao caseInfoDao;
	@Autowired
	public KeyWordDao keyWordDao;
	@Autowired
	public CaseKeyDAO caseKeyDAO;

	// 看图识病添加
	@Override
	public CaseInfoEntity save(CaseInfoEntity caseInfoEntity) {

		return caseInfoDao.saveAndFlush(caseInfoEntity);
	}

	// 看图识病查找
	@Override
	public CaseInfoEntity findBId(String id) {
		CaseInfoEntity caseEntity = caseInfoDao.findBId(id);
		List<KeyWordEntity> keys = keyWordDao.findKeyByCase(id);
		caseEntity.setKeyCodes(keys);
		return caseEntity;
	}

	// 看图识病分页与模糊查询
	@Override
	public Page<CaseInfoEntity> findListByName(String name, String auditStatus, Pageable pageable) {
		String caseName = "%" + name + "%";
		String status = "%" + auditStatus + "%";
		return caseInfoDao.findListByName(caseName, status, pageable);
	}

	// 看图识病修改
	@Override
	public CaseInfoEntity update(CaseInfoEntity caseInfoEntity) {

		return caseInfoDao.saveAndFlush(caseInfoEntity);
	}

	// 看图识病删除
	@Override
	public void delete(String id) {
		caseInfoDao.deleteById(id);

	}

	//查询所有病虫害信息的最新3条记录
	@Override
	public List<CaseInfoEntity> findCaseInfo() {
		return caseInfoDao.findCaseInfo();
	}

	//添加(级联添加关键词)
	@Override
	@Transactional
	public CaseInfoEntity saveWithKeyword(CaseInfoEntity caseInfoEntity, String keywords) {
		caseInfoDao.save(caseInfoEntity);
		if(!StringUtils.isNullOrEmpty(keywords)) {
			String[] arr = keywords.split(",");
			for(String item : arr) {
				CaseKeyEntity entity = new CaseKeyEntity();
				entity.setKeyId(item);
				entity.setCaseId(caseInfoEntity.getId());
				caseKeyDAO.save(entity);
			}
		}
		return caseInfoEntity;
	}
	
	@Transactional
	//修改(级联添加关键词)
	public CaseInfoEntity updateWithKeyword(CaseInfoEntity caseInfoEntity, String keywords) {
		caseInfoDao.save(caseInfoEntity);
		caseKeyDAO.deleteByCase(caseInfoEntity.getId());
		if(!StringUtils.isNullOrEmpty(keywords)) {
			String[] arr = keywords.split(",");
			for(String item : arr) {
				CaseKeyEntity entity = new CaseKeyEntity();
				entity.setKeyId(item);
				entity.setCaseId(caseInfoEntity.getId());
				caseKeyDAO.save(entity);
			}
		}
		return caseInfoEntity;
	}

	// 关键字搜索病虫害信息
	@Override
	public List<CaseInfoEntity> findCaseInfoByKey(String name) {
		String caseName = "%" + name + "%";
		return caseInfoDao.findCaseInfoByKey(caseName);
	}
}
