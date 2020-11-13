package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.EduCertificateInfoEntity;

public interface EduCertificateInfoDao extends JpaRepository<EduCertificateInfoEntity, String> {
	// GetById方法
	@Query(value = "select * from edu_certificate_info  where id =:id", nativeQuery = true)
	public EduCertificateInfoEntity GetById(@Param("id") String id);

	// 分页与模糊查询
	@Query(value = "select * from edu_certificate_info  t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.status = ?2,1=1) and if(?3 !='',t.create_by like ?3,1=1) order by t.create_date desc", countQuery = "select count(*) from edu_certificate_info t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.status = ?2,1=1) and if(?3 !='',t.create_by like ?3,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<EduCertificateInfoEntity> findListByName(String name, String status, String createBy,
			Pageable pageable);

	// 通过职业类别查询
	@Query(value = "select * from edu_certificate_info  where vocation_id = ?1", nativeQuery = true)
	public EduCertificateInfoEntity findInfobyVocation(String vocationId);
}
