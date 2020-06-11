package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.ExplanationEntity;

public interface ExplanationDao extends JpaRepository<ExplanationEntity, String>{
	@Query(value="select * from w_appointment_user t where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.phone_num like ?2,1=1)",
			countQuery="select count(*) from w_appointment_user t where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.phone_num like ?2,1=1)",nativeQuery = true)
	public Page<ExplanationEntity> findListByName(String name,String phoneNum,Pageable pageable);
}
