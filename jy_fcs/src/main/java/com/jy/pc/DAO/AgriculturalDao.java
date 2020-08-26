package com.jy.pc.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.AgriculturalEntity;

public interface AgriculturalDao extends JpaRepository<AgriculturalEntity, String> {

	// Echart图关联
	@Query(value = "SELECT * FROM sas_publication_info e where e.status = '1'", nativeQuery = true)
	public List<AgriculturalEntity> findAgrSum();

	// fingById方法
	@Query(value = "select * from sas_publication_info where id =:id", nativeQuery = true)
	public AgriculturalEntity findBId(@Param("id") String id);

	// 分页与模糊查询
	@Query(value = "select * from sas_publication_info  t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.status like ?2,1=1) order by t.create_date desc", countQuery = "select count(*) from sas_publication_info t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.status like ?2,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<AgriculturalEntity> findListByName(String name, String status, Pageable pageable);

	// 查询农服标题名称
	@Query(value = "select * from sas_publication_info  t  where if(?1 !='',t.name like ?1,1=1)order by t.create_date desc", nativeQuery = true)
	public List<AgriculturalEntity> findListByAgrName(String name);

	// 查询农服标题名称
	@Query(value = " select * from sas_publication_info order by create_date  desc limit 0,3", nativeQuery = true)
	public List<AgriculturalEntity> findListByTime();

	// 不同状态加载不同的发布
	@Query(value = "SELECT * FROM sas_publication_info t where t.status = :status", nativeQuery = true)
	public List<AgriculturalEntity> findStatusPass(@Param("status") String status);

	// 获取农服预约信息
	@Query(value = "SELECT * FROM sas_publication_info t where t.status = '0'", nativeQuery = true)
	public List<AgriculturalEntity> findAppointment();
	
	@Query(value = "SELECT * FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and t.status = '1' order by create_date  desc limit 0,3", nativeQuery = true)
	public List<AgriculturalEntity> findListByTime(@Param("map") Map<String,List<String>> map);
	
	@Query(value = "SELECT * FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and t.status = '1' order by t.create_date desc", nativeQuery = true)
	public List<AgriculturalEntity> findListByType(@Param("map") Map<String,List<String>> map);

}
