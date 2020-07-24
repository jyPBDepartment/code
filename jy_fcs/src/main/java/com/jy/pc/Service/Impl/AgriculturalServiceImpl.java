package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.AgriculturalDao;
import com.jy.pc.Entity.AgriculturalEntity;
import com.jy.pc.Service.AgriculturalService;

@Service
public class AgriculturalServiceImpl implements AgriculturalService{

	@Autowired
	private AgriculturalDao agriculturalDao;
	@Override
	public List<AgriculturalEntity> findAgrSum() {
		
		return agriculturalDao.findAgrSum();
	}

	
}
