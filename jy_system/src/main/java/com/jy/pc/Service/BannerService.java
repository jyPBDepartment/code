package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.BannerEntity;

public interface BannerService {
	
	
	public void save(BannerEntity bannerEntity);

	public void update();
	public void delete(String id);
	public Page<BannerEntity> findPageInfo(String name,Pageable pageable);
	public BannerEntity findInfoById(String id);

}
