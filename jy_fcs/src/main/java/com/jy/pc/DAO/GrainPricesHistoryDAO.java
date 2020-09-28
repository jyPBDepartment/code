package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.GrainPricesHistoryEntity;

public interface GrainPricesHistoryDAO extends JpaRepository<GrainPricesHistoryEntity,String>{

	@Query(value="select * from sas_grain_prices_history t where t.id=?1",nativeQuery = true)
	public GrainPricesHistoryEntity findInfoById(String id);
	
	@Query(value = "select * from sas_grain_prices_history t where if(?1 !='',t.operate_type = ?1,1=1) and if(?2 !='',date_format(t.create_date,'%Y-%m-%d') >= ?2,1=1) and if(?3 !='',date_format(t.create_date,'%Y-%m-%d') <= ?3,1=1) order by t.create_date desc", countQuery = "select count(*) from sas_grain_prices_history t where if(?1 !='',t.operate_type like ?1,1=1) and if(?2 !='',date_format(t.create_date,'%Y-%m-%d') >= ?2,1=1) and if(?3 !='',date_format(t.create_date,'%Y-%m-%d') <= ?3,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<GrainPricesHistoryEntity> findPageByParam(String priceDefinedType,String startDate,String endDate,Pageable pageable);
}
