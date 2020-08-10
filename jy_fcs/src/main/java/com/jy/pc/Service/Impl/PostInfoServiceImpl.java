package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.PostInfoDao;
import com.jy.pc.Entity.PostInfoEntity;
import com.jy.pc.Service.PostInfoService;

@Service

public class PostInfoServiceImpl implements PostInfoService {

	@Autowired
	private PostInfoDao invitationDao;

	@Override
	public List<PostInfoEntity> findInva() {

		return invitationDao.findInva();
	}

	public List<PostInfoEntity> findListByType(String type) {

		return invitationDao.findListByType(type);
	}
	
	@Override
	public Page<PostInfoEntity> findListByName(String name, String createUser, Pageable pageable) {
		String invName = "%" + name + "%";
		String user = "%" + createUser + "%";
		return invitationDao.findListByName(invName, user, pageable);
	}

	@Override
	public PostInfoEntity findId(String id) {
		return invitationDao.findId(id);
	}

	@Override
	public void update(PostInfoEntity invitation) {
		invitationDao.saveAndFlush(invitation);
	}

}
