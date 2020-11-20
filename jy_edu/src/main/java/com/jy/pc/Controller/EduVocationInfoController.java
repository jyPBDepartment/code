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
import com.jy.pc.Entity.EduExamPaperInfoEntity;
import com.jy.pc.Entity.EduManualInfoEntity;
import com.jy.pc.Entity.EduQuestionInfoEntity;
import com.jy.pc.Entity.EduVocationInfoEntity;
import com.jy.pc.Service.EduExamPaperInfoService;
import com.jy.pc.Service.EduManualInfoService;
import com.jy.pc.Service.EduQuestionInfoService;
import com.jy.pc.Service.EduVocationInfoService;

/**
 * 职业类别 Controller
 */
@Controller
@RequestMapping(value = "/vocationInfo")
public class EduVocationInfoController {
	@Autowired
	private EduVocationInfoService eduVocationInfoService;
	@Autowired
	private EduQuestionInfoService eduQuestionInfoService;
	@Autowired
	private EduManualInfoService eduManualInfoService;
	@Autowired
	private EduExamPaperInfoService eduExamPaperInfoService;

	// 切换是否需要考试
	@RequestMapping(value = "/examEnable")
	@ResponseBody
	public Map<String, String> examEnable(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "isExam") Integer isExam, @RequestParam(name = "id") String id,
			@RequestParam(name = "updateUser") String updateUser) {

		Map<String, String> map = new HashMap<String, String>();
		EduVocationInfoEntity eduVocationInfoEntity = eduVocationInfoService.findId(id);
		eduVocationInfoEntity.setIsExam(isExam);
		if (isExam.equals(0)) {
			map.put("state", "0");
			map.put("message", "关闭考试成功");
		}
		if (isExam.equals(1)) {
			map.put("state", "1");
			map.put("message", "开启考试成功");
		}
		eduVocationInfoService.update(eduVocationInfoEntity);
		return map;
	}

	// 查询 分页
	@RequestMapping(value = "/findByName")
	@ResponseBody
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "createBy") String createBy, @RequestParam(name = "status") String status,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<EduVocationInfoEntity> vocationInfoList = eduVocationInfoService.findListByName(createBy, status,
				pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", vocationInfoList);
		return map;
	}

	// 职业类别添加
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("eduVocationInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		EduVocationInfoEntity eduVocationInfoEntity = jsonObject.toJavaObject(EduVocationInfoEntity.class);
		Date date = new Date();
		eduVocationInfoEntity.setCreateDate(date);
		eduVocationInfoEntity.setStatus(1);
		eduVocationInfoEntity.setSort("1");
		eduVocationInfoService.save(eduVocationInfoEntity);
		map.put("state", "0");
		map.put("message", "添加成功");
		return map;
	}

	// 职业类别修改
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("eduVocationInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		EduVocationInfoEntity eduVocationInfoEntity = jsonObject.toJavaObject(EduVocationInfoEntity.class);
		Date date = new Date();
		eduVocationInfoEntity.setUpdateDate(date);
		eduVocationInfoService.update(eduVocationInfoEntity);
		map.put("state", "0");
		map.put("message", "修改成功");
		return map;
	}

	// 职业类别删除
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<EduQuestionInfoEntity> questionList = eduQuestionInfoService.findQuestVocationId(id);
			List<EduExamPaperInfoEntity> examList = eduExamPaperInfoService.findExamVocationId(id);
			List<EduManualInfoEntity> manualList = eduManualInfoService.findManualVocationId(id);
			if(questionList.size() == 0 && examList.size() == 0 && manualList.size() == 0) {
				eduVocationInfoService.delete(id);
				map.put("state", "0");
				map.put("message", "删除成功");
			}else {
				map.put("state", "1");
				map.put("message", "删除失败，请先解除职业类别的关联关系！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	// 通过id查询
	@RequestMapping(value = "/findById")
	@ResponseBody
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		EduVocationInfoEntity eduVocationInfoEntity = eduVocationInfoService.findId(id);
		if (eduVocationInfoEntity != null) {
			map.put("state", "0");// 成功
			map.put("data", eduVocationInfoEntity);
		} else {
			map.put("state", "1");
		}
		return map;
	}

	// 启用/禁用
	@RequestMapping(value = "/enable")
	@ResponseBody
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") Integer status, @RequestParam(name = "id") String id,
			@RequestParam(name = "updateUser") String updateUser) {

		Date date = new Date();
		Map<String, String> map = new HashMap<String, String>();
		EduVocationInfoEntity eduVocationInfoEntity = eduVocationInfoService.findId(id);
		eduVocationInfoEntity.setStatus(status);
		eduVocationInfoEntity.setUpdateDate(date);
		eduVocationInfoEntity.setUpdateBy(updateUser);
		boolean result = true;
		if (status.equals(0)) {
			map.put("state", "0");
			map.put("message", "启用成功");
		}
		if (status.equals(1)) {
			map.put("state", "1");
			map.put("message", "禁用成功");
			result = false;
		}
		eduVocationInfoService.enable(eduVocationInfoEntity, result);
		return map;
	}

	// 修改排序
	@RequestMapping(value = "/changeSort")
	@ResponseBody
	public Map<String, String> changeSort(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id, @RequestParam(name = "sort") String sort) {

		Date date = new Date();
		Map<String, String> map = new HashMap<String, String>();
		EduVocationInfoEntity eduVocationInfoEntity = eduVocationInfoService.findId(id);
		List<EduVocationInfoEntity> vocationInfoList = eduVocationInfoService.findSort();
		for (int i = 0; i < vocationInfoList.size(); i++) {
			if (vocationInfoList.get(i).getSort().contains(sort) == true) {
				map.put("state", "1");
				map.put("message", "排序不能重复");
				return map;
			}
		}
		eduVocationInfoEntity.setSort(sort);
		eduVocationInfoEntity.setUpdateDate(date);
		eduVocationInfoService.changeSort(eduVocationInfoEntity);
		map.put("state", "0");
		map.put("message", "修改成功");
		return map;
	}

	// 查询有效职业类别
	@RequestMapping(value = "/occupation")
	@ResponseBody
	public Map<String, Object> findVocabel(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<EduVocationInfoEntity> vocationList = eduVocationInfoService.findVocationId();
		map.put("state", "0");
		map.put("data", vocationList);
		return map;
	}
	// 查询有效并考试职业类别
	@RequestMapping(value = "/findVocationIsExam")
	@ResponseBody
	public Map<String, Object> findVocabelExamId(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<EduVocationInfoEntity> vocationExamList = eduVocationInfoService.findVocationIsExamId();
		map.put("state", "0");
		map.put("data", vocationExamList);
		return map;
	}
}
