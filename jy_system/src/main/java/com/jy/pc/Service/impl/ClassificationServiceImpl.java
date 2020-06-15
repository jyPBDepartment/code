package com.jy.pc.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.ClassificationDao;
import com.jy.pc.Entity.ClassificationEntity;
import com.jy.pc.Service.ClassificationService;

@Service
public class ClassificationServiceImpl implements ClassificationService {
	@Autowired
	private ClassificationDao classificationDao;
	@Override
	public void save(ClassificationEntity classificationEntity) {
		
		classificationDao.save(classificationEntity);
	}

	@Override
	public void update(ClassificationEntity classificationEntity) {
		
		classificationDao.saveAndFlush(classificationEntity);
	}

	@Override
	public void delete(String id) {
		
		classificationDao.deleteById(id);
	}

	@Override
	public ClassificationEntity findBId(String id) {
		
		return classificationDao.findBId(id);
	}

	@Override
	public Page<ClassificationEntity> findListByName(String name, Pageable pageable) {
		String classiName = "%"+name+"%";
		return classificationDao.findListByName(classiName, pageable);
	}
	@Override
	public List<ClassificationEntity> findAll() {
		
		return classificationDao.findAll();
	}

}
