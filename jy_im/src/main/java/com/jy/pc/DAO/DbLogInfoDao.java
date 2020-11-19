package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.DbLogInfoEntity;

public interface DbLogInfoDao extends JpaRepository<DbLogInfoEntity, String>{
	@Query(value="select * from sas_db_log_info t where if(?1 !='',t.module like ?1,1=1) "
			+ "and if(?2 !='',t.action like ?2,1=1) order by t.log_date desc ",
			countQuery="select count(*) from sas_db_log_info t where if(?1 !='',t.module like ?1,1=1) "
					+ "and if(?2 !='',t.action like ?2,1=1)order by t.log_date desc",nativeQuery = true)
	public Page<DbLogInfoEntity> findListByContent(String module,String action,Pageable pageable);
	
	@Query(value="select * from sas_db_log_info t where t.id =:id",nativeQuery = true)
	public DbLogInfoEntity findId(@Param("id")String id);

	@Query(value="delete from sas_db_log_info ",nativeQuery = true)
	@Modifying
	public void onceDeleteAll();
}
