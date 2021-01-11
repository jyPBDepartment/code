package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.ExclusivePraiseEntity;

public interface ExclusivePraiseDao extends JpaRepository<ExclusivePraiseEntity, String> {
	@Query(value = "select * from sas_exclusive_praise t where t.id=?1", nativeQuery = true)
	public ExclusivePraiseEntity findInfoById(String id);

	@Query(value = "select * from sas_exclusive_praise t where t.art_id = ?1 and praise_user_id = ?2 limit 1", nativeQuery = true)
	public ExclusivePraiseEntity findInfoByAll(String artId, String userId);
}
