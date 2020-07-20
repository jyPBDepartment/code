package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.LimitEntity;

public interface LimitDao extends JpaRepository<LimitEntity, String>{
	//通过name查询
	@Query(value="select * from w_limit t where t.name =:name",nativeQuery = true)
	public LimitEntity findByName(@Param("name")String name);
	//通过id查询
	@Query(value="select * from w_limit t where t.id =:id",nativeQuery = true)
	public LimitEntity findId(@Param("id")String id);
	//查询排序
	@Query(value="select * from w_limit t where if(?1 !='',t.name like ?1,1=1) order by t.create_time desc ",
			countQuery="select count(*) from w_limit t where if(?1 !='',t.name like ?1,1=1) and order by t.create_time desc",nativeQuery = true)
	public Page<LimitEntity> findListByName(String name,Pageable pageable);
	//查找状态为0数据
	@Query(value="select * from w_limit t where t.state ='0'",nativeQuery = true)
	public List<LimitEntity> findAl();
	//查找两个表中无关联数据
	@Query(value="select distinct t.id,t.create_time,t.edit_time,t.name,t.path,t.state,t.type from  w_limit t where t.id not in (select w.limit_id from w_role w)",nativeQuery = true)
	public List<LimitEntity> findLimitId();
}
