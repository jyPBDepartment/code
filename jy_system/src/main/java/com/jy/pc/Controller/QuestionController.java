package com.jy.pc.Controller;

import java.util.Date;
import java.util.HashMap;
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
import com.jy.pc.Entity.QuestionEntity;
import com.jy.pc.Service.QuestionService;

@Controller
@ResponseBody
@RequestMapping(value = "/questionnaire")
public class QuestionController {
	@Autowired
	private QuestionService questionService;

	// 调查问卷添加
	@RequestMapping(value = "save")
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("questionEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);

		Date date = new Date();
		QuestionEntity questionEntity = jsonObject.toJavaObject(QuestionEntity.class);
		questionEntity.setCreateDate(date);
		QuestionEntity questionn =questionService.save(questionEntity);
		if (questionn.getQuestionScore() > 26) {
			map.put("status", "0");
			map.put("tips", "您的身份为供应商！");
		}
		if (questionn.getQuestionScore() >= 20 && questionn.getQuestionScore() <= 26) {
			map.put("status", "1");
			map.put("tips", "您的身份为经销商！");
		}
		if (questionn.getQuestionScore() >= 14 && questionn.getQuestionScore() <= 19) {
			map.put("status", "2");
			map.put("tips", "您的身份为农业经理人！");
		}
		
		return map;
	}

	// 分类修改前查询
	@RequestMapping(value = "findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		QuestionEntity question = questionService.findBId(id);
		if (question != null) {
			map.put("status", "0");// 查询数据成功
			map.put("data", question);
		} else {
			map.put("status", "1");// 查询数据失败
		}
		return map;
	}

	// 分类修改
	@RequestMapping(value = "update")
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("questionEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		QuestionEntity questionEntity = jsonObject.toJavaObject(QuestionEntity.class);
		questionService.update(questionEntity);
		map.put("message", "修改成功");
		return map;
	}

	// 分类删除
	@RequestMapping(value = "delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();// 接收数据容器
		questionService.delete(id);
		map.put("status", "0");
		map.put("message", "删除成功");
		return map;
	}

	// 分类模糊查询与分页
	@RequestMapping(value = "findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "phoneNum") String phoneNum,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<QuestionEntity> questionList = questionService.findListByName(name, phoneNum, pageable);
		map.put("status", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", questionList);

		return map;
	}
}
