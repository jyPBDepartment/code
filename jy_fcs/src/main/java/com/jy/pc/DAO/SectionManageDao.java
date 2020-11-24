package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.SectionManageEntity;

/**
 * 版块管理DAO
 */
public interface SectionManageDao extends JpaRepository<SectionManageEntity, String>{

	// 分页与模糊查询
	@Query(value = "select * from sas_section_manage  t  where if(?1 !='',t.name like ?1,1=1)  order by t.create_date desc", countQuery = "select count(*) from sas_section_manage t  where if(?1 !='',t.name like ?1,1=1)  order by t.create_date desc", nativeQuery = true)
	public Page<SectionManageEntity> findListByName(String name, Pageable pageable);

	// 通过id查询
	@Query(value = "select * from sas_section_manage t where t.id =:id", nativeQuery = true)
	public SectionManageEntity findBId(@Param("id") String id);
	
	//动态获取版块管理下拉列表内容
	@Query(value = "select * from sas_section_manage", nativeQuery = true)
	public List<SectionManageEntity> findListName();

}
