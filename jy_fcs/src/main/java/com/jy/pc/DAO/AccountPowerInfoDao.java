package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.AccountPowerInfoEntity;

public interface AccountPowerInfoDao extends JpaRepository<AccountPowerInfoEntity, String>{
	@Query(value="select * from sas_account_power_info t where t.account_id =:accountId",nativeQuery = true)
	//@Query(value="select t.limit_id,d.limit_id from sys_role t inner join sys_relation d on d.limit_id=t.limit_id",nativeQuery = true)
	public AccountPowerInfoEntity findAccountId(@Param("accountId")String accountId);

}
