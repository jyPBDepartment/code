package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.ExclusiveCollectionEntity;

public interface ExclusiveCollectionDao extends JpaRepository<ExclusiveCollectionEntity, String> {
	@Query(value = "select * from sas_exclusive_collection t where t.id=?1", nativeQuery = true)
	public ExclusiveCollectionEntity findInfoById(String id);

	@Query(value = "select * from sas_exclusive_collection t where t.art_id = ?1 and collection_user_id = ?2 limit 1", nativeQuery = true)
	public ExclusiveCollectionEntity findInfoByAll(String agrId, String userId);
}
