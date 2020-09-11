package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.FarmworkPictureDAO;
import com.jy.pc.Entity.FarmworkPictureEntity;
import com.jy.pc.Service.FarmworkPictureService;

@Service
public class FarmworkPictureServiceImpl implements FarmworkPictureService{
	@Autowired 
	private FarmworkPictureDAO farmworkPictureDAO;

	@Override
	public FarmworkPictureEntity save(FarmworkPictureEntity farmworkPicture) {
		return farmworkPictureDAO.saveAndFlush(farmworkPicture);
	}

	@Override
	public void update(FarmworkPictureEntity farmworkPicture) {
		farmworkPictureDAO.saveAndFlush(farmworkPicture);
	}

	@Override
	public void delete(String id) {
		farmworkPictureDAO.deleteById(id);
	}

	@Override
	public List<FarmworkPictureEntity> findPicId(String farmworkId) {
		return farmworkPictureDAO.findPicId(farmworkId);
	}

}
