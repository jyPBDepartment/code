package com.jy.pc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.GrainTradingReplyEntity;

public interface GrainTradingReplyDao extends JpaRepository<GrainTradingReplyEntity, String> {
	@Query(value = "select * from sas_grain_trading_reply t where t.id=?1", nativeQuery = true)
	public GrainTradingReplyEntity findInfoById(String id);

	@Query(value = "select * from sas_grain_trading_reply t where t.id=?1 and status != -1", nativeQuery = true)
	public GrainTradingReplyEntity findNormalInfoById(String id);

	@Query(value = "update sas_grain_trading_reply t set t.status = -1 where t.comment_id = ?1 ", nativeQuery = true)
	@Modifying
	public void logicalDelete(String cid);
}
