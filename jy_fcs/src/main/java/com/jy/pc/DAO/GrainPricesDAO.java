package com.jy.pc.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.GrainPricesEntity;

public interface GrainPricesDAO extends JpaRepository<GrainPricesEntity,String>{
	
	@Query(value="select * from sas_grain_prices_info t where t.id=?1",nativeQuery = true)
	public GrainPricesEntity findInfoById(String id);

	@Query(value = "select * from sas_grain_prices_info t where if(?1 !='',t.price_defined_type = ?1,1=1) order by t.create_date desc", countQuery = "select count(*) from sas_grain_prices_info t where if(?1 !='',t.price_defined_type like ?1,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<GrainPricesEntity> findPageByParam(String priceDefinedType,Pageable pageable);
	
	@Query(value="select * from sas_grain_prices_info  where date(price_date) = date(?1)",nativeQuery = true)
	public List<GrainPricesEntity> findInfoByDate(Date now);
	
	@Query(value="SELECT * FROM sas_grain_prices_info ORDER BY price_date DESC limit 1",nativeQuery = true)
	public GrainPricesEntity findNewestInfo();
	
	@Query(value="select * from (SELECT * FROM sas_grain_prices_info t where t.price_date <= date_format(now(),'%Y-%m-%d')  limit ?1 ) t1 order by t1.price_date",nativeQuery = true)
	public List<GrainPricesEntity> findListByType(int type);
}
