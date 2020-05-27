package com.jy.pc.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.JurisdictionDao;
import com.jy.pc.Entity.JurisdictionEntity;
import com.jy.pc.Service.JurisdictionService;

@Service
public class JurisdictionServiceImpl implements JurisdictionService{
	
	@Autowired
	private JurisdictionDao jurisdictionDao;
	@Override
	public void save(JurisdictionEntity jurisdiction) {
		jurisdictionDao.saveAndFlush(jurisdiction);
	}

	@Override
	public void update(JurisdictionEntity jurisdiction) {
		jurisdictionDao.saveAndFlush(jurisdiction);
	}

	@Override
	public void delete(String id) {
		jurisdictionDao.deleteById(id);
	}

	@Override
	public List<JurisdictionEntity> findAll() {
		return jurisdictionDao.findAll();
	}

	@Override
	public JurisdictionEntity findId(String id) {
		return jurisdictionDao.findId(id);
	}

	@Override
	public Page<JurisdictionEntity> findListByName(String name, Integer type,Pageable pageable) {
		String jurName = "%"+name+"%";
		return jurisdictionDao.findListByName(jurName, type,pageable);
	}

}
