package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.PostInfoEntity;


public interface PostInfoService {

	//农活预约总数
	public List<PostInfoEntity> findInva();
	public void update(PostInfoEntity invitation);
	public Page<PostInfoEntity> findListByName(String name,String createUser,Pageable pageable);
	public PostInfoEntity findId(String id);
}
