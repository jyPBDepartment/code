package com.jy.pc.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.pc.Entity.EduManualInfoEntity;
import com.jy.pc.Service.EduManualInfoService;
import com.jy.pc.Service.EduUserExamService;


/**
 * h5端我的控制层
 * @author Stephen
 * 
 * */
@Controller
@RequestMapping("/mine")
public class EduAppMineController {
	
	@Autowired
	private EduManualInfoService eduManualInfoService;//手册信息业务层
	@Autowired
	private EduUserExamService eduUserExamService;//用户考试关联业务层
	
	/**
	 * 我的-我的收藏、学习记录
	 * 
	 * @Param userId 用户Id
	 */
	@RequestMapping("/getManualListByUserId")
	@ResponseBody
	public Map<String, Object> getManualListByUserId(String userId, int isCollection) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List<EduManualInfoEntity> eduManualInfoList = eduManualInfoService.getManualListByUserId(userId,
					isCollection);
			map.put("code", "200");// 查询成功
			map.put("data", eduManualInfoList);
		} catch (Exception e) {
			map.put("code", "201");// 查询失败
			map.put("msg",e.getMessage());
		}
		return map;
	}
	
	/**
	 * 我的-考试成绩
	 * 
	 * @Param userId 用户Id
	 */
	@RequestMapping("/getExamResultByUserId")
	@ResponseBody
	public Map<String, Object> getExamResultByUserId(String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> eduUserExamlist= eduUserExamService.getExamResultByUserId(userId);
			map.put("code", "200");// 查询成功
			map.put("data", eduUserExamlist);
		} catch (Exception e) {
			map.put("code", "201");// 查询失败
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	/**
	 * 我的-报名课程
	 * 
	 * @Param userId 用户Id
	 */
	@RequestMapping("/getLessonByUserId")
	@ResponseBody
	public Map<String, Object> getLessonByUserId(String userId, int isCollection) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List<EduManualInfoEntity> eduManualInfoList = eduManualInfoService.getManualListByUserId(userId,
					isCollection);
			map.put("code", "200");// 查询成功
			map.put("data", eduManualInfoList);
		} catch (Exception e) {
			map.put("code", "201");// 查询失败
		}
		return map;
	}
	
	/**
	 * 我的-报名课程
	 * 
	 * @Param userId 用户Id
	 */
	@RequestMapping("/getCertificateByUserId")
	@ResponseBody
	public Map<String, Object> getCertificateByUserId(String userId, int isCollection) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List<EduManualInfoEntity> eduManualInfoList = eduManualInfoService.getManualListByUserId(userId,
					isCollection);
			map.put("code", "200");// 查询成功
			map.put("data", eduManualInfoList);
		} catch (Exception e) {
			map.put("code", "201");// 查询失败
		}
		return map;
	}
	

}
