package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.EduVocationInfoEntity;

public interface EduVocationInfoDao extends JpaRepository<EduVocationInfoEntity, String> {

	// 分页与模糊查询
	@Query(value = "select * from edu_vocation_info  t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.status = ?2,1=1) order by t.create_date desc",
			countQuery = "select count(*) from edu_vocation_info t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.status = ?2,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<EduVocationInfoEntity> findListByName(String name, String status, Pageable pageable);

	// 通过id查询
	@Query(value = "select * from edu_vocation_info t where t.id =:id", nativeQuery = true)
	public EduVocationInfoEntity findId(@Param("id") String id);

	// 查询排序
	@Query(value = "select * from edu_vocation_info ", nativeQuery = true)
	public List<EduVocationInfoEntity> findSort();
}
