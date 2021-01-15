package com.jy.pc.Service;

import com.jy.pc.Entity.AccountRoleInfoEntity;

public interface AccountRoleInfoService {
	
	public void save(AccountRoleInfoEntity accountRoleInfoEntity);
	
	public AccountRoleInfoEntity findRoleIdByAccountId(String accountId);
	
	public void delete(String id);

}
