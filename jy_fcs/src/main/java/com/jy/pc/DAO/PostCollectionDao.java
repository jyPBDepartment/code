package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.PostCollectionEntity;

public interface PostCollectionDao extends JpaRepository<PostCollectionEntity, String> {

	// 通过userId查询
	@Query(value = "SELECT * FROM sas_post_collection t where t.user_id =:userId and t.circle_id =:circleId", nativeQuery = true)
	public PostCollectionEntity findUserId(String userId, String circleId);
}
