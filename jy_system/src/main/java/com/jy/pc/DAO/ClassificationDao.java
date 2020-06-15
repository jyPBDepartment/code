package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.jy.pc.Entity.ClassificationEntity;

public interface ClassificationDao extends JpaRepository<ClassificationEntity, String>{
//	fingById方法
	@Query(value="select * from w_classification_info  where id =:id",nativeQuery = true)
	public ClassificationEntity findBId(@Param("id")String id);
	
	//分页与模糊查询
	@Query(value="select * from w_classification_info t  where if(?1 !='',t.name like ?1,1=1)order by t.create_date desc",
			countQuery="select count(*) from w_classification_info t  where if(?1 !='',t.name like ?1,1=1)order by t.create_date desc",
			nativeQuery = true)
	public Page<ClassificationEntity> findListByName(String name,Pageable pageable);
//	//过滤重复字段
//	@Query (value="select name from w_classification_info group by name",nativeQuery = true)
//	public ClassificationEntity findByWord(@Param("name")String name);
}
