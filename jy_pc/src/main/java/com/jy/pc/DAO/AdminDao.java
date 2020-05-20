package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.AdminEntity;

public interface AdminDao extends JpaRepository<AdminEntity, String>{
	
//	public void findAll();
	
	@Query(value="select * from admin t where t.admin_name =:loginName",nativeQuery = true)
	public AdminEntity findByName(@Param("loginName")String loginName);
	
	@Query(value="select * from admin t where t.admin_id =:id",nativeQuery = true)
	public AdminEntity findBId(@Param("id")String id);
	
	
//	if(?1 !='',x1=?1,1=1) and if(?2 !='',x2=?2,1=1)" +
//        "and if(?3 !='',x3=?3,1=1)
	
	@Query(value="select * from admin t where if(?1 !='',t.admin_name like ?1,1=1) and if(?2 !='',t.admin_phone like ?2,1=1) and if(?3 !='',t.admin_static=?3,1=1)",
			countQuery="select count(*) from admin t where if(?1 !='',t.admin_name like ?1,1=1) and if(?2 !='',t.admin_phone like ?2,1=1) and if(?3 !='',t.admin_static=?3,1=1)",
			nativeQuery = true)
	public Page<AdminEntity> findListByName(String adminName,String adminPhone,String adminStatic,Pageable pageable);
}
