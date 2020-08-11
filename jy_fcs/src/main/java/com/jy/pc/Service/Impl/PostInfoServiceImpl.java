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

	//农活预约总数
	@Override
	public List<PostInfoEntity> findInva() {

		return invitationDao.findInva();
	}

	//查询
	@Override
	public Page<PostInfoEntity> findListByName(String name, String createUser, Pageable pageable) {
		String invName = "%" + name + "%";
		String user = "%" + createUser + "%";
		return invitationDao.findListByName(invName, user, pageable);
	}
	
	//findById
	@Override
	public PostInfoEntity findId(String id) {
		return invitationDao.findId(id);
	}

	//修改
	@Override
	public void update(PostInfoEntity invitation) {
		invitationDao.saveAndFlush(invitation);
	}

	//添加
	@Override
	public PostInfoEntity save(PostInfoEntity postInfo) {
		return invitationDao.saveAndFlush(postInfo);
	}

}
