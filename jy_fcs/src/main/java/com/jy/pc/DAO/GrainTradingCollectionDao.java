package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.GrainTradingCollectionEntity;

public interface GrainTradingCollectionDao extends JpaRepository<GrainTradingCollectionEntity, String> {
	@Query(value = "select * from sas_grain_trading_collection t where t.id=?1", nativeQuery = true)
	public GrainTradingCollectionEntity findInfoById(String id);

	@Query(value = "select * from sas_grain_trading_collection t where t.arg_id = ?1 and collection_user_id = ?2 limit 1", nativeQuery = true)
	public GrainTradingCollectionEntity findInfoByAll(String agrId, String userId);
}
