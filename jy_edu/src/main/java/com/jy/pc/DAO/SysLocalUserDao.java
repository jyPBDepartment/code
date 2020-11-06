package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.SysLocalUserEntity;

public interface SysLocalUserDao extends JpaRepository<SysLocalUserEntity, String> {
	// 通过id查询
	@Query(value = "select * from sys_local_user t where t.id =:id", nativeQuery = true)
	public SysLocalUserEntity findId(@Param("id") String id);
}
