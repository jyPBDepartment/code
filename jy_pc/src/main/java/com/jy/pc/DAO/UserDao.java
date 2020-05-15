package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Long>{
	
	//extends JpaRepository<UserEntity, Long>
	@Query(value="select * from user t where t.user_name=:userName",nativeQuery = true)
	public UserEntity findUserInfo(@Param("userName")String userName);
	
	
	@Query(value = "SELECT * FROM USERS WHERE LASTNAME = ?1",
		    countQuery = "SELECT count(*) FROM USERS WHERE LASTNAME = ?1",
		    nativeQuery = true)
		  Page<UserEntity> findByLastname(String lastname, Pageable pageable);
}
