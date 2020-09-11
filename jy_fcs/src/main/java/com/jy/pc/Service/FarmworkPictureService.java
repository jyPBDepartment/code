package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.FarmworkPictureEntity;

public interface FarmworkPictureService {

	// 添加
	public FarmworkPictureEntity save(FarmworkPictureEntity farm);

	// 修改
	public void update(FarmworkPictureEntity farmworkPicture);

	// 删除
	public void delete(String id);
	public List<FarmworkPictureEntity> findPicId(String farmworkId);
}
