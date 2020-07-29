package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.MenuEntity;

public interface MenuDao extends JpaRepository<MenuEntity,String>{
	@Query(value="select * from sas_menu t where t.id =:id",nativeQuery = true)
	public MenuEntity findId(@Param("id")String id);
	@Query(value="select * from sas_menu t where if(?1 !='',t.name like ?1,1=1) order by t.add_date desc",
			countQuery="select count(*) from sas_menu t where if(?1 !='',t.name like ?1,1=1) order by t.add_date desc",nativeQuery = true)
	public Page<MenuEntity> findListByName(String name,Pageable pageable);
}
