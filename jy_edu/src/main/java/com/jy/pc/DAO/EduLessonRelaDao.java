package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.EduLessonStudentRelationEntity;

public interface EduLessonRelaDao extends JpaRepository<EduLessonStudentRelationEntity, String> {
	// GetById方法
	@Query(value = "select * from edu_lesson_info  where id =:id", nativeQuery = true)
	public EduLessonStudentRelationEntity GetById(@Param("id") String id);
	//获取报名情况
	@Query(value = "select * from edu_lesson_student_relation  where lesson_id =?1 and if(?2 !='',user_name like ?2,1=1)", nativeQuery = true)
	public List<EduLessonStudentRelationEntity> findRelaById(String id,String name);
	
	//根据userid获取报名情况
	@Query(value = "select * from edu_lesson_student_relation  where user_code =?1 ", nativeQuery = true)
	public List<EduLessonStudentRelationEntity> getLessonsByUserId(String userId);
	
	//通过lessonId，userId查询
	@Query(value = "select * from edu_lesson_student_relation t where t.lesson_id =:lessonId and t.user_code = :userId", nativeQuery = true)
	public EduLessonStudentRelationEntity findByLessonId(String lessonId,String userId);
	
	//根据lessonId获取报名情况
	@Query(value = "select * from edu_lesson_student_relation t where t.lesson_id =:lessonId ", nativeQuery = true)
	public List<EduLessonStudentRelationEntity> findByLesson(String lessonId);
}
