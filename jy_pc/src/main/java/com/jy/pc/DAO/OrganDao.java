package com.jy.pc.DAO;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.OrganEntity;

public interface OrganDao extends JpaRepository<OrganEntity,String>{

	@Query(value="select * from sys_organ  where id =:id",nativeQuery = true)
	public OrganEntity findBId(@Param("id")String id);
	
	@Query(value="select * from sys_organ t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.super_id like ?2,1=1) order by t.create_time desc",
			countQuery="select count(*) from sys_organ t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.super_id like ?2,1=1) order by t.create_time desc",
			nativeQuery = true)
	public Page<OrganEntity> findListByName(String name,String superId,Pageable pageable);
	
}
