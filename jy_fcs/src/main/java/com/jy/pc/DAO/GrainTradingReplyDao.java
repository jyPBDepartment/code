package com.jy.pc.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Query(value = "select t3.id,t3.reply_content as replyContent,t1.comment_content as commentContent,date_format(t3.reply_date,'%Y-%m-%d %H:%i:%s') as date,t3.reply_user_name as user,t3.status,t3.is_anonymous as isAnonymous,t2.name as title from sas_grain_trading_comment t1,sas_publication_info t2,sas_grain_trading_reply t3 where t1.aid=t2.id and t1.id = t3.comment_id and t3.status != -1 and t2.name like ?1 and t3.reply_user_name like ?2 and t3.reply_content like ?3 and t3.comment_id = ?4 order by t3.reply_date desc", 
			countQuery = "select count(0) from sas_grain_trading_comment t1,sas_publication_info t2,sas_grain_trading_reply t3 where t1.aid=t2.id and t1.id = t3.comment_id and t3.status != -1 and t2.name like ?1 and t3.reply_user_name like ?2 and t3.reply_content like ?3 and t3.comment_id = ?4 order by t3.reply_date desc", 
			nativeQuery = true)
	public Page<List<Map<String, Object>>> findPageByParam(String title, String name, String content,String cid,
			Pageable pageable);

	@Query(value = "select * from sas_grain_trading_reply t where t.comment_id=?1 and status =0 order by reply_date desc", 
			countQuery = "select count(0) from sas_grain_trading_reply t where t.comment_id=?1 and status =0", 
			nativeQuery = true)
	public Page<GrainTradingReplyEntity> findCommentPage(String cid, Pageable pageable);
}
