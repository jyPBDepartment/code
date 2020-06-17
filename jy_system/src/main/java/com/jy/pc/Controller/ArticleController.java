package com.jy.pc.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jy.pc.Entity.ArticleEntity;
import com.jy.pc.Entity.ClassificationEntity;
import com.jy.pc.Service.ArticleService;
import com.jy.pc.Service.ClassificationService;

@Controller
@RequestMapping(value = "/article")
@ResponseBody
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ClassificationService classificationService;

	// 文章添加
	@RequestMapping(value = "/add")
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("articleEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		ArticleEntity articleEntity = jsonObject.toJavaObject(ArticleEntity.class);
		Date date = new Date();
		articleEntity.setCreateDate(date);
		articleEntity.setStatus("0");
		articleEntity.setIsRelease(1);
		articleEntity.setIsRecommend(1);
		articleEntity.setIsTopping(1);
		
		ClassificationEntity classificationEntity = new ClassificationEntity();
		classificationEntity=classificationService.findBId(articleEntity.getClassificationId());
		articleEntity.setClassificationName(classificationEntity.getName());
		articleService.save(articleEntity);
		map.put("message", "添加成功");
		return map;
	}

	// 文章修改
	@RequestMapping(value = "/update")
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("articleEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		ArticleEntity articleEntity = jsonObject.toJavaObject(ArticleEntity.class);
		Date date = new Date();
		articleEntity.setUpdateDate(date);
		ClassificationEntity classificationEntity = new ClassificationEntity();
		classificationEntity=classificationService.findBId(articleEntity.getClassificationId());
		articleEntity.setClassificationName(classificationEntity.getName());
		articleService.update(articleEntity);
		map.put("message", "修改成功");
		return map;
	}

	// 文章删除
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();	
		articleService.delete(id);
		map.put("state", "0");
		map.put("message", "删除成功");
		return map;
	}

	// 查询所有
	@RequestMapping(value = "/findAll")
	public Map<String, Object> findAll(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String,Object>();
		List<ArticleEntity> articleList = articleService.findAll();
		map.put("state", "0");
		map.put("message", "查询成功");
		map.put("data", articleList);
		return map;
	}

	// 条件查询
	@RequestMapping(value = "/findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		ArticleEntity articleEntity = articleService.findId(id);
		if (articleEntity != null) {
			map.put("state", "0");
			map.put("data", articleEntity);
		} else {
			map.put("state", "1");
		}
		return map;
	}

	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<ArticleEntity> articleList = articleService.findListByName(name,pageable);
		map.put("state", "0");
		map.put("message", "查询成功");
		map.put("data", articleList);
		return map;
	}

	// 启用
	@RequestMapping(value = "/enable")
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") String status, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		ArticleEntity articleEntity = articleService.findId(id);
		articleEntity.setStatus(status);
		articleEntity.getStatus();
		if (status.equals("0")) {
			articleEntity.setStatus("0");
			map.put("state", "0");
			map.put("message", "禁用成功");
		} else if (status.equals("1")) {
			articleEntity.setStatus("1");
			map.put("state", "0");
			map.put("message", "启用成功");
		}
		articleService.update(articleEntity);
		return map;
	}
	
	//发布
	@RequestMapping(value = "/release")
	public Map<String, String> release(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "isRelease") Integer isRelease, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		ArticleEntity articleEntity = articleService.findId(id);
		articleEntity.setIsRelease(isRelease);
		articleEntity.getIsRelease();
		if (isRelease.equals(0)) {
			articleEntity.setIsRelease(1);
			map.put("state", "0");
			map.put("message", "取消发布成功");
		} else if (isRelease.equals(1)) {
			articleEntity.setIsRelease(0);
			Date date = new Date();
			articleEntity.setReleaseDate(date);
			map.put("state", "0");
			map.put("message", "发布成功");
		}
		articleService.update(articleEntity);
		return map;
	}
	
	//推荐
	@RequestMapping(value = "/recommend")
	public Map<String, String> recommend(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "isRecommend") Integer isRecommend, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		ArticleEntity articleEntity = articleService.findId(id);
		articleEntity.setIsRecommend(isRecommend);
		articleEntity.getIsRecommend();
		if (isRecommend.equals(0)) {
			articleEntity.setIsRecommend(1);
			map.put("state", "0");
			map.put("message", "取消推荐成功");
		} else if (isRecommend.equals(1)) {
			articleEntity.setIsRecommend(0);
			map.put("state", "0");
			map.put("message", "推荐成功");
		}
		articleService.update(articleEntity);
		return map;
	}
	
	//置顶
	@RequestMapping(value = "/topping")
	public Map<String, String> topping(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "isTopping") Integer isTopping, @RequestParam(name = "id") String id) {
		Map<String, String> map = new HashMap<String, String>();
		ArticleEntity articleEntity = articleService.findId(id);
		articleEntity.setIsTopping(isTopping);
		articleEntity.getIsTopping();
		List<ArticleEntity> areicle=articleService.findTop();
		if (isTopping.equals(0)) {
			articleEntity.setIsTopping(1);
			map.put("state", "1");
			map.put("message", "取消置顶成功");
		}
		else if(areicle.size()<1) {
			if (isTopping.equals(1)) {
				articleEntity.setIsTopping(0);
				map.put("state", "0");
				map.put("message", "置顶成功");
			}
		}else {
			map.put("message", "置顶失败，只能置顶一条数据!");
		}
		articleService.update(articleEntity);
		return map;
	}
}
