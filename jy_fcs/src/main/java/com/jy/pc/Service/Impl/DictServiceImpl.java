package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.DictDataDao;
import com.jy.pc.DAO.DictTypeDao;
import com.jy.pc.Entity.DictDataEntity;
import com.jy.pc.Entity.DictTypeEntity;
import com.jy.pc.Service.DictService;
import com.jy.pc.Utils.DbLogUtil;

@Service
public class DictServiceImpl implements DictService {

	@Autowired
	private DbLogUtil logger;
	@Autowired
	private DictTypeDao dictTypeDao;
	@Autowired
	private DictDataDao dictDataDao;

	// 新增字典类型
	@Override
	public void insertType(DictTypeEntity dictType) {
		logger.initAddLog(dictType);
		dictTypeDao.saveAndFlush(dictType);
	}

	// 新增字典键值
	@Override
	public void insertData(DictDataEntity dictData) {
		logger.initAddLog(dictData);
		dictDataDao.saveAndFlush(dictData);
	}

	// 启用禁用字典类型
	@Override
	public void enableType(DictTypeEntity dictType, boolean result) {
		logger.initEnableLog(dictType, result);
		dictTypeDao.saveAndFlush(dictType);
	}

	// 启用禁用字典键值
	@Override
	public void enableData(DictDataEntity dictData, boolean result) {
		logger.initEnableLog(dictData, result);
		dictDataDao.saveAndFlush(dictData);
	}

	// 更新字典类型
	@Override
	public void updateType(DictTypeEntity dictType) {
		logger.initUpdateLog(dictType);
		dictTypeDao.saveAndFlush(dictType);
	}

	// 更新字典键值
	@Override
	public void updateData(DictDataEntity dictData) {
		logger.initUpdateLog(dictData);
		dictDataDao.saveAndFlush(dictData);
	}

	// 分页查询字典类型
	@Override
	public Page<DictTypeEntity> findPageByName(String name, String status, Pageable pageable) {
		String nameParam = "%" + name + "%";
		String statusParam = "%" + status + "%";
		return dictTypeDao.findListByName(nameParam, statusParam, pageable);
	}

	// 根据字典类型查询对应键值
	@Override
	public List<DictDataEntity> findListByType(String type) {
		return dictDataDao.fingByTypeId(type);
	}

	// 根据ID删除字典类型
	@Override
	public void deleteType(String id) {
		dictTypeDao.deleteById(id);
	}

	// 根据ID删除字典类型
	@Override
	public void deleteData(String id) {
		dictDataDao.deleteById(id);
	}

	// 清空指定字典类型下的所有键值
	@Override
	public void removeData(String type) {
		dictDataDao.removeData(type);
	}

	@Override
	public DictTypeEntity findTypeById(String id) {
		return dictTypeDao.GetById(id);
	}

	@Override
	public DictDataEntity findDataById(String id) {
		return dictDataDao.GetById(id);
	}

}
