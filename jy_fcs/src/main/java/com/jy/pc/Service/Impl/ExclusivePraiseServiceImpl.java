package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.ArticleManageDao;
import com.jy.pc.DAO.ExclusivePraiseDao;
import com.jy.pc.Entity.AgriculturalEntity;
import com.jy.pc.Entity.ArticleManageEntity;
import com.jy.pc.Entity.ExclusivePraiseEntity;
import com.jy.pc.Service.ExclusivePraiseService;

@Service
public class ExclusivePraiseServiceImpl implements ExclusivePraiseService{
	@Autowired
	private ExclusivePraiseDao praiseDao;
	@Autowired
	private ArticleManageDao articleManageDao;

	@Override
	public ExclusivePraiseEntity findInfoByAll(String agrId, String userId) {
		return praiseDao.findInfoByAll(agrId, userId);
	}

	@Override
	public List<ExclusivePraiseEntity> findInfoByAgr(String agrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExclusivePraiseEntity> findInfoByUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void praise(String artId, String userId) {
		ExclusivePraiseEntity old = praiseDao.findInfoByAll(artId, userId);
		if (old != null) {
			return;
		}
		ExclusivePraiseEntity praise = new ExclusivePraiseEntity();
		praise.setArtId(artId);
		praise.setPraiseUserId(userId);
		praiseDao.save(praise);
		ArticleManageEntity grain = articleManageDao.findBId(artId);
		grain.setPraiseNum(grain.getPraiseNum() + 1);
		articleManageDao.save(grain);

	}

	@Transactional
	@Override
	public void cancel(String agrId, String userId) throws Exception {
		ExclusivePraiseEntity praise = praiseDao.findInfoByAll(agrId, userId);
		if (praise == null) {
			return;
		}
		ArticleManageEntity grain = articleManageDao.findBId(agrId);
		int num = 0;
		if (grain.getPraiseNum() == 0) {
			num = 0;
		} else {
			num = grain.getPraiseNum() - 1;
		}
		grain.setPraiseNum(num);
		praiseDao.deleteById(praise.getId());
		articleManageDao.save(grain);

	}

	@Override
	public ExclusivePraiseEntity save(ExclusivePraiseEntity exclusivePraiseEntity) {
		// TODO Auto-generated method stub
		return praiseDao.save(exclusivePraiseEntity);
	}
}
