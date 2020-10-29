package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.EduManualLabelInfoEntity;
/**
 * 标签Service
 * */
public interface EduManualLabelService {

	//分页与模糊查询
	public Page<EduManualLabelInfoEntity> findListByName(String createBy,String name, String status, Pageable pageable);
	
	//添加
	public EduManualLabelInfoEntity save(EduManualLabelInfoEntity eduManualLabelInfoEntity);
	
	//修改
	public void update(EduManualLabelInfoEntity eduManualLabelInfoEntity);
	
	//删除
	public void delete(String id);
	
	//通过id查询
	public EduManualLabelInfoEntity findId(String id);
	
	//调整状态
	void enable(EduManualLabelInfoEntity eduManualLabelInfoEntity,boolean result);
	
	// 查询启用标签
	public List<EduManualLabelInfoEntity> findManualLabelId();
	
	//查询未关联标签
	public List<EduManualLabelInfoEntity> findManualLink();
}
