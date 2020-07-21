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
import com.jy.pc.Entity.ClassificationEntity;
import com.jy.pc.Service.ClassificationService;

@Controller
@RequestMapping(value="classification_info")
@ResponseBody
public class ClassificationController {
	@Autowired
	private ClassificationService classificationService;

	//分类添加前查询上级分类列表
	
	@RequestMapping(value = "/findSubList")
	public Map<String, Object> findSubList(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ClassificationEntity> classi = classificationService.findSubList();
		System.out.println(classi);
		if (classi != null) {
			map.put("status", "0");
			map.put("data", classi);
		} else {
			map.put("status", "1");
		}
		return map;
	}
	
	//分类添加
		@RequestMapping(value = "save")
		public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
			Map<String, String> map = new HashMap<String, String>();
			String s = res.getParameter("classificationEntity");
			JSONObject jsonObject = JSONObject.parseObject(s);
			
			Date date = new Date();
			ClassificationEntity classificationEntity = jsonObject.toJavaObject(ClassificationEntity.class);
			classificationEntity.setCreateDate(date);
			
			classificationService.save(classificationEntity);
		
			map.put("message", "添加成功");
			return map;
		}
		// 分类修改前查询
		@RequestMapping(value = "findById")
		public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
				@RequestParam(name = "id") String id) {
			Map<String, Object> map = new HashMap<String, Object>();
			ClassificationEntity classification = classificationService.findBId(id);
			if (classification != null) {
				map.put("state", "0");// 查询数据成功
				map.put("data", classification);
			} else {
				map.put("state", "1");// 查询数据失败
			}
			return map;
		}
		// 分类修改
		@RequestMapping(value = "update")
		public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {
			Map<String, String> map = new HashMap<String, String>();
			String s = res.getParameter("classificationEntity");
			JSONObject jsonObject = JSONObject.parseObject(s);
			Date date = new Date();
			ClassificationEntity classificationEntity = jsonObject.toJavaObject(ClassificationEntity.class);
			classificationEntity.setUpdateDate(date);
			classificationService.update(classificationEntity);
			map.put("message", "修改成功");
			return map;
		}
		// 分类删除
		@RequestMapping(value = "delete")
		public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
				@RequestParam(name = "id") String id) {
			Map<String, Object> map = new HashMap<String, Object>();// 接收数据容器
			classificationService.delete(id);
			map.put("state", "0");
			map.put("message", "删除成功");
			return map;
		}

		// 分类模糊查询与分页
		@RequestMapping(value = "findByName")
		public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
				@RequestParam(name = "name") String name, @RequestParam(name = "page") Integer page,
				@RequestParam(name = "size") Integer size) {
			Map<String, Object> map = new HashMap<String, Object>();
			Pageable pageable = new PageRequest(page - 1, size);
			Page<ClassificationEntity> classificationList = classificationService.findListByName(name, pageable);
			map.put("state", "0");// 成功
			map.put("message", "查询成功");
			map.put("data", classificationList);
			
			return map;
		}

		// 分类禁用
		@RequestMapping(value = "/enable")
		public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
				@RequestParam(name = "status") String status, @RequestParam(name = "id") String id) {
			Map<String, String> map = new HashMap<String, String>();
			ClassificationEntity classificationEntity = classificationService.findBId(id);
			classificationEntity.setStatus(status);
			classificationEntity.getStatus();
			if (status.equals("0")) {
				classificationEntity.setStatus("0");
				map.put("state", "0");
				map.put("message", "启用成功");
			} else if (status.equals("1")) {
				classificationEntity.setStatus("1");
				map.put("state", "0");
				map.put("message", "禁用成功");
			}
			classificationService.update(classificationEntity);
			return map;
		}
		//分类下拉列表查询
		@RequestMapping(value = "findAll")
		public Map<String,Object> findAll(HttpServletRequest res,HttpServletResponse req) {

			Map<String,Object> map = new HashMap<String,Object>();//接收数据容器
			List<ClassificationEntity> classificationList = classificationService.findAll();//查询所有数据方法
			map.put("status", "0");
			map.put("message", "查询成功");
			map.put("data", classificationList);
			return map;
		}
}
