package com.jy.pc.Service;

import com.jy.pc.Entity.FarmworkPictureEntity;

public interface FarmworkPictureService {

	// 添加
	public FarmworkPictureEntity save(FarmworkPictureEntity farmworkPicture);

	// 修改
	public void update(FarmworkPictureEntity farmworkPicture);

	// 删除
	public void delete(String id);
}
