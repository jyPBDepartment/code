package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.CircleThumbsEntity;

public interface CircleThumbsDao extends JpaRepository<CircleThumbsEntity, String> {

	// 通过userId查询
	@Query(value = "SELECT * FROM sas_circle_thumbs t where t.thumbs_user_id =:thumbsUserId and t.circle_id =:circleId", nativeQuery = true)
	public CircleThumbsEntity findUserId(String thumbsUserId, String circleId);
}
