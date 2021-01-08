package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.GrainTradingPraiseEntity;

public interface GrainTradingPraiseDao extends JpaRepository<GrainTradingPraiseEntity, String> {
	@Query(value = "select * from sas_grain_trading_praise t where t.id=?1", nativeQuery = true)
	public GrainTradingPraiseEntity findInfoById(String id);

	@Query(value = "select * from sas_grain_trading_praise t where t.arg_id = ?1 and praise_user_id = ?2 limit 1", nativeQuery = true)
	public GrainTradingPraiseEntity findInfoByAll(String agrId, String userId);
}
