package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.SectionManageDao;
import com.jy.pc.Entity.SectionManageEntity;
import com.jy.pc.Service.SectionManageService;
import com.jy.pc.Utils.DbLogUtil;

/**
 * 版块管理ServiceImpl
 */
@Service
public class SectionManageServiceImpl implements SectionManageService {
	@Autowired
	private SectionManageDao eduSectionManageDao;
	@Autowired
	DbLogUtil logger;

	// 分页与模糊查询（版块信息）
	@Override
	public Page<SectionManageEntity> findListByName(String name, Pageable pageable) {
		String sectionManageName = "%" + name + "%";
		return eduSectionManageDao.findListByName(sectionManageName, pageable);
	}

	// 通过id查询（版块信息）
	@Override
	public SectionManageEntity findBId(String id) {
		return eduSectionManageDao.findBId(id);
	}

	// 添加版块信息
	@Override
	public SectionManageEntity save(SectionManageEntity eduSectionManageEntity) {
		logger.initAddLog(eduSectionManageEntity);
		return eduSectionManageDao.saveAndFlush(eduSectionManageEntity);

	}

	// 修改版块信息
	@Override
	public void update(SectionManageEntity eduSectionManageEntity) {
		logger.initUpdateLog(eduSectionManageEntity);
		eduSectionManageDao.saveAndFlush(eduSectionManageEntity);

	}

	// 删除版块信息
	@Override
	public void delete(String id) {
		logger.initDeleteLog(eduSectionManageDao.findBId(id));
		eduSectionManageDao.deleteById(id);
	}

	// 调整版块信息状态
	@Override
	public void enable(SectionManageEntity eduSectionManageEntity, boolean result) {
		logger.initEnableLog(eduSectionManageEntity, result);
		eduSectionManageDao.saveAndFlush(eduSectionManageEntity);

	}

	//动态获取版块管理下拉列表内容
	@Override
	public List<SectionManageEntity> findListName() {
		
		return eduSectionManageDao.findListName();
	}

}
