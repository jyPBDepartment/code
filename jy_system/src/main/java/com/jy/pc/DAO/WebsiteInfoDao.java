package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.WebsiteInfoEntity;

public interface WebsiteInfoDao extends JpaRepository<WebsiteInfoEntity, String>{

	@Query(value="select * from w_website_info",nativeQuery = true)
	public WebsiteInfoEntity findId();
}
