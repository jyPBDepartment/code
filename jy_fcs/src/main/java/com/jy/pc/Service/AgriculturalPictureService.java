package com.jy.pc.Service;

import java.util.List;

import com.jy.pc.Entity.AgriculturalPictureEntity;

public interface AgriculturalPictureService {

	// 添加
	public AgriculturalPictureEntity save(AgriculturalPictureEntity agriculturalPicture);

	// 修改
	public void update(AgriculturalPictureEntity agriculturalPicture);

	// 删除
	public void delete(String id);
	//查询
	public List<AgriculturalPictureEntity> findByAgrId(String id);
}
