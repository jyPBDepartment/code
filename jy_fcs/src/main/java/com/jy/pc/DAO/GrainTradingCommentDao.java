package com.jy.pc.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.GrainTradingCommentEntity;

public interface GrainTradingCommentDao extends JpaRepository<GrainTradingCommentEntity, String> {
	@Query(value = "select * from sas_grain_trading_comment t where t.id=?1", nativeQuery = true)
	public GrainTradingCommentEntity findInfoById(String id);

	@Query(value = "select * from sas_grain_trading_comment t where t.id=?1 and status != -1", nativeQuery = true)
	public GrainTradingCommentEntity findNormalInfoById(String id);

	@Query(value = "select t1.id,t1.comment_content as content,date_format(t1.comment_date,'%Y-%m-%d %H:%i:%s') as date,t1.comment_user_name as user,t1.status,t1.is_anonymous as isAnonymous,t2.name as title from sas_grain_trading_comment t1,sas_publication_info t2 where t1.aid=t2.id and t1.status != -1 and t2.name like ?1 and t1.comment_user_name like ?2 and t1.comment_content like ?3 order by t1.comment_date desc", 
			countQuery = "select count(0) from sas_grain_trading_comment t1,sas_publication_info t2 where t1.aid=t2.id and t1.status != -1 and t2.name like ?1 and t1.comment_user_name like ?2 and t1.comment_content like ?3 order by t1.comment_date desc", 
			nativeQuery = true)
	public Page<List<Map<String, Object>>> findPageByParam(String title, String name, String content,
			Pageable pageable);

	@Query(value = "select * from sas_grain_trading_comment t where t.aid=?1 and status != -1 order by comment_date desc limit 1", nativeQuery = true)
	public GrainTradingCommentEntity findNewestById(String aid);

	@Query(value = "select t1.id,t1.comment_content AS content,date_format( t1.comment_date, '%Y-%m-%d %H:%i:%s' ) AS date,t1.comment_user_name AS nickName,t1.status,t1.is_anonymous AS isAnonymous,t1.comment_pic,(select count(0) from sas_grain_trading_reply t2 where comment_id = t1.id and t2.status=0) as replyNum FROM sas_grain_trading_comment t1 where t1.status=0 and t1.aid = ?1 ORDER BY t1.comment_date DESC", 
			countQuery = "select count(0) FROM sas_grain_trading_comment t1 where t1.status=0 and t1.aid = ?1", 
			nativeQuery = true)
	public Page<List<Map<String, Object>>> findCommentPage(String aid,Pageable pageable);
}
