package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.InvitationDao;
import com.jy.pc.Entity.InvitationEntity;
import com.jy.pc.Service.InvitationService;

@Service

public class InviationServiceImpl implements InvitationService{

	@Autowired
	private InvitationDao invitationDao ;
	@Override
	public List<InvitationEntity> findInva() {
		
		return invitationDao.findInva();
	}

}
