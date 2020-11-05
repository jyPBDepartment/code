package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.EduIssueInfoEntity;

public interface EduIssueDao extends JpaRepository<EduIssueInfoEntity, String> {
	// GetById方法
	@Query(value = "select * from edu_issue_info  where id =:id", nativeQuery = true)
	public EduIssueInfoEntity GetById(@Param("id") String id);

	// 分页与模糊查询
	@Query(value = "select * from edu_issue_info  t  where if(?1 ='',1=1,t.user_id in (select id from sys_local_user where user_name like concat('%',?1,'%') )) and if(?2 !='',t.issue_state = ?2,1=1) and if(?3 !='',t.issue_by like ?3,1=1) order by t.create_date desc", countQuery = "select count(*) from edu_formwork_info t  where if(?1 ='',1=1,t.user_id in (select id from sys_local_user where user_name like concat('%',?1,'%') )) and if(?2 !='',t.issue_state = ?2,1=1) and if(?3 !='',t.issue_by like ?3,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<EduIssueInfoEntity> findListByName(String name, String status, String createBy, Pageable pageable);
	
	@Query(value = "select * from edu_issue_info  t  where issue_state = 1 and if(?1 ='',1=1,t.user_id in (select id from sys_local_user where user_name like concat('%',?1,'%') )) and if(?2 ='',1=1,t.user_id in (select id from sys_local_user where user_card like concat('%',?2,'%') )) and if(?3 ='',1=1,t.certificate_id in (select id from edu_certificate_info where vocation_id = ?3 )) order by t.create_date desc", 
			countQuery = "select count(*) from edu_formwork_info t  where issue_state = 1 and if(?1 ='',1=1,t.user_id in (select id from sys_local_user where user_name like concat('%',?1,'%') )) and if(?2 ='',1=1,t.user_id in (select id from sys_local_user where user_card like concat('%',?2,'%') )) and if(?3 ='',1=1,t.certificate_id in (select id from edu_certificate_info where vocation_id = ?3 )) order by t.create_date desc", nativeQuery = true)
	public Page<EduIssueInfoEntity> findMgtByParam(String userName, String card, String vocationId, Pageable pageable);
}
