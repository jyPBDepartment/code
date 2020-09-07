package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.DictDataEntity;

public interface DictDataDao extends JpaRepository<DictDataEntity, String> {
	// fingById方法
	@Query(value = "select * from sas_dict_data  where id =:id", nativeQuery = true)
	public DictDataEntity GetById(@Param("id") String id);

	// fingByTypeId方法 根据字典类型查询所有键值
	@Query(value = "select * from sas_dict_data  where dict_type =:type", nativeQuery = true)
	public List<DictDataEntity> fingByTypeId(@Param("type") String type);
	
	//根据type清空所有键值
	@Query(value = "delete from sas_dict_data  where dict_type =:type", nativeQuery = true)
	@Modifying
	public void removeData(@Param("type") String type);
	
}
