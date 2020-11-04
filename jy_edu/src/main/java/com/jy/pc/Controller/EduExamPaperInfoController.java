package com.jy.pc.Controller;

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

import com.jy.pc.Entity.EduExamPaperInfoEntity;
import com.jy.pc.Entity.EduQuestionInfoEntity;
import com.jy.pc.Service.EduExamPaperInfoService;

/**
 * 试卷信息表Controller
 * */
@Controller
@RequestMapping(value="examPaperInfo")
public class EduExamPaperInfoController {
	@Autowired
	private EduExamPaperInfoService eduExamPaperInfoService;

	// 查询 分页
	@RequestMapping(value = "/findByName")
	@ResponseBody
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "createBy") String createBy,@RequestParam(name = "status") String status,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<EduExamPaperInfoEntity> questionList = eduExamPaperInfoService.findListByName(createBy, status, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", questionList);
		return map;
	}
	
	// 添加
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req,
			EduExamPaperInfoEntity eduExamPaperInfoEntity,@RequestParam(name = "questionId") String questionId) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			eduExamPaperInfoService.saveQuest(res, req, eduExamPaperInfoEntity, questionId);
			map.put("state", "0");
			map.put("message", "添加成功");
			
		} catch (Exception e) {
			map.put("state", "1");
			map.put("message", "添加失败");
		
		}
		return map;
	}
}
