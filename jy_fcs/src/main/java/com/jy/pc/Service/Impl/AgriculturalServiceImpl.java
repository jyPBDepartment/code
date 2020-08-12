package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.AgriculturalDao;
import com.jy.pc.Entity.AgriculturalEntity;
import com.jy.pc.Service.AgriculturalService;

@Service
public class AgriculturalServiceImpl implements AgriculturalService {

	@Autowired
	private AgriculturalDao agriculturalDao;

	// 农服关联Echart图
	@Override
	public List<AgriculturalEntity> findAgrSum() {

		return agriculturalDao.findAgrSum();
	}

	// 农服添加
	@Override
	public AgriculturalEntity save(AgriculturalEntity agriculturalEntity) {

		return agriculturalDao.saveAndFlush(agriculturalEntity);
	}

	// 农服查找
	@Override
	public AgriculturalEntity findBId(String id) {

		return agriculturalDao.findBId(id);
	}

	// 农服分页与模糊查询
	@Override
	public Page<AgriculturalEntity> findListByName(String name, String status, Pageable pageable) {
		String argicuturalName = "%" + name + "%";
		return agriculturalDao.findListByName(argicuturalName, status, pageable);
	}

	// 农服修改
	@Override
	public AgriculturalEntity update(AgriculturalEntity agriculturalEntity) {

		return agriculturalDao.saveAndFlush(agriculturalEntity);
	}

	// 农服查询标题名称
	@Override
	public List<AgriculturalEntity> findListByAgrName(String name) {
		String argName = "%" + name + "%";
		return agriculturalDao.findListByAgrName(name);
	}

	// 农服查询最近三条
	@Override
	public List<AgriculturalEntity> findListByTime() {

		return agriculturalDao.findListByTime();
	}

	// 关键字搜索病虫害信息
	@Override
	public List<AgriculturalEntity> findCaseInfoByKey(String name) {
		String caseName = "%" + name + "%";
		return agriculturalDao.findCaseInfoByKey(caseName);
	}

	// 不同状态加载不同的发布
	@Override
	public List<AgriculturalEntity> findStatusPass(String status) {

		return agriculturalDao.findStatusPass(status);
	}

	// 获取农服预约信息
	@Override
	public List<AgriculturalEntity> findAppointment() {

		return agriculturalDao.findAppointment();
	}

}
