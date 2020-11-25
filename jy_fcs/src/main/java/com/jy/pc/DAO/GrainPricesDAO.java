package com.jy.pc.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.GrainPricesEntity;

public interface GrainPricesDAO extends JpaRepository<GrainPricesEntity, String> {

	@Query(value = "select * from sas_grain_prices_info t where t.id=?1", nativeQuery = true)
	public GrainPricesEntity findInfoById(String id);

	@Query(value = "select * from sas_grain_prices_info t where if(?1 !='',t.price_defined_type = ?1,1=1) and if(?2 !='',t.province = ?2,1=1) and if(?3 !='',t.city = ?3,1=1) and if(?4 !='',t.district = ?4,1=1) order by t.create_date desc", countQuery = "select count(*) from sas_grain_prices_info t where if(?1 !='',t.price_defined_type = ?1,1=1) and if(?2 !='',t.province = ?2,1=1) and if(?3 !='',t.city = ?3,1=1) and if(?4 !='',t.district = ?4,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<GrainPricesEntity> findPageByParam(String priceDefinedType, String province, String city,
			String district, Pageable pageable);

	@Query(value = "select * from sas_grain_prices_info  where date(price_date) = date(?1)", nativeQuery = true)
	public List<GrainPricesEntity> findInfoByDate(Date now);

	@Query(value = "SELECT * FROM sas_grain_prices_info ORDER BY price_date DESC limit 1", nativeQuery = true)
	public GrainPricesEntity findNewestInfo();

	@Query(value = "select * from (SELECT * FROM sas_grain_prices_info t where t.price_date <= date_format(now(),'%Y-%m-%d')  order by t.price_date desc limit ?1) t1 order by t1.price_date", nativeQuery = true)
	public List<GrainPricesEntity> findListByType(int type);

	// 查询当天记录（符合地区条件）
	@Query(value = "SELECT * FROM sas_grain_prices_info WHERE province = ?1 and city = ?2 and district = ?3 and date(price_date) = date(now()) limit 1", nativeQuery = true)
	public GrainPricesEntity findInfoByArea(String province, String city, String district);

	@Query(value = "select * from (SELECT * FROM sas_grain_prices_info t where t.price_date <= date_format(now(),'%Y-%m-%d') and province = ?1 and city = ?2 and district = ?3  order by t.price_date desc limit ?4) t1 order by t1.price_date", nativeQuery = true)
	public List<GrainPricesEntity> findListByArea(String province, String city, String district, int queryParam);

	// 复制昨天数据
	@Modifying
	@Query(value = "INSERT INTO sas_grain_prices_info SELECT UUID() AS id,create_date,create_user,price,'1',update_date,update_user,DATE_FORMAT(NOW(),'%Y-%m-%d') AS price_date,area_id,city,district,max_price,min_price,province,area FROM sas_grain_prices_info WHERE date(price_date) = date(?1)", nativeQuery = true)
	public void copyToToday(Date now);
}
