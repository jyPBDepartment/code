package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.PowerInfoEntity;

public interface PowerInfoDao extends JpaRepository<PowerInfoEntity, String> {

	// fingById方法
	@Query(value = "select * from sas_power_info  where id =:id", nativeQuery = true)
	public PowerInfoEntity findBId(@Param("id") String id);

	// 分页与模糊查询
	@Query(value = "select * from sas_power_info  t  where if(?1 !='',t.jur_name like ?1,1=1) and if(?2 !='',t.jur_code like ?2,1=1) order by t.create_date desc", countQuery = "select count(*) from sas_power_info t  where if(?1 !='',t.jur_name like ?1,1=1) and if(?2 !='',t.jur_code like ?2,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<PowerInfoEntity> findListByName(String jurName, String jurCode, Pageable pageable);

	// 查询上级权限编码
	@Query(value = "select * from sas_power_info t where t.sub_jur_code ='' AND t.audit_status ='1'", nativeQuery = true)
	public List<PowerInfoEntity> findSubPowerList();

	// 账户关联方法
	@Query(value = "select * from sas_power_info t where t.audit_status ='1'", nativeQuery = true)
	public List<PowerInfoEntity> findCount();

	// 删除前查询
	@Query(value = "select distinct t.id,t.create_date,t.update_date,t.jur_name,t.jur_code,t.sub_jur_code,t.create_user,t.update_user,t.audit_status  from  sas_power_info t where t.id not in (select w.jur_codel from sas_account_power_info w)", nativeQuery = true)
	public List<PowerInfoEntity> findAccountLink();
}
