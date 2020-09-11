package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.FarmworkEntity;

public interface FarmworkDao extends JpaRepository<FarmworkEntity, String> {

	// 记录农活预约数量
	@Query(value = "SELECT * FROM sas_farmwork_appointment_info t where t.status = '0'", nativeQuery = true)
	public List<FarmworkEntity> findSum();

	// 通过id查询
	@Query(value = "select * from sas_farmwork_appointment_info t where t.id =:id", nativeQuery = true)
	public FarmworkEntity findId(@Param("id") String id);

	
	@Query(value = "SELECT * FROM sas_farmwork_appointment_info t where t.operate_user_id = ?1 and if(?2 !='',t.status like ?2,1=1)", 
			countQuery="SELECT count(*) FROM sas_farmwork_appointment_info t where t.operate_user_id = ?1 and if(?2 !='',t.status like ?2,1=1)",
			nativeQuery = true)
	public Page<FarmworkEntity> findMyFarm(String userId,String status, String user, Pageable pageable);
	
	//意向用户
	@Query(value = "SELECT * FROM sas_farmwork_appointment_info t where  t.agricultural_id in (select d.id from sas_publication_info d where d.create_user_id = ?1 order by d.create_date desc ) and if(?2 !='',t.status like ?2,1=1)",
			countQuery="SELECT count(*) FROM sas_farmwork_appointment_info t where t.agricultural_id in (select d.id from sas_publication_info d where d.create_user_id = ?1 order by d.create_date desc ) and if(?2 !='',t.status like ?2,1=1)",
			nativeQuery = true)
	public Page<FarmworkEntity> findFarmForMe(String userId,String status, String user, Pageable pageable);


}
