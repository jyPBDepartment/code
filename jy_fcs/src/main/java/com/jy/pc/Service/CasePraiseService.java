package com.jy.pc.Service;

import com.jy.pc.Entity.CasePraiseEntity;

//看图识病点赞表 Service
public interface CasePraiseService {
	// 添加
	public CasePraiseEntity save(CasePraiseEntity casePraiseEntity);

	// 删除
	public void delete(String id);
	
	// 通过看图识病id 点赞用户id查询
	public CasePraiseEntity findUserId(String caseId, String praiseUserId);
}
