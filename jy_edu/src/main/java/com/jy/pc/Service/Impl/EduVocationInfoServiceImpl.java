package com.jy.pc.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.EduVocationInfoDao;
import com.jy.pc.Entity.EduVocationInfoEntity;
import com.jy.pc.Service.EduVocationInfoService;
/**
 * 职业类别ServiceImpl
 * */
@Service
public class EduVocationInfoServiceImpl implements EduVocationInfoService{
	@Autowired
	EduVocationInfoDao eduVocationInfoDao;

	//分页 条件查询
	@Override
	public Page<EduVocationInfoEntity> findListByName(String createBy, String status, Pageable pageable) {
		String createName="%"+ createBy +"%";
		return eduVocationInfoDao.findListByName(createName, status, pageable);
	}

	//添加
	@Override
	public EduVocationInfoEntity save(EduVocationInfoEntity eduVocationInfoEntity) {
		return eduVocationInfoDao.saveAndFlush(eduVocationInfoEntity);
	}

	//修改
	@Override
	public void update(EduVocationInfoEntity eduVocationInfoEntity) {
		eduVocationInfoDao.saveAndFlush(eduVocationInfoEntity);
	}

	//删除
	@Override
	public void delete(String id) {
		eduVocationInfoDao.deleteById(id);
	}

	//通过id查询
	@Override
	public EduVocationInfoEntity findId(String id) {
		return eduVocationInfoDao.findId(id);
	}

	//调整状态
	@Override
	@Transactional
	public void enable(EduVocationInfoEntity eduVocationInfoEntity, boolean result) {
		eduVocationInfoDao.saveAndFlush(eduVocationInfoEntity);
	}

	//修改排序
	@Override
	public void changeSort(EduVocationInfoEntity eduVocationInfoEntity) {	
		eduVocationInfoDao.saveAndFlush(eduVocationInfoEntity);
	}

	//查询排序
	@Override
	public List<EduVocationInfoEntity> findSort() {
		return eduVocationInfoDao.findSort();
	}

	//查询有效职业类别
	@Override
	public List<EduVocationInfoEntity> findVocationId() {
		return eduVocationInfoDao.findVocationId();
	}

	//查询未关联职业类别
	@Override
	public List<EduVocationInfoEntity> findVocationLink() {
		return eduVocationInfoDao.findVocationLink();
	}
}
