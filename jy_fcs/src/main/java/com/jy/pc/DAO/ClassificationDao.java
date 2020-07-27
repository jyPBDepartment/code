package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.ClassificationEntity;
import com.jy.pc.Entity.PowerInfoEntity;

public interface ClassificationDao extends JpaRepository<ClassificationEntity	,String>{
	//fingById方法
	@Query(value="select * from sas_classification_info  where id =:id",nativeQuery = true)
	public ClassificationEntity findBId(@Param("id")String id);
	
	//分页与模糊查询
	@Query(value="select * from sas_classification_info  t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.code like ?2,1=1) order by t.create_date desc",
			countQuery="select count(*) from sas_classification_info t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.code like ?2,1=1) order by t.create_date desc",
			nativeQuery = true)
	public Page<ClassificationEntity> findListByName(String name,String code,Pageable pageable);
	//查询分类编码
	@Query(value="select * from sas_classification_info t where t.parent_code ='' AND t.status ='1'",nativeQuery = true)
	public List<ClassificationEntity> findSubClassiList();
}
