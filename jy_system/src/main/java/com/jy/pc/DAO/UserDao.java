package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Long> {

	@Query(value = "select * from w_user t where t.user_name=:userName", nativeQuery = true)
	public UserEntity findUserInfo(@Param("userName") String userName);

}
