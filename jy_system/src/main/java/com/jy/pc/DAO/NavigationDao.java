package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.NavigationEntity;

public interface NavigationDao extends JpaRepository<NavigationEntity,String>{
	
	//	fingById方法
	@Query(value="select * from w_nav_info  where id =:id",nativeQuery = true)
	public NavigationEntity findBId(@Param("id")String id);
	
	//分页与模糊查询
	@Query(value="select * from w_nav_info t  where if(?1 !='',t.name like ?1,1=1)order by t.create_date_time desc",
			countQuery="select count(*) from w_nav_info t  where if(?1 !='',t.name like ?1,1=1)order by t.create_date_time desc",
			nativeQuery = true)
	public Page<NavigationEntity> findListByName(String name,Pageable pageable);

	
	
}
