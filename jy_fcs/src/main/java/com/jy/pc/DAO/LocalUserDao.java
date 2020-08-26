package com.jy.pc.DAO;

import com.jy.pc.Entity.LocalUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocalUserDao extends JpaRepository<LocalUserEntity, String> {
	// 通过id查询
	@Query(value = "select * from sas_local_user t where t.id =:id", nativeQuery = true)
	public LocalUserEntity findId(@Param("id") String id);
}
