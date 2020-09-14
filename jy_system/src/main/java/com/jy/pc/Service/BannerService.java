package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.BannerEntity;

public interface BannerService {
	
	
	public void save(BannerEntity bannerEntity);

	public void update();
	public void delete(String id);
	public Page<BannerEntity> findPageInfo(String name,Pageable pageable);
	public BannerEntity findInfoById(String id);
	
	//查找非禁用banner图
	public List<BannerEntity> findId();

}
