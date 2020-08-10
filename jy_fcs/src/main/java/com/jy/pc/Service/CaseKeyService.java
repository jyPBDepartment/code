package com.jy.pc.Service;

import com.jy.pc.Entity.CaseKeyEntity;

public interface CaseKeyService {
	//添加
	public CaseKeyEntity save(CaseKeyEntity caseKey);

	// 修改
	public void update(CaseKeyEntity caseKey);

	// 删除
	public void delete(String id);

}
