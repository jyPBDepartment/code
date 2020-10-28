package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.AccountRoleInfoEntity;

public interface AccountRoleInfoDao extends JpaRepository<AccountRoleInfoEntity, String> {
	
	@Query(value="select * from sas_account_role_info t where t.account_id=:accountId",nativeQuery=true)
	public AccountRoleInfoEntity findRoleIdByAccountId(@Param("accountId") String accountId);
}
