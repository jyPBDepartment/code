package com.jy.pc.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.CaseInfoCollectionEntity;

//看图识病收藏表DAO
public interface CaseInfoCollectionDAO extends JpaRepository<CaseInfoCollectionEntity, String>{
	// 根据收藏用户id查询
	@Query(value = "select * from sas_case_info_collection  where collection_user_id =:collectionUserId",
			countQuery = "select count(*) from sas_case_info_collection  where collection_user_id =:collectionUserId",nativeQuery = true)
	public Page<CaseInfoCollectionEntity> findUserId(String collectionUserId, Pageable pageable);
	
	// 根据收藏用户id 看图识病id查询
	@Query(value = "select * from sas_case_info_collection t where t.case_id =:caseId and  t.collection_user_id =:collectionUserId", nativeQuery = true)
	public CaseInfoCollectionEntity findCaseUserId(String caseId, String collectionUserId);
	
	/**
	 * 我的-我的收藏
	 * 
	 * @Param userId 用户Id
	 */
	@Query(value = "select t.id,t.audit_status as auditStatus,t.classi_code as classiCode,t.classi_dip_code as classiDipCode,t.create_date as createDate,t.create_user as createUser,t.crops_type_code as cropsTypeCode,t.describetion,t.dip_type_code as dipTypeCode,t.name,t.update_date as updateDate,t.url,t.browse_num as browseNum,t.channel,t.collection_num as collectionNum,t.comment_num as commentNum,t.control_technology as controlTechnology,t.harm,t.is_selected as isSelected,t.negative_num as negativeNum,t.praise_num as praiseNum,t.update_user as updateUser,t.irrelevant_num as irrelevantNum,t.is_irrelevant as isIrrelevant from sas_case_info t,sas_case_info_collection t1 where t.id = t1.case_id and t1.collection_user_id =:collectionUserId order by t.create_date desc",
			countQuery = "select count(0) from sas_case_info t,sas_case_info_collection t1 where t.id = t1.case_id and t1.collection_user_id =:collectionUserId order by t.create_date desc", nativeQuery = true)
	public Page<List<Map<String, Object>>> findPageByParam(String collectionUserId, Pageable pageable);

}
