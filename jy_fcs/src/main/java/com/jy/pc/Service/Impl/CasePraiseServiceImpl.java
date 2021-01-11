package com.jy.pc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.CasePraiseDAO;
import com.jy.pc.Entity.CasePraiseEntity;
import com.jy.pc.Service.CasePraiseService;

//看图识病点赞表 ServiceImpl
@Service
public class CasePraiseServiceImpl implements CasePraiseService{
	
	@Autowired
	private CasePraiseDAO casePraiseDAO;

	//保存
	@Override
	public CasePraiseEntity save(CasePraiseEntity casePraiseEntity) {
		return casePraiseDAO.saveAndFlush(casePraiseEntity);
	}

	//删除
	@Override
	public void delete(String id) {
		casePraiseDAO.deleteById(id);
	}

	// 通过看图识病id 点赞用户id查询
	@Override
	public CasePraiseEntity findUserId(String caseId, String praiseUserId) {
		return casePraiseDAO.findUserId(caseId, praiseUserId);
	}
	

}
