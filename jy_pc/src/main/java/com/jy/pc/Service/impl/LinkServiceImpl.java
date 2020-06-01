package com.jy.pc.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.LinkDao;
import com.jy.pc.Entity.LinkEntity;

import com.jy.pc.Service.LinkService;

@Service
public class LinkServiceImpl implements LinkService{
@Autowired
private LinkDao linkDao;

@Override
public void save(LinkEntity linkEntity) {
	
	 linkDao.save(linkEntity);
}

@Override
public void update(LinkEntity linkEntity) {
	
	linkDao.saveAndFlush(linkEntity);
}

@Override
public void delete(String id) {

	linkDao.deleteById(id);
}


@Override
public LinkEntity findBId(String salesId) {
	
	return linkDao.findBId(salesId);
}
}
