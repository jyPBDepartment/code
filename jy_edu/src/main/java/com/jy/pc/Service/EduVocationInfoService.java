package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.EduVocationInfoEntity;

public interface EduVocationInfoService {
	
	//分页与模糊查询
	public Page<EduVocationInfoEntity> findListByName(String createBy, String status, Pageable pageable);
	
	//添加
	public EduVocationInfoEntity save(EduVocationInfoEntity eduVocationInfoEntity);
	
	//修改
	public void update(EduVocationInfoEntity eduVocationInfoEntity);
	
	//删除
	public void delete(String id);
	
	//通过id查询
	public EduVocationInfoEntity findId(String id);
	
	//调整状态
	void enable(EduVocationInfoEntity eduVocationInfoEntity,boolean result);
	
	//查询排序
	public List<EduVocationInfoEntity> findSort();
	
	//修改排序
	public void changeSort(EduVocationInfoEntity eduVocationInfoEntity);
	
	//查询有效职业类别
	public List<EduVocationInfoEntity> findVocationId();
}
