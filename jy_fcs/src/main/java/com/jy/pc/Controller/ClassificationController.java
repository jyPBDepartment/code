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
import com.jy.pc.Enum.ClassificationEnum;
import com.jy.pc.Service.ClassificationService;

@Controller
@ResponseBody
@RequestMapping(value = "classification")
public class ClassificationController {
	@Autowired
	private ClassificationService classificationService;

	// 分类添加
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

	// 下拉列表显示
	@RequestMapping(value = "findAll")
	public Map<String, Object> findAll(HttpServletRequest res, HttpServletResponse req) {

		Map<String, Object> map = new HashMap<String, Object>();// 接收数据容器
		List<ClassificationEntity> Classi = classificationService.findAll(); // 查询所有数据方法
		map.put("state", "0");
		map.put("message", "查询成功");
		map.put("data", Classi);
		return map;
	}

	// 分类修改
	@RequestMapping(value = "update")
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "parentCode") String parentCode) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("classificationEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		Date date = new Date();
		ClassificationEntity classificationEntity = jsonObject.toJavaObject(ClassificationEntity.class);
		classificationEntity.setUpdateDate(date);
		classificationService.update(classificationEntity);
		map.put("status", "0");
		map.put("message", "修改完成");
		return map;
	}

	// 分类删除
	@RequestMapping(value = "delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();// 接收数据容器
		int calssiFicati=classificationService.deleteClass(id);
		if(calssiFicati == 1) {
			map.put("status", "1");
			map.put("message", "该菜单下存在子菜单，不可直接删除！");
		}else if(calssiFicati ==2) {
			map.put("status", "0");
			map.put("message", "删除成功");
		}
		return map;
	}

	// 分类模糊查询与分页
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "code") String code, @RequestParam(name = "name") String name,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<ClassificationEntity> classiList = classificationService.findListByName(code, name, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", classiList);
		return map;
	}

	// 启用禁用
	@RequestMapping(value = "/enable")
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") String status, @RequestParam(name = "id") String id) {
		Map<String, String> map = new HashMap<String, String>();
		ClassificationEntity classificationEntity = classificationService.findBId(id);
		classificationEntity.setStatus(status);
		classificationEntity.getStatus();
		boolean result = true;
		if (status.equals("1")) {
			classificationEntity.setStatus("1");
			map.put("state", "0");
			map.put("message", "启用成功");
		} else if (status.equals("0")) {
			classificationEntity.setStatus("0");
			map.put("state", "0");
			map.put("message", "禁用成功");
			result = false;
		}
		classificationService.enable(classificationEntity,result);
		return map;
	}

	/**
	 * 查询上级分类编码列表
	 * 
	 */
	@RequestMapping(value = "/findSubClassiList")
	public Map<String, Object> findSubClassiList(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ClassificationEntity> classificat = classificationService.findSubClassiList();
		if (classificat != null) {
			map.put("state", "0");
			map.put("data", classificat);
		} else {
			map.put("state", "1");
		}
		return map;
	}

	/**
	 * 查询病虫害分类编码列表
	 * 
	 */
	@RequestMapping(value = "/findDipList")
	public Map<String, Object> findDipList(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ClassificationEntity> classificat = classificationService
				.findDipList(ClassificationEnum.DIP_CLASS.getCode());
		if (classificat != null) {
			map.put("state", "0");
			map.put("data", classificat);
		} else {
			map.put("state", "1");
		}
		return map;
	}

	/**
	 * 查询农作物种类分类编码列表
	 * 
	 */
	@RequestMapping(value = "/findCaseList")
	public Map<String, Object> findCaseList(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ClassificationEntity> classifi = classificationService
				.findCaseList(ClassificationEnum.CASE_CLASS.getCode());
		if (classifi != null) {
			map.put("state", "0");
			map.put("data", classifi);
		} else {
			map.put("state", "1");
		}
		return map;
	}

	/**
	 * 接口 -- 农作物分类
	 * 
	 */
	@RequestMapping(value = "/getCaseList")
	public Map<String, Object> getCaseList(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ClassificationEntity> classifi = classificationService
				.findCaseList(ClassificationEnum.CASE_CLASS.getCode());
		if (classifi != null) {
			map.put("state", "0");
			map.put("data", classifi);
		} else {
			map.put("state", "1");
		}
		return map;
	}
	
	/**
	 * 接口 -- 查询病虫害分类编码列表
	 * 
	 */
	@RequestMapping(value = "/getDipList")
	public Map<String, Object> getDipList(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ClassificationEntity> classificat = classificationService
				.findDipList(ClassificationEnum.DIP_CLASS.getCode());
		if (classificat != null) {
			map.put("state", "0");
			map.put("data", classificat);
		} else {
			map.put("state", "1");
		}
		return map;
	}
	
	// 查询子菜单
	@RequestMapping(value = "/findListById")
	public Map<String, Object> findListById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ClassificationEntity> classif = classificationService.findListById(id);
		map.put("status", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", classif);
		return map;
	}

	/**
	 * 查询关键词分类编码列表
	 * 
	 */
	@RequestMapping(value = "/findClassKey")
	public Map<String, Object> findClassKey(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ClassificationEntity> Classification = classificationService
				.findKeyWordList(ClassificationEnum.KEYWORD_CLASS.getCode());
		if (Classification != null) {
			map.put("state", "0");
			map.put("data", Classification);
		} else {
			map.put("state", "1");
		}
		return map;
	}
}
