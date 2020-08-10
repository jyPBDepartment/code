package com.jy.pc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.PictureInfoDAO;
import com.jy.pc.Entity.PictureInfoEntity;
import com.jy.pc.Service.PictureInfoService;

@Service
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

}
