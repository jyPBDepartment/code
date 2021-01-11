package com.jy.pc.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jy.pc.Entity.ExclusiveCommentEntity;

public interface ExclusiveCommentDao extends JpaRepository<ExclusiveCommentEntity, String>{
	
	@Query(value = "select * from sas_exclusive_comment t where t.id=?1", nativeQuery = true)
	public ExclusiveCommentEntity findInfoById(String id);

	@Query(value = "select * from sas_exclusive_comment t where t.id=?1 and status != -1", nativeQuery = true)
	public ExclusiveCommentEntity findNormalInfoById(String id);

	@Query(value = "select t1.id,t1.comment_content as content,date_format(t1.comment_date,'%Y-%m-%d %H:%i:%s') as date,t1.comment_user_name as user,t1.status,t1.is_anonymous as isAnonymous,t2.title as title from sas_exclusive_comment t1,sas_article_manage t2 where t1.art_id=t2.id and t1.status != -1 and t2.title like ?1 and t1.comment_user_name like ?2 and t1.comment_content like ?3", 
			countQuery = "select count(0) from sas_exclusive_comment t1,sas_article_manage t2 where t1.art_id=t2.id and t1.status != -1 and t2.title like ?1 and t1.comment_user_name like ?2 and t1.comment_content like ?3", 
			nativeQuery = true)
	public Page<List<Map<String, Object>>> findPageByParam(String title, String name, String content,
			Pageable pageable);
}
