package com.jy.pc.Service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.ArticleEntity;

public interface ArticleService {
	// 添加
	public ArticleEntity save(ArticleEntity article);

	// 修改
	public void update(ArticleEntity article);

	// 删除
	public void delete(String id);

	// 查询所有
	public List<ArticleEntity> findAll();

	// 按条件查询
	public ArticleEntity findId(String id);

	// 搜索
	public Page<ArticleEntity> findListByName(String name,Pageable pageable);
	
	//置顶
	public List<ArticleEntity> findTop();
	//发布
	public List<ArticleEntity> findIsRelease();
	//推荐
	public List<ArticleEntity> findIsRecommend();
	
	public ArticleEntity findOn(Date releaseDate);
}
