package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.ArticleManageEntity;

/**
 * 文章管理Service
 */
public interface ArticleManageService {
	// 分页与模糊查询（文章信息）
	public Page<ArticleManageEntity> findListByName(String title, String sectionId, Pageable pageable);

	// 通过id查询（文章信息）
	public ArticleManageEntity findBId(String id);

	// 添加文章信息
	public ArticleManageEntity save(ArticleManageEntity eduArticleManageEntity);

	// 修改文章信息
	public void update(ArticleManageEntity eduArticleManageEntity);

	// 删除文章信息
	public void delete(String id);

	// 调整文章信息状态
	void enable(ArticleManageEntity eduArticleManageEntity, boolean result);
	
	// 查询文章管理信息的最新3条有效记录
	public List<ArticleManageEntity> findArticleInfo();
	
	// 通过版块id查询文章管理信息
	public List<ArticleManageEntity> findBySectionId(String id);
	
	// 条件查询文章管理信息列表（接口）
	public Page<ArticleManageEntity> findListByChoose(String sectionId, Pageable pageable);
}
