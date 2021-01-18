package com.jy.pc.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.ArticleManageEntity;

/**
 * 文章管理Dao
 */
public interface ArticleManageDao extends JpaRepository<ArticleManageEntity, String> {

	// 分页与模糊查询文章管理信息
	@Query(value = "select * from sas_article_manage  t  where if(?1 !='',t.title like ?1,1=1)  and if(?2 !='',t.section_id like ?2,1=1)  order by t.create_date and t.update_date desc", countQuery = "select count(*) from sas_article_manage t  where if(?1 !='',t.title like ?1,1=1) and if(?2 !='',t.section_id like ?2,1=1) order by t.create_date and t.update_date desc", nativeQuery = true)
	public Page<ArticleManageEntity> findListByName(String title, String sectionId, Pageable pageable);

	// 通过id查询文章管理信息
	@Query(value = "select * from sas_article_manage t where t.id =:id", nativeQuery = true)
	public ArticleManageEntity findBId(@Param("id") String id);

	// 移动端-查询文章管理信息的最新3条有效记录(接口)
	@Query(value = "select * from sas_article_manage  t  where status='0' order by t.update_date desc limit 3", nativeQuery = true)
	public List<ArticleManageEntity> findArticleInfo();

	// 通过版块id查询文章管理信息l
	@Query(value = "select * from sas_article_manage t where t.section_id =:id", nativeQuery = true)
	public List<ArticleManageEntity> findBySectionId(String id);

	// 移动端-条件查询文章管理信息列表（接口）
	@Query(value = "select t.id,t.title,t.url,t.section_id as sectionId,t.collection_num,t.praise_num,t.view_num,date_format(t.create_date,'%Y-%m-%d %H:%i:%s') as date,(select t.id from sas_exclusive_collection t1 where t.id=t1.art_id and t1.collection_user_id=:userId) as is_collection , (select t.id from sas_exclusive_praise t2 where t.id=t2.art_id and t2.praise_user_id=:userId) as is_praise from sas_article_manage t where t.section_id = :sectionId order by case when :orderType='1' then view_num end desc,case when :orderType='2' then create_date end desc,case when :orderType='3' then is_selected end desc,case when :orderType='4' then comment_num end desc,case when :orderType='5' then praise_num end desc", 
			countQuery = "select count(0) from sas_article_manage t where t.section_id = :sectionId ", nativeQuery = true)
	public Page<List<Map<String,Object>>> findListByChoose(@Param("sectionId") String sectionId,@Param("userId") String userId,@Param("orderType")String orderType, Pageable pageable);

	// 通过id查询文章管理信息
	@Query(value = "select *,(select t1.id from sas_exclusive_collection t1 where t.id=t1.art_id and t1.collection_user_id=:userId) as is_collection,(select t2.id from sas_exclusive_praise t2 where t.id=t2.art_id and t2.praise_user_id=:userId) as is_praise from sas_article_manage t where t.id =:id", nativeQuery = true)
	public Map<String,Object> findInfoByUserId(@Param("id") String id,@Param("userId") String userId);
	
	@Query(value="select t.id,t.url,t.title,t.create_by as contactsUser,t.update_date as updateDate from sas_article_manage t where t.id in (select art_id from sas_exclusive_collection where collection_user_id = ?1) order by t.create_date desc",
			countQuery="select count(0) from sas_article_manage t where t.id in (select art_id from sas_exclusive_collection where collection_user_id = ?1)",nativeQuery = true)
	public Page<List<Map<String,Object>>> findMyCollection(String userId,Pageable pageable);
}
