package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.AgriculturalPivtureDAO;
import com.jy.pc.Entity.AgriculturalPictureEntity;
import com.jy.pc.Service.AgriculturalPictureService;

@Service
public class AgriculturaPictureServiceImpl implements AgriculturalPictureService{
	@Autowired
	private AgriculturalPivtureDAO agriculturalPivtureDAO;

	@Override
	public AgriculturalPictureEntity save(AgriculturalPictureEntity agriculturalPicture) {
		return agriculturalPivtureDAO.saveAndFlush(agriculturalPicture);
	}

	@Override
	public void update(AgriculturalPictureEntity agriculturalPicture) {
		agriculturalPivtureDAO.saveAndFlush(agriculturalPicture);
	}

	@Override
	public void delete(String id) {
		agriculturalPivtureDAO.deleteById(id);
		
	}
	
	@Override
	public List<AgriculturalPictureEntity> findByAgrId(String id){
		return agriculturalPivtureDAO.findByAgrId(id);
	}

	@Override
	public AgriculturalPictureEntity findByPicId(String picId) {
		return agriculturalPivtureDAO.findByPicId(picId);
	}

}
