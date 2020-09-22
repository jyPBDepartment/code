package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.PictureInfoDAO;
import com.jy.pc.Entity.PictureInfoEntity;
import com.jy.pc.Service.PictureInfoService;

@Service
@Transactional
public class PictureInfoServiceImpl implements PictureInfoService{
	@Autowired
	private PictureInfoDAO pictureInfoDao;

	@Override
	public PictureInfoEntity save(PictureInfoEntity pictureInfo) {
		return pictureInfoDao.saveAndFlush(pictureInfo);
	}

	@Override
	public void update(PictureInfoEntity pictureInfo) {
		pictureInfoDao.saveAndFlush(pictureInfo);
	}

	@Override
	public void delete(String id) {
		pictureInfoDao.deleteById(id);
	}

	@Override
	public List<PictureInfoEntity> findByAgrId(@Param("id") String id){
		return 	pictureInfoDao.findByAgrId(id);
	}

	@Override
	public List<PictureInfoEntity> findByFarmId(String id) {
	
		return pictureInfoDao.findByFarmId(id);
	}

	@Override
	public PictureInfoEntity findByAgrUrl(String url) {
		return pictureInfoDao.findByAgrUrl(url);
	}
}
