package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.AgriculturalEntity;

public interface AgriculturalDao extends JpaRepository<AgriculturalEntity,String>{
	
	@Query(value="SELECT * FROM sas_agricultural_clothing_info e where e.status = '1'",nativeQuery = true)
	public List<AgriculturalEntity> findAgrSum();
}
