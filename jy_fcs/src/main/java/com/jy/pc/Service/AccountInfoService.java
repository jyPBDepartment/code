package com.jy.pc.Service;

import java.util.Map;

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
	//通过name，password查询数据
	public AccountInfoEntity findUserInfo(String name, String password);
	//切换状态
	void enable(AccountInfoEntity accountInfo,boolean result);
	//修改密码
	void updatePwd(AccountInfoEntity accountInfo);
	
	Map<String, String> updateJur(String accountId,String addItem,String deleteItem);

}
