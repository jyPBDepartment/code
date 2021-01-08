package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.AgriculturalDao;
import com.jy.pc.DAO.GrainTradingPraiseDao;
import com.jy.pc.Entity.AgriculturalEntity;
import com.jy.pc.Entity.GrainTradingPraiseEntity;
import com.jy.pc.Service.GrainTradingPraiseService;

@Service
public class GrainTradingPraiseServiceImpl implements GrainTradingPraiseService {

	@Autowired
	private GrainTradingPraiseDao praiseDao;
	@Autowired
	private AgriculturalDao agriculturalDao;

	@Override
	public GrainTradingPraiseEntity findInfoByAll(String agrId, String userId) {
		return praiseDao.findInfoByAll(agrId, userId);
	}

	@Override
	public List<GrainTradingPraiseEntity> findInfoByAgr(String agrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GrainTradingPraiseEntity> findInfoByUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void praise(String agrId, String userId) {
		GrainTradingPraiseEntity old = praiseDao.findInfoByAll(agrId, userId);
		if (old != null) {
			return;
		}
		GrainTradingPraiseEntity praise = new GrainTradingPraiseEntity();
		praise.setArgId(agrId);
		praise.setPraiseUserId(userId);
		praiseDao.save(praise);
		AgriculturalEntity grain = agriculturalDao.findId(agrId);
		grain.setPraiseNum(grain.getPraiseNum() + 1);
		agriculturalDao.save(grain);

	}

	@Transactional
	@Override
	public void cancel(String agrId, String userId) throws Exception {
		GrainTradingPraiseEntity praise = praiseDao.findInfoByAll(agrId, userId);
		if (praise == null) {
			return;
		}
		AgriculturalEntity grain = agriculturalDao.findId(agrId);
		grain.setPraiseNum(grain.getPraiseNum() - 1);
		praiseDao.deleteById(praise.getId());
		agriculturalDao.save(grain);

	}

	@Override
	public GrainTradingPraiseEntity save(GrainTradingPraiseEntity grainTradingCollentionEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
