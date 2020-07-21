package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.QuestionEntity;

public interface QuestionDao extends JpaRepository<QuestionEntity, String>{
//	fingById方法
	@Query(value="select * from w_questionnaire  where id =:id",nativeQuery = true)
	public QuestionEntity findBId(@Param("id")String id);
	
	//分页与模糊查询
	@Query(value="select * from w_questionnaire t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.phone_num like ?2,1=1)order by t.create_date desc",
			countQuery="select count(*) from w_questionnaire t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.phone_num like ?2,1=1)order by t.create_date desc",
			nativeQuery = true)
	public Page<QuestionEntity> findListByName(String name,String phoneNum,Pageable pageable);
}
