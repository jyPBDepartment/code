package com.jy.pc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.KeyWordDao;
import com.jy.pc.Entity.KeyWordEntity;
import com.jy.pc.Service.KeyWordService;

@Service
public class KeyWordServiceImpl implements KeyWordService {
	@Autowired
	private KeyWordDao keyWordDao;

	//查询
	@Override
	public Page<KeyWordEntity> findListByName(String name, Pageable pageable) {
		String keyName = "%" + name + "%";
		return keyWordDao.findListByName(keyName, pageable);
	}

	//新增
	@Override
	public KeyWordEntity save(KeyWordEntity keyWord) {
		return keyWordDao.saveAndFlush(keyWord);
	}

	//修改
	@Override
	public void update(KeyWordEntity keyWord) {
		keyWordDao.saveAndFlush(keyWord);
	}

	//删除
	@Override
	public void delete(String id) {
		keyWordDao.deleteById(id);
	}

	//通过id查询
	@Override
	public KeyWordEntity findId(String id) {
		return keyWordDao.findId(id);
	}

}
