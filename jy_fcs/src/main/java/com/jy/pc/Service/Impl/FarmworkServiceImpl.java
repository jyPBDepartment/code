package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.FarmworkDao;
import com.jy.pc.Entity.FarmworkEntity;
import com.jy.pc.Service.FarmworkService;

@Service
public class FarmworkServiceImpl implements FarmworkService{
	@Autowired
	private FarmworkDao farmworkDao;
	
	@Override
	public List<FarmworkEntity> findSum() {
		
		return farmworkDao.findSum() ;
	}

}
