package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.KeyWordEntity;

public interface KeyWordDao extends JpaRepository<KeyWordEntity, String> {
	// 查询
	@Query(value = "select * from sas_key_word t where if(?1 !='',t.name like ?1,1=1) order by t.create_date desc", countQuery = "select count(*) from sas_key_word t where if(?1 !='',t.name like ?1,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<KeyWordEntity> findListByName(String name, Pageable pageable);

	// 通过id查询
	@Query(value = "select * from sas_key_word t where t.id =:id", nativeQuery = true)
	public KeyWordEntity findId(@Param("id") String id);

	// 通过分类编码查询对应关键词
	@Query(value = "select * from sas_key_word a where a.parent_code = (select c.id from sas_classification_info c where c.code = ?1)", nativeQuery = true)
	public List<KeyWordEntity> findListByClass(String classCode);

	// 通过看图识病id查询关键词
	@Query(value = "select * from sas_key_word a where a.id in (select key_id from sas_case_key t where t.case_id =:caseId)", nativeQuery = true)
	public List<KeyWordEntity> findKeyByCase(@Param("caseId") String caseId);
}
