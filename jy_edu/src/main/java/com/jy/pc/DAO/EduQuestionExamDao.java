package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.EduQuestionExamLinkEntity;
/**
 * 试卷试题关联表Dao
 * */
public interface EduQuestionExamDao extends JpaRepository<EduQuestionExamLinkEntity, String>{
	// 通过id查询
	@Query(value = "select * from edu_question_exam t where t.id =:id", nativeQuery = true)
	public EduQuestionExamLinkEntity findId(@Param("id") String id);
	
	// 通过试卷id查询
	@Query(value = "select * from edu_question_exam t where t.exam_id =:examId", nativeQuery = true)
	public List<EduQuestionExamLinkEntity> findExamId(String examId);
	// 通过试题id查询
	@Query(value = "select * from edu_question_exam t where t.question_id =:questionId", nativeQuery = true)
	public List<EduQuestionExamLinkEntity> findQuestionId(String questionId);

}
