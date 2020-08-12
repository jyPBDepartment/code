package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.FarmworkEntity;

public interface FarmworkDao extends JpaRepository<FarmworkEntity,String>{

	//记录农活预约数量
	@Query(value="SELECT * FROM sas_farmwork_appointment_info t where t.status = '0'",nativeQuery = true)
	public List<FarmworkEntity> findSum();
	
}
