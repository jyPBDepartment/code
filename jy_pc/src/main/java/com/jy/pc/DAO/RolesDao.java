package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.RolesEntity;

public interface RolesDao extends JpaRepository<RolesEntity, String>{
	@Query(value="select * from sys_role t where t.role_name =:name",nativeQuery = true)
	public RolesEntity findByName(@Param("name")String name);
	
	@Query(value="select * from sys_role t where t.id =:id",nativeQuery = true)
	public RolesEntity findId(@Param("id")String id);
	
	
	@Query(value="select * from sys_role t where if(?1 !='',t.role_name like ?1,1=1) and if(?2!=999,t.role_type like ?2,1=1) order by t.create_time desc",
			countQuery="select count(*) from sys_role t where if(?1 !='',t.role_name like ?1,1=1) and if(?2!=999,t.role_type like ?2,1=1) order by t.create_time desc",nativeQuery = true)
	public Page<RolesEntity> findListByName(String roleName,Integer roleType,Pageable pageable);
	
}
