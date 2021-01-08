package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.AgriculturalDao;
import com.jy.pc.DAO.GrainTradingCollectionDao;
import com.jy.pc.Entity.AgriculturalEntity;
import com.jy.pc.Entity.GrainTradingCollectionEntity;
import com.jy.pc.Service.GrainTradingCollectionService;

@Service
public class GrainTradingCollectionServiceImpl implements GrainTradingCollectionService {
	@Autowired
	private GrainTradingCollectionDao collectionDao;
	@Autowired
	private AgriculturalDao agriculturalDao;

	@Override
	public GrainTradingCollectionEntity findInfoByAll(String agrId, String userId) {
		return collectionDao.findInfoByAll(agrId, userId);
	}

	@Override
	public List<GrainTradingCollectionEntity> findInfoByAgr(String agrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GrainTradingCollectionEntity> findInfoByUser(String agrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void collection(String agrId, String userId) {
		GrainTradingCollectionEntity old = collectionDao.findInfoByAll(agrId, userId);
		if (old != null) {
			return;
		}
		GrainTradingCollectionEntity collection = new GrainTradingCollectionEntity();
		collection.setArgId(agrId);
		collection.setCollectionUserId(userId);
		collectionDao.save(collection);
		AgriculturalEntity grain = agriculturalDao.findId(agrId);
		grain.setCollectionNum(grain.getCollectionNum() + 1);
		agriculturalDao.save(grain);
	}

	@Override
	@Transactional
	public void cancel(String agrId, String userId) throws Exception {
		GrainTradingCollectionEntity collection = collectionDao.findInfoByAll(agrId, userId);
		if (collection == null) {
			return;
		}
		AgriculturalEntity grain = agriculturalDao.findId(agrId);
		grain.setCollectionNum(grain.getCollectionNum() - 1);
		collectionDao.deleteById(collection.getId());
		agriculturalDao.save(grain);
	}

	@Override
	public GrainTradingCollectionEntity save(GrainTradingCollectionEntity grainTradingCollentionEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
