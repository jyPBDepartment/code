package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.DictTypeEntity;

public interface DictTypeDao extends JpaRepository<DictTypeEntity, String> {
	// fingById方法
	@Query(value = "select * from sas_dict_type  where id =:id", nativeQuery = true)
	public DictTypeEntity GetById(@Param("id") String id);

	// 分页与模糊查询
	@Query(value = "select * from sas_dict_type  t  where if(?1 !='',t.dict_name like ?1,1=1) and if(?2 !='',t.status like ?2,1=1) order by t.create_date desc", countQuery = "select count(*) from sas_dict_type t  where if(?1 !='',t.dict_name like ?1,1=1) and if(?2 !='',t.status = ?2,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<DictTypeEntity> findListByName(String name, String status, Pageable pageable);
}
