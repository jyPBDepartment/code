package com.jy.pc.Controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.jy.pc.Entity.EduOptionInfoEntity;
import com.jy.pc.Entity.EduQuestionExamLinkEntity;
import com.jy.pc.Entity.EduQuestionInfoEntity;
import com.jy.pc.Service.EduOptionInfoService;
import com.jy.pc.Service.EduQuestionExamService;
import com.jy.pc.Service.EduQuestionInfoService;

/**
 * 试题表Controller
 * */
@Controller
@RequestMapping(value = "/questionInfo")
@RestController
public class EduQuestionInfoController {
	@Autowired
	private EduQuestionInfoService eduQuestionInfoService;
	@Autowired
	private EduOptionInfoService eduOptionInfoService;
	@Autowired
	private EduQuestionExamService eduQuestionExamService;
	
	// 查询 分页
	@RequestMapping(value = "/findByName")
	@ResponseBody
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "createBy") String createBy,
			@RequestParam(name = "quType") String quType,@RequestParam(name = "status") String status,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<EduQuestionInfoEntity> questionList = eduQuestionInfoService.findListByName(createBy, quType, status, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", questionList);
		return map;
	}

	// 添加
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req,
			EduQuestionInfoEntity eduQuestionInfoEntity,@RequestParam(name = "addOption") String addOption,
			@RequestParam(name = "addName") String addName) {

		Map<String, String> map = new HashMap<String, String>();
		eduQuestionInfoService.saveOption(res,req,eduQuestionInfoEntity, addOption, addName);
		map.put("state", "0");
		map.put("message", "添加成功");
		return map;
	}
					
	// 修改
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req,
			EduQuestionInfoEntity eduQuestionInfoEntity,@RequestParam(name = "addOption") String addOption,
			@RequestParam(name = "addName") String addName) {

		Map<String, String> map = new HashMap<String, String>();
		eduQuestionInfoService.updateOption(res, req, eduQuestionInfoEntity, addOption, addName);
		map.put("state", "0");
		map.put("message", "修改成功");
		return map;
	}

	// 删除
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<EduOptionInfoEntity> optionInfo = eduOptionInfoService.findquestionId(id);
			for(int i=0;i<optionInfo.size();i++) {
				eduOptionInfoService.delete(optionInfo.get(i).getId());
			}
			eduQuestionInfoService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("state", "0");
		map.put("message", "删除成功");			
		return map;
	}
					
	//通过id查询
	@RequestMapping(value = "/findById")
	@ResponseBody
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		EduQuestionInfoEntity eduQuestionInfoEntity = eduQuestionInfoService.findId(id);
		List<EduOptionInfoEntity> optionInfo = eduOptionInfoService.findquestionId(id);
		map.put("state", "0");
		map.put("data", eduQuestionInfoEntity);
		map.put("dataOption", optionInfo);
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
		EduQuestionInfoEntity eduQuestionInfoEntity = eduQuestionInfoService.findId(id);
		eduQuestionInfoEntity.setStatus(status);
		eduQuestionInfoEntity.setUpdateDate(date);
		eduQuestionInfoEntity.setUpdateBy(updateUser);
		boolean result = true;
		//启用
		if (status.equals(0)) {
			map.put("state", "0");
			map.put("message", "启用成功");	
		}
		//禁用
		if (status.equals(1)) {
			map.put("state", "1");
			map.put("message", "禁用成功");
			result = false;
		}
		eduQuestionInfoService.enable(eduQuestionInfoEntity,result);
		return map;
	}
	
	// 查询 分页(启用试题)
	@RequestMapping(value = "/findQuestion")
	@ResponseBody
	public Map<String, Object> findQuestion(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "quType") String quType,@RequestParam(name = "voationId") String voationId,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<EduQuestionInfoEntity> question = eduQuestionInfoService.findQuestion(quType, voationId, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", question);
		return map;
	}
	
	/**
	 * 加载试题列表接口
	 * */
	@RequestMapping(value = "/getQuestionListByExamId")
	@ResponseBody
	public Map<String, Object> getQuestionListByExamId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "examId") String examId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<EduQuestionExamLinkEntity> questionExamList = eduQuestionExamService.findExamId(examId);
		List<EduQuestionInfoEntity> questList = new ArrayList<EduQuestionInfoEntity>();
		for(int i=0;i<questionExamList.size();i++) {
			EduQuestionInfoEntity question = eduQuestionInfoService.findId(questionExamList.get(i).getQuestionId());
			List<EduOptionInfoEntity> option = eduOptionInfoService.findquestionId(question.getId());
			questList.add(question);
			questList.get(i).setOptionList(option);
		}
		map.put("code", "200");
		map.put("data", questList);
		return map;
	}
}
