package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.SalesEntity;

public interface SalesDao extends JpaRepository<SalesEntity, String>{
	@Query(value="select * from sys_sales where id =:id",nativeQuery = true)
	public SalesEntity findBId(@Param("id")String id);

	@Query(value="select * from sys_sales s where if(?1 !='',s.name like ?1,1=1) and if(?2 !='',s.phone like ?2,1=1)",nativeQuery = true)
	public List<SalesEntity> findListByName(String name,String phone);
}
