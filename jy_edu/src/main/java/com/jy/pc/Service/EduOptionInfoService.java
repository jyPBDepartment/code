package com.jy.pc.Service;

import java.util.List;

import com.jy.pc.Entity.EduOptionInfoEntity;
/**
 * 选项信息表Service
 * */
public interface EduOptionInfoService {
	// 通过id查询
	public EduOptionInfoEntity findId(String id);
	
	//添加
	public EduOptionInfoEntity save(EduOptionInfoEntity eduOptionInfoEntity);
	
	//修改
	public void update(EduOptionInfoEntity eduOptionInfoEntity);
	
	//删除
	public void delete(String id);
	
	//通过试题id查询
	public List<EduOptionInfoEntity> findquestionId(String questionId);
		
}
