package com.jy.pc.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.EduUserExamEntity;

public interface EduUserExamDao extends JpaRepository<EduUserExamEntity, String> {

	@Query(value = "select t2.name as vocationName,t.exam_date as examDate,t1.name as examPaperName,t1.pass_score as passScore,t.score as score from  edu_user_exam t,edu_exam_paper_info t1,edu_vocation_info t2 where t.exam_id =t1.id \r\n"
			+ "and t1.vocation_id = t2.id and t.user_id=?1", nativeQuery = true)
	public List<Map<String, Object>> getExamResultByUserId(String userId);

	@Modifying
	@Query(value = "delete from edu_user_exam where user_id = ?1 and exam_id = ?2", nativeQuery = true)
	public void deleteByExam(String userId, String examId);

	@Query(value = "select * from edu_user_exam where user_id = ?1 and exam_id = ?2", nativeQuery = true)
	public EduUserExamEntity findByExam(String userId, String examId);

	@Query(value = "select * from edu_user_exam a,edu_exam_paper_info b where a.exam_id = b.id and b.vocation_id = ?1 and if(?2!='',is_pass = ?2,1=1)", nativeQuery = true)
	public List<EduUserExamEntity> findByVocation(String vocationId, String isPass);
	
	//根据用户Id获取用户考试列表信息
	@Query(value = "select * from edu_user_exam where user_id = ?1", nativeQuery = true)
	public List<EduUserExamEntity> findUserExamByUserId(String userId);
	
	@Query(value = "select * from edu_user_exam where user_id = ?1 and vocation_id=?2", nativeQuery = true)
	public List<EduUserExamEntity> findUserExam(String userId,String vocationId);

}
