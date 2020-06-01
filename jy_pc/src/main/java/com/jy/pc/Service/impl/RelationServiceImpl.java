package com.jy.pc.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.RelationDao;
import com.jy.pc.Entity.RelationEntity;
import com.jy.pc.Service.RelationService;
@Service
public class RelationServiceImpl implements RelationService{
	@Autowired
	private RelationDao relationDao;
	@Override
	public void save(RelationEntity relation) {
		relationDao.saveAndFlush(relation);
	}

	@Override
	public void update(RelationEntity relation) {
		relationDao.saveAndFlush(relation);
	}

	@Override
	public void delete(String id) {
		relationDao.deleteById(id);
	}

	@Override
	public RelationEntity findRelationId(String limitId) {
		return relationDao.findRelationId(limitId);
	}

}
