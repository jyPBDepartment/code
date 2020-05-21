package com.jy.pc.DAO;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.JurisdictionEntity;

public interface JurisdictionDao extends JpaRepository<JurisdictionEntity, String>{
	
	@Query(value="select * from sys_jurisdiction t where t.name =:name",nativeQuery = true)
	public JurisdictionEntity findByName(@Param("name")String name);
	
	@Query(value="select * from sys_jurisdiction t where t.id =:id",nativeQuery = true)
	public JurisdictionEntity findId(@Param("id")String id);
	
	@Query(value="select * from sys_jurisdiction t where if(?1 !='',t.name like ?1,1=1) and if(?2!=999,t.type = ?2,1=1)",
			countQuery="select count(*) from sys_jurisdiction t where if(?1 !='',t.name like ?1,1=1) and if(?2!=999,t.type = ?2,1=1)",nativeQuery = true)
	public Page<JurisdictionEntity> findListByName(String name,Integer type,Pageable pageable);
}
