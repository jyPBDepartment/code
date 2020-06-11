package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.MenuEntity;

public interface MenuDao extends JpaRepository<MenuEntity, String>{
	
	
	@Query(value="select * from w_menu t where if(?1 !='',t.menu_name like ?1,1=1) order by t.create_date desc ",
			countQuery="select count(*) from w_limit t where if(?1 !='',t.menu_name like ?1,1=1) and order by t.create_time desc",nativeQuery = true)
	public Page<MenuEntity> findListByName(String name,Pageable pageable);

}
