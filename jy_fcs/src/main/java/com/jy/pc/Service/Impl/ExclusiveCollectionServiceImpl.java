package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.ArticleManageDao;
import com.jy.pc.DAO.ExclusiveCollectionDao;
import com.jy.pc.Entity.ArticleManageEntity;
import com.jy.pc.Entity.ExclusiveCollectionEntity;
import com.jy.pc.Service.ExclusiveCollectionService;

@Service
public class ExclusiveCollectionServiceImpl implements ExclusiveCollectionService{
	@Autowired
	private ExclusiveCollectionDao collectionDao;
	@Autowired
	private ArticleManageDao articleManageDao;

	@Override
	public ExclusiveCollectionEntity findInfoByAll(String agrId, String userId) {
		return collectionDao.findInfoByAll(agrId, userId);
	}

	@Override
	public List<ExclusiveCollectionEntity> findInfoByAgr(String agrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExclusiveCollectionEntity> findInfoByUser(String agrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void collection(String agrId, String userId) {
		ExclusiveCollectionEntity old = collectionDao.findInfoByAll(agrId, userId);
		if (old != null) {
			return;
		}
		ExclusiveCollectionEntity collection = new ExclusiveCollectionEntity();
		collection.setArtId(agrId);
		collection.setCollectionUserId(userId);
		collectionDao.save(collection);
		ArticleManageEntity grain = articleManageDao.findBId(agrId);
		grain.setCollectionNum(grain.getCollectionNum() + 1);
		articleManageDao.save(grain);
	}

	@Override
	@Transactional
	public void cancel(String agrId, String userId) throws Exception {
		ExclusiveCollectionEntity collection = collectionDao.findInfoByAll(agrId, userId);
		if (collection == null) {
			return;
		}
		ArticleManageEntity grain = articleManageDao.findBId(agrId);
		int num = 0;
		if (grain.getCollectionNum() == 0) {
			num = 0;
		} else {
			num = grain.getCollectionNum() - 1;
		}
		grain.setCollectionNum(num);
		collectionDao.deleteById(collection.getId());
		articleManageDao.save(grain);
	}

	@Override
	public ExclusiveCollectionEntity save(ExclusiveCollectionEntity exclusiveCollectionEntity) {
		// TODO Auto-generated method stub
		return null;
	}
}
