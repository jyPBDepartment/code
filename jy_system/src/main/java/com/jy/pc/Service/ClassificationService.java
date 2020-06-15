package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.ClassificationEntity;


public interface ClassificationService {
	//分类添加
		public void save(ClassificationEntity classificationEntity);
		//分类修改
		public void update(ClassificationEntity classificationEntity);
		//分类删除
		public void delete(String id);
		//分类findById
		public ClassificationEntity findBId(String id);
		//分类分页与模糊查询
		public Page<ClassificationEntity> findListByName(String name,Pageable pageable);
		public List<ClassificationEntity> findAll();
//		public ClassificationEntity findByWord(String name);
}
