package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.EduOptionInfoEntity;

/**
 * 选项信息表Dao
 * */
public interface EduOptionInfoDao extends JpaRepository<EduOptionInfoEntity, String>{
	// 通过id查询
	@Query(value = "select * from edu_option_info t where t.id =:id", nativeQuery = true)
	public EduOptionInfoEntity findId(@Param("id") String id);
	
	// 通过试题id查询
	@Query(value = "select * from edu_option_info t where t.qu_id =:questionId", nativeQuery = true)
	public List<EduOptionInfoEntity> findquestionId(@Param("questionId") String questionId);

}
