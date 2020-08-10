package com.jy.pc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.CaseKeyDAO;
import com.jy.pc.Entity.CaseKeyEntity;
import com.jy.pc.Service.CaseKeyService;

@Service
public class CaseKeyServiceImpl implements CaseKeyService{
	@Autowired
	private CaseKeyDAO caseKeyDAO;

	@Override
	public CaseKeyEntity save(CaseKeyEntity caseKey) {
		return caseKeyDAO.saveAndFlush(caseKey);
	}

	@Override
	public void update(CaseKeyEntity caseKey) {
		caseKeyDAO.saveAndFlush(caseKey);
	}

	@Override
	public void delete(String id) {
		caseKeyDAO.deleteById(id);
	}

}
