package com.jy.pc.DAO;

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
}
