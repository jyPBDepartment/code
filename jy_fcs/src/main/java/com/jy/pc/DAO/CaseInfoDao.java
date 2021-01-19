package com.jy.pc.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.CaseInfoEntity;

// 看图识病 DAO
public interface CaseInfoDao extends JpaRepository<CaseInfoEntity, String> {

	// fingById方法
	@Query(value = "select * from sas_case_info  where id =:id", nativeQuery = true)
	public CaseInfoEntity findBId(@Param("id") String id);

	// 分页与模糊查询
	@Query(value = "select * from sas_case_info  t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.audit_status like ?2,1=1) order by t.create_date desc", countQuery = "select count(*) from sas_case_info t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.audit_status like ?2,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<CaseInfoEntity> findListByName(String name, String auditStatus, Pageable pageable);

	// 查询所有病虫害信息的最新3条记录
	@Query(value = "select * from sas_case_info  t  where audit_status='1' order by t.create_date desc limit 3", nativeQuery = true)
	public List<CaseInfoEntity> findCaseInfo();

	// 关键字搜索病虫害信息
	@Query(value = " select distinct t.* from (sas_case_info t inner join sas_case_key d on t.id=d.case_id inner join  sas_key_word k on d.key_id=k.id) where k.name like :name order by t.create_date desc", nativeQuery = true)
	public List<CaseInfoEntity> findCaseInfoByKey(@Param("name") String name);

	@Query(value = "select * from sas_case_info  t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.classi_code = ?2,1=1) and if(?3 !='',t.classi_dip_code = ?3,1=1) and t.audit_status = 1 order by t.create_date desc", countQuery = "select count(*) from sas_case_info  t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.classi_code = ?2,1=1) and if(?3 !='',t.classi_dip_code = ?3,1=1) and t.audit_status = 1 order by t.create_date desc", nativeQuery = true)
	public Page<CaseInfoEntity> findPage(String name, String cropsTypeCode, String dipTypeCode, Pageable pageable);

	// 看图识病最火查询
	@Query(value = "select * from sas_case_info  t where t.audit_status='1' order by t.browse_num desc", 
			countQuery = "select count(*) from sas_case_info t where t.audit_status='1' order by t.browse_num desc", nativeQuery = true)
	public Page<CaseInfoEntity> findByBowNum(Pageable pageable);

	// 看图识病最新查询
	@Query(value = "select * from sas_case_info  t where t.audit_status='1' order by t.create_date desc", 
			countQuery = "select count(*) from sas_case_info t  where t.audit_status='1' order by t.create_date desc", nativeQuery = true)
	public Page<CaseInfoEntity> findByNewNum(Pageable pageable);

	// 看图识病精选查询
	@Query(value = "select * from sas_case_info  t where t.audit_status='1' and t.is_selected=1 order by t.create_date desc", 
			countQuery = "select count(*) from sas_case_info t  where t.audit_status='1' and t.is_selected=1 order by t.create_date desc", nativeQuery = true)
	public Page<CaseInfoEntity> findBySelected(Pageable pageable);

	// 看图识病热议查询
	@Query(value = "select * from sas_case_info  t where t.audit_status='1' order by t.comment_num desc", 
			countQuery = "select count(*) from sas_case_info t where t.audit_status='1' order by t.comment_num desc", nativeQuery = true)
	public Page<CaseInfoEntity> findByCommNum(Pageable pageable);

	// 看图识病好评查询
	@Query(value = "select * from sas_case_info  t where t.audit_status='1' order by t.praise_num desc", 
			countQuery = "select count(*) from sas_case_info t where t.audit_status='1' order by t.praise_num desc", nativeQuery = true)
	public Page<CaseInfoEntity> findByPraNum(Pageable pageable);
	
	// 通过id userId查询看图识病信息
	@Query(value = "select *,(select t1.id from sas_case_info_collection t1 where t.id=t1.case_id and t1.collection_user_id=:userId) as is_collection,(select t2.id from sas_case_praise t2 where t.id=t2.case_id and t2.praise_user_id=:userId) as is_praise from sas_case_info t where t.id =:id", nativeQuery = true)
	public Map<String,Object> findInfoByUserId(@Param("id") String id,@Param("userId") String userId);
	
	//	查询看图识病列表
	@Query(value = "SELECT t.id,t.url,t.name,t.update_date as updateDate,praise_num as praiseNum,browse_num as browseNum,collection_num as collectionNum,if((select count(0) from sas_case_info_collection where case_id = t.id and collection_user_id in :#{#map['userId']}) > 0,1,0) as isUserCollection,if((select count(0) from sas_case_praise where case_id = t.id and praise_user_id in :#{#map['userId']}) > 0,1,0) as isUserPraise,if((select count(0) from sas_case_info_irrelevant where case_id = t.id and irrelevantn_user_id in :#{#map['userId']}) > 0,1,0) as isUserIrrelevant FROM sas_case_info t where if(?1 !='',t.name like ?1,1=1) and t.audit_status='1' and if(?2 !='',t.crops_type_code = ?2,1=1) and if(?3 !='',t.dip_type_code = ?3,1=1) and if(?4 ='3',t.is_selected = 1,1=1) order by case when ?4='1' then browse_num end desc,case when ?4='2' then create_date end desc,case when ?4='3' then is_selected end desc,case when ?4='4' then comment_num end desc,case when ?4='5' then praise_num end desc",
			countQuery = "select count(*) FROM sas_case_info t where if(?1 !='',t.name like ?1,1=1) and t.audit_status='1' and if(?2 !='',t.crops_type_code = ?2,1=1) and if(?3 !='',t.dip_type_code = ?3,1=1) and if(?4 ='3',t.is_selected = 1,1=1) order by t.create_date desc", 
			nativeQuery = true)
	public Page<List<Map<String, Object>>> findCaseInfo(String name, String cropsTypeCode,String dipTypeCode, String sort,Map<String,Object> map,Pageable pageable);

	// 看图识病查看详情H5
	@Query(value = "SELECT t.*,if((select count(0) from sas_case_info_collection where case_id = t.id and collection_user_id in :#{#map['userId']}) > 0,1,0) as isUserCollection,if((select count(0) from sas_case_praise where case_id = t.id and praise_user_id in :#{#map['userId']}) > 0,1,0) as isUserPraise,if((select count(0) from sas_case_info_irrelevant where case_id = t.id and irrelevantn_user_id in :#{#map['userId']}) > 0,1,0) as isUserIrrelevant FROM sas_case_info t where t.id =:id", nativeQuery = true)
	public Map<String, Object> findChannelId(Map<String,Object> map,String id);
}
