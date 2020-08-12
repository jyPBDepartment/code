package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.AgriculturalEntity;
import com.jy.pc.Entity.FarmworkEntity;

public interface AgriculturalDao extends JpaRepository<AgriculturalEntity, String> {

	// Echart图关联
	@Query(value = "SELECT * FROM sas_agricultural_clothing_info e where e.status = '1'", nativeQuery = true)
	public List<AgriculturalEntity> findAgrSum();

	// fingById方法
	@Query(value = "select * from sas_agricultural_clothing_info where id =:id", nativeQuery = true)
	public AgriculturalEntity findBId(@Param("id") String id);

	// 分页与模糊查询
	@Query(value = "select * from sas_agricultural_clothing_info  t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.status like ?2,1=1) order by t.create_date desc", countQuery = "select count(*) from sas_agricultural_clothing_info t  where if(?1 !='',t.name like ?1,1=1) and if(?2 !='',t.status like ?2,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<AgriculturalEntity> findListByName(String name, String status, Pageable pageable);

	// 查询农服标题名称
	@Query(value = "select * from sas_agricultural_clothing_info  t  where if(?1 !='',t.name like ?1,1=1)order by t.create_date desc", nativeQuery = true)
	public List<AgriculturalEntity> findListByAgrName(String name);

	// 查询农服标题名称
	@Query(value = " select * from sas_agricultural_clothing_info order by create_date  desc limit 0,3", nativeQuery = true)
	public List<AgriculturalEntity> findListByTime();

	// 关键字搜索病虫害信息
	@Query(value = " select t.* from (sas_agricultural_clothing_info t inner join sas_case_key d on t.id=d.case_id inner join  sas_key_word k on d.key_id=k.id) where k.name like :name order by t.create_date desc", nativeQuery = true)
	public List<AgriculturalEntity> findCaseInfoByKey(@Param("name") String name);

	// 不同状态加载不同的发布
	@Query(value = "SELECT * FROM sas_agricultural_clothing_info t where t.status = :status", nativeQuery = true)
	public List<AgriculturalEntity> findStatusPass(@Param("status") String status);

	// 获取农服预约信息
	@Query(value = "SELECT * FROM sas_agricultural_clothing_info t where t.status = '0'", nativeQuery = true)
	public List<AgriculturalEntity> findAppointment();

}
