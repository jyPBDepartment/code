package com.jy.pc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.ArticleManageDao;
import com.jy.pc.Entity.ArticleManageEntity;
import com.jy.pc.Service.ArticleManageService;
import com.jy.pc.Utils.DbLogUtil;

/**
 * 文章管理Service
 */
@Service
public class ArticleManageServiceImpl implements ArticleManageService {
	@Autowired
	private ArticleManageDao eduArticleManageDao;
	@Autowired
	DbLogUtil logger;

	// 分页与模糊查询（文章信息）
	@Override
	public Page<ArticleManageEntity> findListByName(String title, String sectionId, Pageable pageable) {
		String articleTitle = "%" + title + "%";
		String articleSectionId = "%" + sectionId + "%";
		return eduArticleManageDao.findListByName(articleTitle, articleSectionId, pageable);
	}

	// 根据id查询文章信息
	@Override
	public ArticleManageEntity findBId(String id) {

		return eduArticleManageDao.findBId(id);
	}

	// 添加文章信息
	@Override
	public ArticleManageEntity save(ArticleManageEntity eduArticleManageEntity) {
		logger.initAddLog(eduArticleManageEntity);
		return eduArticleManageDao.saveAndFlush(eduArticleManageEntity);
	}

	// 修改文章信息
	@Override
	public void update(ArticleManageEntity eduArticleManageEntity) {
		logger.initUpdateLog(eduArticleManageEntity);
		eduArticleManageDao.saveAndFlush(eduArticleManageEntity);
	}

	// 删除文章信息
	@Override
	public void delete(String id) {
		logger.initDeleteLog(eduArticleManageDao.findBId(id));
		eduArticleManageDao.deleteById(id);
	}

	// 文章信息是否启用
	@Override
	public void enable(ArticleManageEntity eduArticleManageEntity, boolean result) {
		logger.initEnableLog(eduArticleManageEntity, result);
		eduArticleManageDao.saveAndFlush(eduArticleManageEntity);
	}

	// 查询文章管理信息的最新3条有效记录
	@Override
	public List<ArticleManageEntity> findArticleInfo() {
		return eduArticleManageDao.findArticleInfo();
	}

	// 通过版块id查询文章管理信息
	@Override
	public List<ArticleManageEntity> findBySectionId(String id) {
		return eduArticleManageDao.findBySectionId(id);
	}

	// 条件查询文章管理信息列表（接口）
	@Override
	public Page<ArticleManageEntity> findListByChoose(String sectionId, Pageable pageable) {
		String articleLinkSectionId = "%" + sectionId + "%";
		return eduArticleManageDao.findListByChoose(articleLinkSectionId, pageable);
	}

}
