package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.EduExamPaperInfoEntity;
import com.jy.pc.Entity.EduManualInfoEntity;
import com.jy.pc.Entity.EduQuestionInfoEntity;
import com.jy.pc.Entity.EduVocationInfoEntity;
/**
 * 职业类别Dao
 * */
public interface EduVocationInfoDao extends JpaRepository<EduVocationInfoEntity, String> {

	// 分页与模糊查询
	@Query(value = "select * from edu_vocation_info  t  where if(?1 !='',t.create_by like ?1,1=1) and if(?2 !='',t.status = ?2,1=1) order by t.create_date desc",
			countQuery = "select count(*) from edu_vocation_info t  where if(?1 !='',t.create_by like ?1,1=1) and if(?2 !='',t.status = ?2,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<EduVocationInfoEntity> findListByName(String createBy, String status, Pageable pageable);

	// 通过id查询
	@Query(value = "select * from edu_vocation_info t where t.id =:id", nativeQuery = true)
	public EduVocationInfoEntity findId(@Param("id") String id);

	// 查询排序
	@Query(value = "select * from edu_vocation_info ", nativeQuery = true)
	public List<EduVocationInfoEntity> findSort();
	
	//查询有效职业类别
	@Query(value = "select * from edu_vocation_info t where t.status = '0'", nativeQuery = true)
	public List<EduVocationInfoEntity> findVocationId();
	
	//查询有效并考试职业类别
	@Query(value = "select * from edu_vocation_info t where t.status = '0' and t.is_exam = '1'", nativeQuery = true)
	public List<EduVocationInfoEntity> findVocationIsExamId();
}
