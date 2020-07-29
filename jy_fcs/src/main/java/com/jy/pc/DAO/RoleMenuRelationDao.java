package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.RoleMenuRelationEntity;

public interface RoleMenuRelationDao extends JpaRepository<RoleMenuRelationEntity,String>{
	@Query(value="select * from sas_role_menu_relation t where t.id =:id",nativeQuery = true)
	public RoleMenuRelationEntity findId(@Param("id")String id);
	
	@Query(value="select * from sas_role_menu_relation t where t.role_id =:roleId",nativeQuery = true)
	public List<RoleMenuRelationEntity> findRelationByRole(@Param("roleId")String roleId);
	
	@Query(value="select * from sas_role_menu_relation t where t.menu_id =:menuId",nativeQuery = true)
	public List<RoleMenuRelationEntity> findRelationByMenu(@Param("menuId")String menuId);
	
	@Query(value="select count(0) from sas_role_menu_relation t where t.role_id = ?1 and t.menu_id = ?2",nativeQuery = true)
	public int checkRealtion(String roleId,String menuId);
}
