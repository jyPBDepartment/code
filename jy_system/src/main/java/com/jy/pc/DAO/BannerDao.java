package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.BannerEntity;

public interface BannerDao extends JpaRepository<BannerEntity, String> {

	@Query(value = "select * from w_banner t where if(?1 !='',t.name like ?1,1=1) order by t.create_date desc", countQuery = "select count(*) from w_banner t where if(?1 !='',t.name like ?1,1=1)", nativeQuery = true)
	public Page<BannerEntity> findPageInfo(String name, Pageable pageable);

	@Query(value = "select * from  w_banner t where t.id=:id", nativeQuery = true)
	public BannerEntity findInfoById(@Param("id") String id);
	@Query(value="select * from  w_banner ",nativeQuery = true)
	public BannerEntity findId();

}
