package com.jy.pc.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.SalesDao;
import com.jy.pc.Entity.SalesEntity;
import com.jy.pc.Service.SalesService;

@Service
public class SalesServiceImpl implements SalesService{
	@Autowired 
	private SalesDao salesDao;
	@Override
	public void save(SalesEntity salesEntity) {
		
		salesDao.save(salesEntity);
	}

	@Override
	public void update(SalesEntity salesEntity) {
		
		salesDao.saveAndFlush(salesEntity);
	}

	@Override
	public void delete(String id) {
		
		salesDao.deleteById(id);
	}

	@Override
	public List<SalesEntity> findAll() {
		
		return salesDao.findAll();
	}

	@Override
	public SalesEntity findBId(String id) {
		
		return salesDao.findBId(id);
	}

	@Override
	public Page<SalesEntity> findListByName(String name, String phone,Pageable pageable) {
		
		String salesName = "%"+name+"%";
		String telephone = "%"+phone+"%";
		return salesDao.findListByName(salesName, telephone, pageable);

	}

	
	

}
