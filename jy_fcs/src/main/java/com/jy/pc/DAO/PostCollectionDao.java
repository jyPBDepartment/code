package com.jy.pc.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.PostCollectionEntity;

public interface PostCollectionDao extends JpaRepository<PostCollectionEntity, String> {

	// 通过userId查询
	@Query(value = "SELECT * FROM sas_post_collection t where t.user_id =:userId and t.circle_id =:circleId", nativeQuery = true)
	public PostCollectionEntity findUserId(String userId, String circleId);

	/**
	 * 我的-我的收藏
	 * 
	 * @Param userId 用户Id
	 */
	@Query(value = "select t.id,t.code,t.comment_num as commentNum,t.author,t.audit_optinion as auditOptinion,t.parent_code as parentCode,t.is_selected as isSelected,t.collection_num as collectionNum,t.comment_id as commentId,t.name,t.status,t.create_user_id as createUserId,t.create_date as createDate,t.update_date as updateDate,t.audit_user as auditUser,t.visibility,t.praise_num as praiseNum,t.is_anonymous as isAnonymous,t.reason,t.audit_status as auditStatus,t.create_user as createUser,t.pageview from sas_post_info t,sas_post_collection t1 where t.id = t1.circle_id and t1.user_id =:userId", countQuery = "select count(t.id,t.code,t.comment_num as commentNum,t.author,t.audit_optinion as auditOptinion,t.parent_code as parentCode,t.is_selected as isSelected,t.collection_num as collectionNum,t.comment_id as commentId,t.name,t.status,t.create_user_id as createUserId,t.create_date as createDate,t.update_date as updateDate,t.audit_user as auditUser,t.visibility,t.praise_num as praiseNum,t.is_anonymous as isAnonymous,t.reason,t.audit_status as auditStatus,t.create_user as createUser,t.pageview) from sas_post_info t,sas_post_collection t1 where t.id =t1.circle_id and t1.user_id =:userId", nativeQuery = true)
	public Page<List<Map<String, Object>>> findPageByParam(String userId, Pageable pageable);

}
