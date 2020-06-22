package com.jy.pc.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.ArticleDao;
import com.jy.pc.Entity.ArticleEntity;
import com.jy.pc.Service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleDao articleDao;
	@Override
	public ArticleEntity save(ArticleEntity article) {
		return articleDao.saveAndFlush(article);
	}

	@Override
	public void update(ArticleEntity article) {
		articleDao.saveAndFlush(article);
	}

	@Override
	public void delete(String id) {
		articleDao.deleteById(id);
	}

	@Override
	public List<ArticleEntity> findAll() {
		return articleDao.findAll();
	}

	@Override
	public ArticleEntity findId(String id) {
		return articleDao.findId(id);
	}

	@Override
	public Page<ArticleEntity> findListByName(String name,Pageable pageable) {
		String jurName = "%"+name+"%";
		return articleDao.findListByName(jurName,pageable);
	}

	@Override
	public List<ArticleEntity> findTop() {
		return articleDao.findTop();
	}

	@Override
	public List<ArticleEntity> findIsRelease() {
		return articleDao.findIsRelease();
	}

	@Override
	public List<ArticleEntity> findIsRecommend() {
		return articleDao.findIsRecommend();
	}
}
