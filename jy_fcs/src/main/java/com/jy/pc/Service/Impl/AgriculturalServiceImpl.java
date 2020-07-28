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
public class AgriculturalServiceImpl implements AgriculturalService{

	@Autowired
	private AgriculturalDao agriculturalDao;
	@Override
	public List<AgriculturalEntity> findAgrSum() {
		
		return agriculturalDao.findAgrSum();
	}
	@Override
	public AgriculturalEntity save(AgriculturalEntity agriculturalEntity) {
		
		return agriculturalDao.saveAndFlush(agriculturalEntity);
	}
	@Override
	public AgriculturalEntity findBId(String id) {
		
		return agriculturalDao.findBId(id);
	}
	@Override
	public Page<AgriculturalEntity> findListByName(String name,String status,Pageable pageable) {
		String argicuturalName = "%"+name+"%";
		return agriculturalDao.findListByName(argicuturalName,status, pageable);
	}
	@Override
	public AgriculturalEntity update(AgriculturalEntity agriculturalEntity) {
		
		return agriculturalDao.saveAndFlush(agriculturalEntity);
	}

	
}
