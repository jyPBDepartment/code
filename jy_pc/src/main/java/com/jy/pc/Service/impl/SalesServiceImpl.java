package com.jy.pc.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		// TODO Auto-generated method stub
		salesDao.save(salesEntity);
	}

	@Override
	public void update(SalesEntity salesEntity) {
		// TODO Auto-generated method stub
		salesDao.saveAndFlush(salesEntity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		salesDao.deleteById(id);
	}

	@Override
	public List<SalesEntity> findAll() {
		// TODO Auto-generated method stub
		return salesDao.findAll();
	}

	@Override
	public SalesEntity findBId(String id) {
		// TODO Auto-generated method stub
		return salesDao.findBId(id);
	}

	@Override
	public List<SalesEntity> findListByName(String name, String phone) {
		// TODO Auto-generated method stub
		
		String salesName = "%"+name+"%";
		String telephone = "%"+phone+"%";
		return salesDao.findListByName(salesName, telephone);
	}

}
