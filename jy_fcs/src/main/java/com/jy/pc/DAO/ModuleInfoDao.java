package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.ModuleInfoEntity;

public interface ModuleInfoDao extends JpaRepository<ModuleInfoEntity,String>{
	@Query(value="select * from sas_module_info t where if(?1 !='',t.name like ?1,1=1) order by t.create_date desc",
			countQuery="select count(*) from sas_module_info t where if(?1 !='',t.name like ?1,1=1) order by t.create_date desc",nativeQuery = true)
	public Page<ModuleInfoEntity> findListByName(String name,Pageable pageable);
	@Query(value="select * from sas_module_info t where t.id =:id",nativeQuery = true)
	public ModuleInfoEntity findId(@Param("id")String id);

}
