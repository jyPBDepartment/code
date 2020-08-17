package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.KeyWordDao;
import com.jy.pc.Entity.KeyWordEntity;
import com.jy.pc.Service.KeyWordService;
import com.jy.pc.Utils.DbLogUtil;

@Service
public class KeyWordServiceImpl implements KeyWordService {
	@Autowired
	private KeyWordDao keyWordDao;
	@Autowired
	private DbLogUtil logger;

	// 查询
	@Override
	public Page<KeyWordEntity> findListByName(String name, Pageable pageable) {
		String keyName = "%" + name + "%";
		return keyWordDao.findListByName(keyName, pageable);
	}

	// 新增
	@Override
	@Transactional
	public KeyWordEntity save(KeyWordEntity keyWord) {
		logger.initAddLog(keyWord);
		return keyWordDao.saveAndFlush(keyWord);
	}

	// 启用/禁用
	@Override
	@Transactional
	public void enable(KeyWordEntity keyWord,boolean result) {
		logger.initEnableLog(keyWord,result);
		keyWordDao.saveAndFlush(keyWord);
	}

	// 修改
	@Override
	@Transactional
	public void update(KeyWordEntity keyWord) {
		logger.initUpdateLog(keyWord);
		keyWordDao.saveAndFlush(keyWord);
	}

	// 删除
	@Override
	@Transactional
	public void delete(String id) {
		logger.initDeleteLog(keyWordDao.findId(id));
		keyWordDao.deleteById(id);
	}

	// 通过id查询
	@Override
	public KeyWordEntity findId(String id) {
		return keyWordDao.findId(id);
	}

	// 通过分类编码查询对应关键词集合
	@Override
	public List<KeyWordEntity> findListByClass(String classCode) {
		return keyWordDao.findListByClass(classCode);
	}

}
