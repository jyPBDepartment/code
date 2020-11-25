package com.jy.pc.DAO;

import java.util.List;

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
	@Query(value = "select * from sas_article_manage  t  where if(?1 !='',t.title like ?1,1=1)  and if(?2 !='',t.section_id like ?2,1=1)  order by t.create_date desc", countQuery = "select count(*) from sas_article_manage t  where if(?1 !='',t.title like ?1,1=1) and if(?2 !='',t.section_id like ?2,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<ArticleManageEntity> findListByName(String title, String sectionId, Pageable pageable);

	// 通过id查询文章管理信息
	@Query(value = "select * from sas_article_manage t where t.id =:id", nativeQuery = true)
	public ArticleManageEntity findBId(@Param("id") String id);

	// 查询文章管理信息的最新3条有效记录(接口)
	@Query(value = "select * from sas_article_manage  t  where status='0' order by t.create_date desc limit 3", nativeQuery = true)
	public List<ArticleManageEntity> findArticleInfo();

	// 通过版块id查询文章管理信息
	@Query(value = "select * from sas_article_manage t where t.section_id =:id", nativeQuery = true)
	public List<ArticleManageEntity> findBySectionId(String id);

	// 条件查询文章管理信息列表（接口）
	@Query(value = "select * from sas_article_manage  t  where if(?1 !='',t.section_id like ?1,1=1) order by t.create_date desc", countQuery = "select count(*) from sas_article_manage t  where if(?1 !='',t.section_id like ?1,1=1)  order by t.create_date desc", nativeQuery = true)
	public Page<ArticleManageEntity> findListByChoose(String sectionId, Pageable pageable);

}
