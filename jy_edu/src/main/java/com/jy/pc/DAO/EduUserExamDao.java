package com.jy.pc.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.EduUserExamEntity;

public interface EduUserExamDao extends JpaRepository<EduUserExamEntity, String>{

	@Query(value="select t2.name as vocationName,t.exam_date as examDate,t1.name as examPaperName,t1.pass_score as passScore,t.score as score from  edu_user_exam t,edu_exam_paper_info t1,edu_vocation_info t2 where t.exam_id =t1.id \r\n" + 
			"and t1.vocation_id = t2.id and t.user_id=?1",nativeQuery=true)
	public List<Map<String,Object>> getExamResultByUserId(String userId);
	
}
