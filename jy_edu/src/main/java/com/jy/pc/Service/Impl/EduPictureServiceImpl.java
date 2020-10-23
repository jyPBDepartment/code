package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.EduPictureDao;
import com.jy.pc.Entity.EduPictureInfoEntity;
import com.jy.pc.Service.EduPictureService;

@Service
public class EduPictureServiceImpl implements EduPictureService{
	@Autowired
	EduPictureDao eduPictureDao;

	//分页与模糊查询
	@Override
	public Page<EduPictureInfoEntity> findListByName(String createBy,String picType, String status, Pageable pageable) {
		String createName="%"+ createBy +"%";
		return eduPictureDao.findListByName(createName,picType, status, pageable);
	}

	//新增
	@Override
	public EduPictureInfoEntity save(EduPictureInfoEntity eduPictureInfoEntity) {
		return eduPictureDao.saveAndFlush(eduPictureInfoEntity);
	}

	//修改
	@Override
	public void update(EduPictureInfoEntity eduPictureInfoEntity) {
		eduPictureDao.saveAndFlush(eduPictureInfoEntity);
	}

	//删除
	@Override
	public void delete(String id) {
		eduPictureDao.deleteById(id);
	}

	//通过id查询
	@Override
	public EduPictureInfoEntity findId(String id) {
		return eduPictureDao.findId(id);
	}

	//调整状态
	@Override
	@Transactional
	public void enable(EduPictureInfoEntity eduPictureInfoEntity, boolean result) {
		eduPictureDao.saveAndFlush(eduPictureInfoEntity);
	}

	//查询排序
	@Override
	public List<EduPictureInfoEntity> findSort() {
		return eduPictureDao.findSort();
	}

	//修改排序
	@Override
	public void changeSort(EduPictureInfoEntity eduPictureInfoEntity) {
		eduPictureDao.saveAndFlush(eduPictureInfoEntity);
	}
	
	//查询图片类型为轮播图
	@Override
	public List<EduPictureInfoEntity> findTypeOne() {
		return eduPictureDao.findTypeOne();
	}


	//查询图片类型为学习手册
	@Override
	public List<EduPictureInfoEntity> findTypeTwo() {
		return eduPictureDao.findTypeTwo();
	}

	//查询图片类型为考试
	@Override
	public List<EduPictureInfoEntity> findTypeThree() {
		return eduPictureDao.findTypeThree();
	}
}
