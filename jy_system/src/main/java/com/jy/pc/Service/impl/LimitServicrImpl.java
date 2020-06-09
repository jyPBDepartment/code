package com.jy.pc.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.LimitDao;
import com.jy.pc.Entity.LimitEntity;
import com.jy.pc.Service.LimitService;
@Service
public class LimitServicrImpl implements LimitService{
	@Autowired
	private LimitDao limitDao;
	@Override
	public LimitEntity save(LimitEntity jurisdiction) {
		return limitDao.saveAndFlush(jurisdiction);
	}

	@Override
	public void update(LimitEntity jurisdiction) {
		limitDao.saveAndFlush(jurisdiction);
	}

	@Override
	public void delete(String id) {
		limitDao.deleteById(id);
	}

	@Override
	public List<LimitEntity> findAll() {
		return limitDao.findAll();
	}

	@Override
	public LimitEntity findId(String id) {
		return limitDao.findId(id);
	}

	@Override
	public Page<LimitEntity> findListByName(String name,Pageable pageable) {
		String jurName = "%"+name+"%";
		return limitDao.findListByName(jurName,pageable);
	}

	@Override
	public List<LimitEntity> findAl() {
		return limitDao.findAl();
	}

}
