package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.AdminEntity;
import com.jy.pc.Entity.LimitEntity;
import com.jy.pc.Entity.RoleEntity;


public interface RoleDao extends JpaRepository<RoleEntity, String>{
	@Query(value="select * from w_role t where t.id =:id",nativeQuery = true)
	public RoleEntity findId(@Param("id")String id);
	
	@Query(value="select * from w_role t where if(?1 !='',t.name like ?1,1=1) order by t.create_time desc",
			countQuery="select count(*) from w_role t where if(?1 !='',t.name like ?1,1=1) order by t.create_time desc",nativeQuery = true)
	public Page<RoleEntity> findListByName(String name,Pageable pageable);
	
	@Query(value="select * from w_role t where t.state ='0'",nativeQuery = true)
	public List<RoleEntity> findAl();
	
	//删除前查询
			@Query(value="select distinct t.id,t.create_time,t.edit_time,t.name,t.state,t.limit_id,t.limit_name from  w_role t where t.id not in (select w.role_id from w_admin w)",nativeQuery = true)
			public List<RoleEntity> findRoleLink();
}
