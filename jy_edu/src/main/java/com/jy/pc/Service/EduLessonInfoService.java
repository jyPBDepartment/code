package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.EduLessonInfoEntity;
import com.jy.pc.Entity.EduLessonStudentRelationEntity;

public interface EduLessonInfoService {
	// 搜索
	public Page<EduLessonInfoEntity> findListByParam(String name, String status, Pageable pageable);

	// 添加
	public EduLessonInfoEntity save(EduLessonInfoEntity dbLogInfoEntity);

	// 修改
	public void update(EduLessonInfoEntity moduleInfo);

	// 删除
	public void delete(String id);

	// 主鍵查詢
	public EduLessonInfoEntity findInfobyId(String id);
	
	//根据主键查询报名信息
	public List<EduLessonStudentRelationEntity> findRelaById(String id);

	//切换线下课程生效状态
	public void enable(EduLessonInfoEntity entity, boolean result);

}
