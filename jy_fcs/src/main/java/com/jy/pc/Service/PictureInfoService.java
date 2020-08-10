package com.jy.pc.Service;

import com.jy.pc.Entity.PictureInfoEntity;

public interface PictureInfoService {

	// 添加
	public PictureInfoEntity save(PictureInfoEntity pictureInfo);

	// 修改
	public void update(PictureInfoEntity pictureInfo);

	// 删除
	public void delete(String id);
}
