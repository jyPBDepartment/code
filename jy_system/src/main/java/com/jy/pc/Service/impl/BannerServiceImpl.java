package com.jy.pc.Service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.BannerDao;
import com.jy.pc.Entity.BannerEntity;
import com.jy.pc.Service.BannerService;
@Service
@Transactional
public class BannerServiceImpl implements BannerService{
	@Autowired
	private BannerDao bannerDao;
	public void save(BannerEntity bannerEntity) {
		
		bannerDao.saveAndFlush(bannerEntity);
	}
	public void update() {
		
	}
	public void delete(String id) {
		bannerDao.deleteById(id);
	}

	public Page<BannerEntity> findPageInfo(String name, Pageable pageable) {
		String n = "%" + name + "%";
		return bannerDao.findPageInfo(n, pageable);

	}
	
	public BannerEntity findInfoById(String id) {
		return bannerDao.findInfoById(id);
	}
	
	

}
