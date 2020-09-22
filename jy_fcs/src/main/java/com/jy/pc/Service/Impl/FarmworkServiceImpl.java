package com.jy.pc.Service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.FarmworkDao;
import com.jy.pc.DAO.FarmworkPictureDAO;
import com.jy.pc.DAO.PictureInfoDAO;
import com.jy.pc.Entity.FarmworkEntity;
import com.jy.pc.Entity.FarmworkPictureEntity;
import com.jy.pc.Entity.PictureInfoEntity;
import com.jy.pc.Service.FarmworkService;

@Service
@Transactional
public class FarmworkServiceImpl implements FarmworkService {
	@Autowired
	private FarmworkDao farmworkDao;
	@Autowired
	private PictureInfoDAO pictureInfoDAO;
	@Autowired
	private FarmworkPictureDAO farmworkPictureDAO;

	// 农活预约Echart图
	@Override
	public List<FarmworkEntity> findSum() {

		return farmworkDao.findSum();
	}

	// 农活预约添加
	@Override
	public FarmworkEntity save(FarmworkEntity farmworkEntity) {

		return farmworkDao.saveAndFlush(farmworkEntity);
	}

	@Override
	public FarmworkEntity findById(String id) {
		return farmworkDao.findId(id);
	}

	@Override
	public Page<FarmworkEntity> findMyFarm(String userId,String status, String user, Pageable pageable) {
		return farmworkDao.findMyFarm(userId,status,user, pageable);
	}

	@Override
	public Page<FarmworkEntity> findFarmForMe(String userId,String status, String user, Pageable pageable) {
		return farmworkDao.findFarmForMe(userId,status,user, pageable);
	}

	@Override
	public String findDay(String id) {
		return farmworkDao.findDay(id);
	}

	@Override
	public void update(FarmworkEntity farmworkEntity) {
		farmworkDao.saveAndFlush(farmworkEntity);
		
	}

	@Override
	public FarmworkEntity saveFarmwork(FarmworkEntity farmworkEntity, String addItem, String agrId) {
		
		
		String[] picArray = null;
		if(addItem.indexOf(",")>-1) {
			picArray = addItem.split(",");
		}else {
			picArray[0]= addItem;
		}
		farmworkEntity.setStatus("0");
		farmworkEntity.setAgriculturalId(agrId);
		farmworkEntity.setUrl(picArray[0]);
		FarmworkEntity farmwork= farmworkDao.save(farmworkEntity);
		farmworkEntity.setDays(farmworkDao.findDay(farmworkEntity.getId()));
		farmworkDao.save(farmworkEntity);
		for(int i=0;i<picArray.length;i++) {
			PictureInfoEntity pictureInfoEntity = new PictureInfoEntity();
			pictureInfoEntity.setPicName(farmwork.getId());
			pictureInfoEntity.setPicUrl(picArray[i]);
			PictureInfoEntity pictureInfo = pictureInfoDAO.save(pictureInfoEntity);
			
			FarmworkPictureEntity farm = new FarmworkPictureEntity();
			farm.setFarmworkId(farmwork.getId());
			farm.setPicId(pictureInfo.getId());
			farmworkPictureDAO.save(farm);		
		}
		return farmworkEntity;
	}

}
