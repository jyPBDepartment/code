package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.ClassificationEntity;

public interface ClassificationService {
	// 分类添加
	public ClassificationEntity save(ClassificationEntity classificationEntity);

	// 分类修改
	public ClassificationEntity update(ClassificationEntity classificationEntity);

	// 分类删除
	public void delete(String id);

	// 分类findById
	public ClassificationEntity findBId(String id);

	// 分类分页与模糊查询
	public Page<ClassificationEntity> findListByName(String code, String name, Pageable pageable);

	// 分类findAll()
	public List<ClassificationEntity> findAll();

	// 查询上级分类编码
	public List<ClassificationEntity> findSubClassiList();

	// 查询关键词分类编码
	public List<ClassificationEntity> findKeyWordList(String classCode);

	// 查询病虫害分类编码
	public List<ClassificationEntity> findDipList(String classCode);

	// 查询农作物种类分类编码
	public List<ClassificationEntity> findCaseList(String classCode);

	// 删除前查询验证农作物
	public List<ClassificationEntity> findCropLink();

	// 删除前查询验证病虫害
	public List<ClassificationEntity> findDipLink();

	// 删除前查询验证关键词
	public List<ClassificationEntity> findKeywordLink();

	// 查询子菜单
	public List<ClassificationEntity> findListById(String id);

	public boolean findParentCode(@Param("parentCode") String parentCode);

	public List<ClassificationEntity> findClassByCode( String classCode);

	
}
