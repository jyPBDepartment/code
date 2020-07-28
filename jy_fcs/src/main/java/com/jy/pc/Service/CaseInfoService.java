package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.CaseInfoEntity;

public interface CaseInfoService {
		
		//添加
		public CaseInfoEntity save(CaseInfoEntity caseInfoEntity);
		//findById
		public CaseInfoEntity findBId(String id);
		//分页与模糊查询
		public Page<CaseInfoEntity> findListByName(String name,String auditStatus,Pageable pageable);
		//修改
		public CaseInfoEntity update(CaseInfoEntity caseInfoEntity);
		//删除
		public void delete(String id);
		
}
