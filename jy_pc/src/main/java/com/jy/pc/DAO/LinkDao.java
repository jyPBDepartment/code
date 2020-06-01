package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.LinkEntity;


public interface LinkDao extends JpaRepository<LinkEntity, String> {
	@Query(value="select * from sys_link where sales_id =:salesId",nativeQuery = true)
	public LinkEntity findBId(@Param("salesId")String salesId);
}
