package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.GrainAreaInfoEntity;

public interface GrainAreaInfoDao extends JpaRepository<GrainAreaInfoEntity, String> {
	@Query(value = "select * from sas_grain_area_info t where t.id=?1", nativeQuery = true)
	public GrainAreaInfoEntity findInfoById(String id);

	@Query(value = "select * from sas_grain_area_info t where t.parent_id=?1", nativeQuery = true)
	public List<GrainAreaInfoEntity> findInfoByParentId(String id);

	@Query(value = "select * from sas_grain_area_info t where t.name=?1 and if(?2 != '' , t.parent_id=?2,1=1)", nativeQuery = true)
	public GrainAreaInfoEntity findInfoByName(String name, String parentId);

	@Query(value = "select * from sas_grain_area_info t where t.level = 1", nativeQuery = true)
	public List<GrainAreaInfoEntity> getProvince();

}
