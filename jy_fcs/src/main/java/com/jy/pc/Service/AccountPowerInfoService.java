package com.jy.pc.Service;

import java.util.List;

import com.jy.pc.Entity.AccountPowerInfoEntity;

public interface AccountPowerInfoService {
	// 添加
	public AccountPowerInfoEntity save(AccountPowerInfoEntity accountPowerInfo);

	// 修改
	public void update(AccountPowerInfoEntity accountPowerInfo);

	// 删除
	public void delete(String id);
	
	public void deleteByJurCode(String jurCodel,String accountId);

	// findById
	public List<AccountPowerInfoEntity> findAccountId(String accountId);
	
	public List<AccountPowerInfoEntity> findId(String accountId);
}
