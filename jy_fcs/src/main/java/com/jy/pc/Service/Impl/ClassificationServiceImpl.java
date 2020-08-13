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

	// 分类添加
	@Override
	public ClassificationEntity save(ClassificationEntity classificationEntity) {

		return classificationDao.saveAndFlush(classificationEntity);
	}

	// 分类修改
	@Override
	public ClassificationEntity update(ClassificationEntity classificationEntity) {

		return classificationDao.saveAndFlush(classificationEntity);
	}

	// 分类删除
	@Override
	public void delete(String id) {

		classificationDao.deleteById(id);
	}

	// 分类查找
	@Override
	public ClassificationEntity findBId(String id) {

		return classificationDao.findBId(id);
	}

	// 分类查询所有
	@Override
	public List<ClassificationEntity> findAll() {
		return classificationDao.findAll();

	}

	// 上级分类编码
	@Override
	public List<ClassificationEntity> findSubClassiList() {
		return classificationDao.findSubClassiList();

	}

	// 关键词关联分类
	@Override
	public List<ClassificationEntity> findKeyWordList(String classCode) {
		return classificationDao.findKeyWordList(classCode);
	}

	// 病虫害关联分类
	@Override
	public List<ClassificationEntity> findDipList() {

		return classificationDao.findDipList();
	}

	// 农作物关联分类
	@Override
	public List<ClassificationEntity> findCaseList() {

		return classificationDao.findCaseList();
	}

	// 分类关联看图识病农作物删除
	@Override
	public List<ClassificationEntity> findCropLink() {

		return classificationDao.findCropLink();
	}

	// 分类关联看图识病病虫害删除
	@Override
	public List<ClassificationEntity> findDipLink() {

		return classificationDao.findDipLink();
	}

	// 分类关联看图识病关键词删除
	@Override
	public List<ClassificationEntity> findKeywordLink() {

		return classificationDao.findKeywordLink();
	}

	@Override
	public boolean findParentCode(String parentCode) {
		int count = classificationDao.findParentCode(parentCode);
		return count > 0 ? true : false;
	}

	// 分类分页与模糊查询
	@Override
	public Page<ClassificationEntity> findListByName(String code, String name, Pageable pageable) {
		String classiCode = "%" + code + "%";
		String classiName = "%" + name + "%";
		return classificationDao.findListByName(classiCode, classiName, pageable);
	}

	@Override
	public List<ClassificationEntity> findClassByCode(String classCode) {
		return classificationDao.findClassByCode(classCode);
	}

	// 查询子菜单
	@Override
	public List<ClassificationEntity> findListById(String id) {

		return classificationDao.findListById(id);
	}
}
