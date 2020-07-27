package com.jy.pc.Service;

import com.jy.pc.Entity.AccountPowerInfoEntity;

public interface AccountPowerInfoService {
	// 添加
	public AccountPowerInfoEntity save(AccountPowerInfoEntity accountPowerInfo);

	// 修改
	public void update(AccountPowerInfoEntity accountPowerInfo);

	// 删除
	public void delete(String id);

	// findById
	public AccountPowerInfoEntity findAccountId(String accountId);

}
