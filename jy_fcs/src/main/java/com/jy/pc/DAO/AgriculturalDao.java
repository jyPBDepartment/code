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

	// 根据id查询农服信息详情
	@Query(value = "select * from sas_publication_info t where t.id =:id and t.status = '1' ", nativeQuery = true)
	public AgriculturalEntity findId(@Param("id") String id);

	// 分页与模糊查询
	@Query(value = "select * from sas_publication_info  t  where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.status like ?2,1=1) order by t.create_date desc", countQuery = "select count(*) from sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.status like ?2,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<AgriculturalEntity> findListByName(String name, String status, Map<String, List<String>> map,
			Pageable pageable);

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
	public List<AgriculturalEntity> findListByTime(@Param("map") Map<String, List<String>> map);

	@Query(value = "SELECT * FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and t.status = '1' order by t.create_date desc", nativeQuery = true)
	public List<AgriculturalEntity> findListByType(@Param("map") Map<String, List<String>> map);

	@Query(value = "SELECT * FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and t.status = '1' order by t.create_date desc", countQuery = "select count(*) FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and t.status = '1' order by t.create_date desc", nativeQuery = true)
	public Page<AgriculturalEntity> findListByType(Map<String, List<String>> map, Pageable pageable);

//	搜索发布信息中农服信息(标题名称)
	@Query(value = "SELECT * FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and if(?1 !='',t.name like ?1,1=1) and t.status in ('1','4') and if(?2 !='',t.transaction_type_code = ?2,1=1) and if(?3 !='',t.transaction_category_code = ?3,1=1) order by t.create_date desc", countQuery = "select count(*) FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and if(?1 !='',t.name like ?1,1=1) and t.status in ('1','4')  and if(?2 !='',t.transaction_type_code = ?2,1=1) and if(?3 !='',t.transaction_category_code = ?3,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<AgriculturalEntity> findAgriInfo(String name, String transactionTypeCode,
			String transactionCategoryCode, Map<String, List<String>> map, Pageable pageable);

//	搜索发布信息中农服信息(类型、类别)
	@Query(value = "SELECT * FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and if(?1 !='',t.transaction_type_code = ?1,1=1) and if(?2 !='',t.transaction_category_code = ?2,1=1) and t.status = '1' order by t.create_date desc", countQuery = "select count(*) FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and if(?1 !='',t.transaction_type_code = ?1,1=1) and if(?2 !='',t.transaction_category_code = ?2,1=1) and t.status = '1' order by t.create_date desc", nativeQuery = true)
	public Page<AgriculturalEntity> findAgriType(String transactionTypeCode, String transactionCategoryCode,
			Map<String, List<String>> map, Pageable pageable);

	// 搜索我的发布信息（接口,标题名称）
	@Query(value = "SELECT * FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and if(?1 !='',t.status like ?1,1=1) order by t.create_date desc", countQuery = "select count(*) FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and if(?1 !='',t.status like ?1,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<AgriculturalEntity> findMyPublication(String status, Map<String, List<String>> map, Pageable pageable);

	// 计算天数
	@Query(value = "select to_days(t.end_date) - to_days(t.begin_date) from sas_publication_info t where id=:id", nativeQuery = true)
	public String findDay(@Param("id") String id);
}
