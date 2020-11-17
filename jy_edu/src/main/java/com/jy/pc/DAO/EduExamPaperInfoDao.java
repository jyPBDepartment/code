package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.EduExamPaperInfoEntity;

/**
 * 试卷信息表Dao
 * */
public interface EduExamPaperInfoDao extends JpaRepository<EduExamPaperInfoEntity, String>{
	// 分页与模糊查询
	@Query(value = "select * from edu_exam_paper_info t where if(?1 !='',t.create_by like ?1,1=1) and if(?2 !='',t.status like ?2,1=1) and if(?3 !='',t.vocation_id like ?3,1=1) order by t.create_date desc",
			countQuery = "select count(*) from edu_exam_paper_info t  where if(?1 !='',t.create_by like ?1,1=1) and if(?2 !='',t.status like ?2,1=1) and if(?3 !='',t.vocation_id like ?3,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<EduExamPaperInfoEntity> findListByName(String createBy, String status,String vocationId, Pageable pageable);

	// 通过id查询
	@Query(value = "select * from edu_exam_paper_info t where t.id =:id", nativeQuery = true)
	public EduExamPaperInfoEntity findId(@Param("id") String id);
	
	//findById
	@Query(value = "select * from edu_exam_paper_info t where t.id =:id", nativeQuery = true)
	public EduExamPaperInfoEntity findExamId(@Param("id") String id);
	
	//试卷列表加载接口
	@Query(value = "SELECT * FROM edu_exam_paper_info t where t.status = '0' and t.vocation_id =:vocationId order by t.create_date desc", nativeQuery = true)
	public List<EduExamPaperInfoEntity> getExamListByVocationId(String vocationId);

}
