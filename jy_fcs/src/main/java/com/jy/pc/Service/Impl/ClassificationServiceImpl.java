package com.jy.pc.Service.Impl;

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
	public ClassificationEntity save(ClassificationEntity classificationEntity) {

		return classificationDao.saveAndFlush(classificationEntity);
	}

	@Override
	public ClassificationEntity update(ClassificationEntity classificationEntity) {

		return classificationDao.saveAndFlush(classificationEntity);
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
	public Page<ClassificationEntity> findListByName( String code, Pageable pageable) {
		
		String classiCode = "%" + code + "%";
		
		return classificationDao.findListByName( classiCode, pageable);
	}

	@Override
	public List<ClassificationEntity> findAll() {
		return classificationDao.findAll();

	}

	// 上级分类编码
	@Override
	public List<ClassificationEntity> findSubClassiList() {
		return classificationDao.findSubClassiList();

	}

	@Override
	public List<ClassificationEntity> findKeyWordList() {
		return classificationDao.findKeyWordList();
	}

	//病虫害种类
	@Override
	public List<ClassificationEntity> findDipList() {

		return classificationDao.findDipList();
	}

	//农作物分类
	@Override
	public List<ClassificationEntity> findCaseList() {

		return classificationDao.findCaseList();
	}
	//分类看图识病农作物删除
	@Override
	public List<ClassificationEntity> findCropLink() {
		
		return classificationDao.findCropLink();
	}
	//分类看图识病病虫害删除
	@Override
	public List<ClassificationEntity> findDipLink() {
		
		return classificationDao.findDipLink();
	}
	//分类看图识病关键词删除
	@Override
	public List<ClassificationEntity> findKeywordLink() {
	
		return classificationDao.findKeywordLink();
	}
}
