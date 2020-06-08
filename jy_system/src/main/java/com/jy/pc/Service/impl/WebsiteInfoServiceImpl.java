package com.jy.pc.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.WebsiteInfoDao;
import com.jy.pc.Entity.WebsiteInfoEntity;
import com.jy.pc.Service.WebsiteInfoService;
@Service
public class WebsiteInfoServiceImpl implements WebsiteInfoService{
	@Autowired
	private WebsiteInfoDao websiteInfoDao;

	@Override
	public void update(WebsiteInfoEntity websiteInfoEntity) {
		websiteInfoDao.saveAndFlush(websiteInfoEntity);
	}

	@Override
	public WebsiteInfoEntity findId() {
		return websiteInfoDao.findId();
	}

}
