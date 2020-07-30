package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.AccountInfoEntity;

public interface AccountInfoService {
	public Boolean checkUser(String name, String password);
	// 搜索
	public Page<AccountInfoEntity> findListByName(String name,String phone,String auditStatus, Pageable pageable);
	// 添加
	public AccountInfoEntity save(AccountInfoEntity accountInfo);

	// 修改
	public void update(AccountInfoEntity accountInfo);

	// 删除
	public void delete(String id);
	//findbyid
	public AccountInfoEntity findId(String id);
	public AccountInfoEntity findUserInfo(String name, String password);

}
