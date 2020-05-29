package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.RelationEntity;

public interface RelationDao extends JpaRepository<RelationEntity, String>{
	
//	@Query(value="select * from sys_relation t where t.role_name =:name",nativeQuery = true)
//	public RolesEntity findByName(@Param("name")String name);
	
	@Query(value="select * from sys_relation t where t.limit_id =:relationId",nativeQuery = true)
	//@Query(value="select t.limit_id,d.limit_id from sys_role t inner join sys_relation d on d.limit_id=t.limit_id",nativeQuery = true)
	public RelationEntity findRelationId(@Param("relationId")String relationId);
}
