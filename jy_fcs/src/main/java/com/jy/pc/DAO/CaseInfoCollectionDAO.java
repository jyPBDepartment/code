package com.jy.pc.DAO;

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

}
