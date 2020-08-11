package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.AgriculturalEntity;

public interface AgriculturalDao extends JpaRepository<AgriculturalEntity, String> {

	// Echart图关联
	@Query(value = "SELECT * FROM sas_agricultural_clothing_info e where e.status = '1'", nativeQuery = true)
	public List<AgriculturalEntity> findAgrSum();

	// fingById方法
	@Query(value = "select * from sas_agricultural_clothing_info where id =:id", nativeQuery = true)
	public AgriculturalEntity findBId(@Param("id") String id);

	// 分页与模糊查询
	@Query(value = "select * from sas_agricultural_clothing_info  t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.status like ?2,1=1) order by t.create_date desc", countQuery = "select count(*) from sas_agricultural_clothing_info t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.status like ?2,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<AgriculturalEntity> findListByName(String name, String status, Pageable pageable);

	// 查询农服标题名称
	@Query(value = "select * from sas_agricultural_clothing_info  t  where if(?1 !='',t.name like ?1,1=1)order by t.create_date desc", nativeQuery = true)
	public List<AgriculturalEntity> findListByAgrName(String name);

	// 查询农服标题名称
	@Query(value = " select * from sas_agricultural_clothing_info order by create_date  desc limit 0,3", nativeQuery = true)
	public List<AgriculturalEntity> findListByTime();
}
