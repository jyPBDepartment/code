package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.EduLessonInfoEntity;
import com.jy.pc.Entity.EduLessonStudentRelationEntity;

public interface EduLessonInfoService {
	// 搜索
	public Page<EduLessonInfoEntity> findListByParam(String name, String status, String createBy, Pageable pageable);

	// 添加
	public EduLessonInfoEntity save(EduLessonInfoEntity dbLogInfoEntity);

	// 修改
	public void update(EduLessonInfoEntity moduleInfo);

	// 删除
	public void delete(String id);

	// 主鍵查詢
	public EduLessonInfoEntity findInfobyId(String id);

	// 根据主键查询报名信息
	public List<EduLessonStudentRelationEntity> findRelaById(String id, String name);

	// 切换线下课程生效状态
	public void enable(EduLessonInfoEntity entity, boolean result);

	// 移动端 - 首页-线下课程加载
	public List<EduLessonInfoEntity> getListByReading();

	public List<EduLessonStudentRelationEntity> getLessonsByUserId(String userId);

	// 移动端 - 课程报名
	public void enrollLesson(EduLessonInfoEntity lesson, String userId, String userName, String useTel);
	
	//app线下课程列表加载
	public Page<EduLessonInfoEntity> findLessonList(String name, String createBy, Pageable pageable);
	
	//通过lessonId，userId查询报名情况
	public EduLessonStudentRelationEntity findByLessonId(String lessonId,String userId);
	
	//根据lessonId获取报名情况
	public List<EduLessonStudentRelationEntity> findByLesson(String lessonId);
	
	// 删除报名表
	public void deleteLesson(String id);

}
