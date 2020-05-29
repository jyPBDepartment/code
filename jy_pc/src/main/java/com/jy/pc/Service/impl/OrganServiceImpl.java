package com.jy.pc.Service.impl;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.OrganDao;

import com.jy.pc.Entity.OrganEntity;
import com.jy.pc.Service.OrganService;

@Service
public class OrganServiceImpl implements OrganService{
	@Autowired
	private OrganDao organDao;
	
	@Override
	public void save(OrganEntity OrganEntity) {
		
		organDao.save(OrganEntity);
	}

	@Override
	public void update(OrganEntity OrganEntity) {
		
		organDao.saveAndFlush(OrganEntity);
	}

	@Override
	public void delete(String id) {
	
		organDao.deleteById(id);
	}

	@Override
	public List<OrganEntity> findAll() {
		// TODO Auto-generated method stub
		return organDao.findAll();
	}

	@Override
	public OrganEntity findBId(String id) {
		
		return organDao.findBId(id);
	}

	@Override
	public Page<OrganEntity> findListByName(String name, String superId,Pageable pageable) {
		String organName = "%"+name+"%";
		String higher = "%"+superId+"%";
		return organDao.findListByName(organName, higher, pageable);
	}

	
}
