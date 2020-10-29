package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.EduPictureInfoEntity;
/**
 * 图片设置Controller
 * */
public interface EduPictureService {
	//分页与模糊查询
	public Page<EduPictureInfoEntity> findListByName(String createBy,String picType, String status, Pageable pageable);
	
	//添加
	public EduPictureInfoEntity save(EduPictureInfoEntity eduPictureInfoEntity);
	
	//修改
	public void update(EduPictureInfoEntity eduPictureInfoEntity);
	
	//删除
	public void delete(String id);
	
	//通过id查询
	public EduPictureInfoEntity findId(String id);
	
	//调整状态
	void enable(EduPictureInfoEntity eduPictureInfoEntity,boolean result);
	
	//查询排序
	public List<EduPictureInfoEntity> findSort();
	
	//修改排序
	public void changeSort(EduPictureInfoEntity eduPictureInfoEntity);
	
	//查询图片类型为轮播图
	public List<EduPictureInfoEntity> findTypeOne();
	
	//查询图片类型为学习手册
	public List<EduPictureInfoEntity> findTypeTwo();
	
	//查询图片类型为考试
	public List<EduPictureInfoEntity> findTypeThree();

}
