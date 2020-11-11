package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.EduUserManualEntity;

public interface EduUserManualDao extends JpaRepository<EduUserManualEntity, String>{
	
	// 通过userId查询
	@Query(value = "SELECT * FROM edu_user_manual t where t.user_id =:userId and t.manual_info_id =:manualInfoId", nativeQuery = true)
	public EduUserManualEntity findUserId(String userId,String manualInfoId);

}
