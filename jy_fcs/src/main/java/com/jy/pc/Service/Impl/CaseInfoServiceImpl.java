package com.jy.pc.Service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jy.pc.DAO.CaseInfoDao;
import com.jy.pc.DAO.CaseKeyDAO;
import com.jy.pc.DAO.ClassificationDao;
import com.jy.pc.DAO.KeyWordDao;
import com.jy.pc.Entity.CaseInfoEntity;
import com.jy.pc.Entity.CaseKeyEntity;
import com.jy.pc.Entity.ClassificationEntity;
import com.jy.pc.Entity.KeyWordEntity;
import com.jy.pc.Service.CaseInfoService;
import com.jy.pc.Utils.Aes;
import com.jy.pc.Utils.DbLogUtil;
import com.mysql.cj.util.StringUtils;
//看图识病表 ServiceImpl
@Service
public class CaseInfoServiceImpl implements CaseInfoService {

	@Autowired
	public CaseInfoDao caseInfoDao;
	@Autowired
	public KeyWordDao keyWordDao;
	@Autowired
	public CaseKeyDAO caseKeyDAO;
	@Autowired
	public ClassificationDao  classificationDao;
	@Autowired
	private DbLogUtil logger;

	// 看图识病添加
	@Override
	@Transactional
	public CaseInfoEntity save(CaseInfoEntity caseInfoEntity) {
		CaseInfoEntity result = caseInfoDao.saveAndFlush(caseInfoEntity);
		logger.initAddLog(caseInfoEntity);
		return result;
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

	// 切换启用禁用状态
	@Override
	@Transactional
	public CaseInfoEntity enable(CaseInfoEntity caseInfoEntity,boolean result) {
		logger.initEnableLog(caseInfoEntity,result);
		return caseInfoDao.saveAndFlush(caseInfoEntity);
	}

	// 看图识病修改
	@Override
	@Transactional
	public CaseInfoEntity update(CaseInfoEntity caseInfoEntity) {
		logger.initUpdateLog(caseInfoEntity);
		return caseInfoDao.saveAndFlush(caseInfoEntity);
	}

	// 看图识病删除
	@Override
	@Transactional
	public void delete(String id) {
		logger.initDeleteLog(caseInfoDao.findBId(id));
		caseInfoDao.deleteById(id);

	}

	// 查询所有病虫害信息的最新3条记录
	@Override
	public List<CaseInfoEntity> findCaseInfo() {
		return caseInfoDao.findCaseInfo();
	}

	// 添加(级联添加关键词)
	@Override
	@Transactional
	public CaseInfoEntity saveWithKeyword(CaseInfoEntity caseInfoEntity, String keywords) {
		caseInfoDao.save(caseInfoEntity);
		logger.initAddLog(caseInfoEntity);
		if (!StringUtils.isNullOrEmpty(keywords)) {
			String[] arr = keywords.split(",");
			for (String item : arr) {
				CaseKeyEntity entity = new CaseKeyEntity();
				entity.setKeyId(item);
				entity.setCaseId(caseInfoEntity.getId());
				caseKeyDAO.save(entity);
			}
		}
		return caseInfoEntity;
	}

	@Transactional
	// 修改(级联添加关键词)
	public CaseInfoEntity updateWithKeyword(CaseInfoEntity caseInfoEntity, String keywords) {
		logger.initUpdateLog(caseInfoEntity);
		caseInfoDao.save(caseInfoEntity);
		caseKeyDAO.deleteByCase(caseInfoEntity.getId());
		if (!StringUtils.isNullOrEmpty(keywords)) {
			String[] arr = keywords.split(",");
			for (String item : arr) {
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

	@Override
	public Page<CaseInfoEntity> findPage(String name, String cropsTypeCode, String dipTypeCode, Pageable pageable) {
		String caseName = "".equals(name)? "":"%" + name + "%";
		return caseInfoDao.findPage(caseName,cropsTypeCode,dipTypeCode, pageable);
	}

	@Override
	public CaseInfoEntity updateCase(CaseInfoEntity caseInfoEntity,HttpServletRequest res,HttpServletResponse req) {
		
		Aes aes = new Aes();
		String s = "";
		String temp = res.getParameter("caseInfoEntity");
		try {
			s = aes.desEncrypt(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.parseObject(s);
		Date date = new Date();
		caseInfoEntity = jsonObject.toJavaObject(CaseInfoEntity.class);
		caseInfoEntity.setUpdateDate(date);

		ClassificationEntity classificationEntity = classificationDao.findBId(caseInfoEntity.getClassiCode());
		caseInfoEntity.setCropsTypeCode(classificationEntity.getName());

		ClassificationEntity classification = classificationDao.findBId(caseInfoEntity.getClassiDipCode());
		caseInfoEntity.setDipTypeCode(classification.getName());
		String keywords = jsonObject.getString("keys");
		caseInfoDao.save(caseInfoEntity);
		caseKeyDAO.deleteByCase(caseInfoEntity.getId());
		if (!StringUtils.isNullOrEmpty(keywords)) {
			String[] arr = keywords.split(",");
			for (String item : arr) {
				CaseKeyEntity entity = new CaseKeyEntity();
				entity.setKeyId(item);
				entity.setCaseId(caseInfoEntity.getId());
				caseKeyDAO.save(entity);
			}
		}
		return caseInfoEntity;
	}

	@Override
	public CaseInfoEntity saveCase(CaseInfoEntity caseInfoEntity,String caseInfo) {
		
		
		JSONObject jsonObject = JSONObject.parseObject(caseInfo);
		Date date = new Date();
		caseInfoEntity = jsonObject.toJavaObject(CaseInfoEntity.class);
		caseInfoEntity.setCreateDate(date);
		caseInfoEntity.setAuditStatus("0");
		caseInfoEntity.setIsSelected(1);

		ClassificationEntity classificationEntity = classificationDao.findBId(caseInfoEntity.getClassiCode());
		caseInfoEntity.setCropsTypeCode(classificationEntity.getName());

		ClassificationEntity classification = classificationDao.findBId(caseInfoEntity.getClassiDipCode());
		caseInfoEntity.setDipTypeCode(classification.getName());

		String keywords = jsonObject.getString("keys");
		
//			caseInfoDao.saveWithKeyword(caseInfoEntity, keywords);
			caseInfoDao.save(caseInfoEntity);
			if (!StringUtils.isNullOrEmpty(keywords)) {
				String[] arr = keywords.split(",");
				for (String item : arr) {
					CaseKeyEntity entity = new CaseKeyEntity();
					entity.setKeyId(item);
					entity.setCaseId(caseInfoEntity.getId());
					caseKeyDAO.save(entity);
				}
			}
			return caseInfoEntity;
		
	}

	//是否设为精选
	@Override
	public CaseInfoEntity setSelect(CaseInfoEntity caseInfoEntity, boolean result) {
		return caseInfoDao.saveAndFlush(caseInfoEntity);
	}

	//设为与我无关
	@Override
	public CaseInfoEntity setIsIrrelevant(CaseInfoEntity caseInfoEntity, boolean result) {
		return caseInfoDao.saveAndFlush(caseInfoEntity);
	}

	//看图识病按类型查询
	@Override
	public Page<CaseInfoEntity> findByNum(Integer type,Pageable pageable) {
		Page<CaseInfoEntity> caseInfoEntity = null;
		switch (type) {
		case 0:
			caseInfoEntity = caseInfoDao.findByBowNum(pageable); // 最火
			break;
		case 1:
			caseInfoEntity = caseInfoDao.findByNewNum(pageable); // 最新
			break;
		case 2:
			caseInfoEntity = caseInfoDao.findBySelected(pageable); // 精选
			break;
		case 3:
			caseInfoEntity = caseInfoDao.findByCommNum(pageable); // 热议
			break;
		case 4:
			caseInfoEntity = caseInfoDao.findByPraNum(pageable); // 好评
			break;
		}
		return caseInfoEntity;
	}

	//通过id userId查询看图识病信息
	@Override
	public Map<String, Object> findInfoByUserId(String id, String userId) {
		return caseInfoDao.findInfoByUserId(id, userId);
	}

	@Override
	public Page<List<Map<String, Object>>> findCaseInfo(String name, String cropsTypeCode, String dipTypeCode,
			String sort, String userId, Pageable pageable) {
		String caseName = "".equals(name) ? "" : "%" + name + "%";
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		list.add(userId);
		map.put("userId", list);
		return caseInfoDao.findCaseInfo(caseName, cropsTypeCode, dipTypeCode, sort, map, pageable);
	}

	// 看图识病查看详情H5
	@Override
	public Map<String, Object> findChannelId(String userId, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		list.add(userId);
		map.put("userId", list);
		return caseInfoDao.findChannelId(map, id);
	}
}
