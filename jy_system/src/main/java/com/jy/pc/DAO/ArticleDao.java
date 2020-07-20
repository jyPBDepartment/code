package com.jy.pc.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.ArticleEntity;

public interface ArticleDao extends JpaRepository<ArticleEntity, String>{
	@Query(value="select * from w_article t where t.name =:name",nativeQuery = true)
	public ArticleEntity findByName(@Param("name")String name);
	
	@Query(value="select * from w_article t where t.id =:id",nativeQuery = true)
	public ArticleEntity findId(@Param("id")String id);
	
	@Query(value="select * from w_article t where if(?1 !='',t.name like ?1,1=1) order by t.create_date desc ",
			countQuery="select count(*) from w_article t where if(?1 !='',t.name like ?1,1=1) order by t.create_date desc",nativeQuery = true)
	public Page<ArticleEntity> findListByName(String name,Pageable pageable);
	
	//置顶
	@Query(value="select * from w_article t where t.is_topping ='0'",nativeQuery = true)
	public List<ArticleEntity> findTop();
	//发布
	@Query(value="select * from w_article t where t.is_release ='0' order by t.release_date desc",nativeQuery = true)
	public List<ArticleEntity> findIsRelease();
	//推荐
	@Query(value="select * from w_article t where t.is_recommend ='0'",nativeQuery = true)
	public List<ArticleEntity> findIsRecommend();
	
	@Query(value="select * from w_article t where t.release_date>:releaseDate order by t.release_date asc limit 1",nativeQuery = true)
	public ArticleEntity findOn(@Param("releaseDate")Date releaseDate);
}
