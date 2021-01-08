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

	@Query(value = "select t1.id,t1.comment_content as content,date_format(t1.comment_date,'%Y-%m-%d %H:%i:%s') as date,t1.comment_user_name as user,t1.status,t1.is_anonymous as isAnonymous,t2.name as title from sas_grain_trading_comment t1,sas_publication_info t2 where t1.aid=t2.id and t1.status != -1 and t2.name like ?1 and t1.comment_user_name like ?2 and t1.comment_content like ?3", 
			countQuery = "select count(0) from sas_grain_trading_comment t1,sas_publication_info t2 where t1.aid=t2.id and t1.status != -1 and t2.name like ?1 and t1.comment_user_name like ?2 and t1.comment_content like ?3", 
			nativeQuery = true)
	public Page<List<Map<String, Object>>> findPageByParam(String title, String name, String content,
			Pageable pageable);
}
