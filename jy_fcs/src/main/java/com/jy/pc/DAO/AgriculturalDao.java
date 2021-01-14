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

	// 根据id查询我的农服，农机，粮食买卖信息详情（h5）
	@Query(value = "select * from sas_publication_info t where t.id =:id", nativeQuery = true)
	public AgriculturalEntity findMineId(@Param("id") String id);

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
	@Query(value = "SELECT t.id,t.address,t.url,t.name,t.contacts_user as contactsUser,t.update_date as updateDate,praise_num as praiseNum,view_num as viewNum,collection_num as collectionNum,if((select count(0) from sas_grain_trading_collection where arg_id = t.id and collection_user_id in :#{#map['userId']}) > 0,1,0) as curCollention,if((select count(0) from sas_grain_trading_praise where arg_id = t.id and praise_user_id in :#{#map['userId']}) > 0,1,0) as curPraise  FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and if(?1 !='',t.name like ?1,1=1) and t.status in ('1','4') and if(?2 !='',t.transaction_type_code = ?2,1=1) and if(?3 !='',t.transaction_category_code = ?3,1=1) and if(?4 !='',t.identity_code = ?4,1=1) and if(?5 !='',t.address like ?5,1=1) and if(?6 ='3',t.is_selected = 1,1=1) order by case when ?6='1' then view_num end desc,case when ?6='2' then create_date end desc,case when ?6='4' then comment_num end desc,case when ?6='5' then praise_num end desc",
			countQuery = "select count(*) FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and if(?1 !='',t.name like ?1,1=1) and t.status in ('1','4')  and if(?2 !='',t.transaction_type_code = ?2,1=1) and if(?3 !='',t.transaction_category_code = ?3,1=1) and if(?4 !='',t.identity_code = ?4,1=1) and if(?5 !='',t.address like ?5,1=1) and if(?6 ='3',t.is_selected = 1,1=1) order by t.create_date desc", 
			nativeQuery = true)
	public Page<List<Map<String, Object>>> findAgriInfo(String name, String transactionTypeCode,
			String transactionCategoryCode,String identityCode,String address, String sort,Map<String, List<String>> map,Pageable pageable);

//	搜索发布信息中农服信息(类型、类别)
	@Query(value = "SELECT * FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and if(?1 !='',t.transaction_type_code = ?1,1=1) and if(?2 !='',t.transaction_category_code = ?2,1=1) and if(?3 !='',t.identity_code = ?3,1=1) and t.status = '1' order by t.create_date desc", countQuery = "select count(*) FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and if(?1 !='',t.transaction_type_code = ?1,1=1) and if(?2 !='',t.transaction_category_code = ?2,1=1) and if(?3 !='',t.identity_code = ?3,1=1) and t.status = '1' order by t.create_date desc", nativeQuery = true)
	public Page<AgriculturalEntity> findAgriType(String transactionTypeCode, String transactionCategoryCode,String identityCode,
			Map<String, List<String>> map, Pageable pageable);

	// 搜索我的发布信息（接口,标题名称）
	@Query(value = "SELECT * FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and if(?1 !='',t.status like ?1,1=1) and if(?2 !='',t.create_user_id = ?2,1=1) order by t.create_date desc", countQuery = "select count(*) FROM sas_publication_info t where t.transaction_type_code in :#{#map['type']} and t.transaction_category_code in :#{#map['category']} and if(?1 !='',t.status like ?1,1=1) and if(?2 !='',t.create_user_id = ?2,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<AgriculturalEntity> findMyPublication(String status, String userId,Map<String, List<String>> map, Pageable pageable);
}
