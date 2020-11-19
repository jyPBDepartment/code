package com.jy.pc.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jy.pc.Entity.EduIssueInfoEntity;
import com.jy.pc.Entity.EduLessonStudentRelationEntity;
import com.jy.pc.Entity.EduManualInfoEntity;
import com.jy.pc.Enum.InterfaceCode;
import com.jy.pc.Service.EduIssueService;
import com.jy.pc.Service.EduLessonInfoService;
import com.jy.pc.Service.EduManualInfoService;
import com.jy.pc.Service.EduUserExamService;
import com.jy.pc.VO.LessonEnrollVO;
import com.jy.pc.VO.UserExamVO;
import com.jy.pc.VO.UserIsApplyVO;


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
	@Autowired
	private EduLessonInfoService eduLessonInfoService;
	
	@Autowired
	private EduIssueService eduIssueService;
	
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
		List<UserExamVO> userExamVOList = new ArrayList<UserExamVO>();
		try {
			List<Map<String,Object>> eduUserExamlist= eduUserExamService.getExamResultByUserId(userId);
			for(int i=0;i<eduUserExamlist.size();i++) {
				String jsonStr = JSONObject.toJSONString(eduUserExamlist.get(i));
				UserExamVO vo = JSON.parseObject(jsonStr, UserExamVO.class);
				userExamVOList.add(vo);
			}
			map.put("code", "200");// 查询成功
			map.put("data", userExamVOList);
		} catch (Exception e) {
			map.put("code", "201");// 查询失败
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	
	/**
	 * 我的-考试是否都通过，并且是否申请证书
	 * 
	 * @Param userId 用户Id
	 */
	@RequestMapping("/getExamIsPassByUserId")
	@ResponseBody
	public Map<String, Object> getExamIsPassByUserId(String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserIsApplyVO> userIsApplyVOList = new ArrayList<UserIsApplyVO>();
		try {
			List<Map<String,Object>> examIsPasslist= eduUserExamService.getExamIsPassByUserId(userId);
			for(int i=0;i<examIsPasslist.size();i++) {
				String jsonStr = JSONObject.toJSONString(examIsPasslist.get(i));
				UserIsApplyVO vo = JSON.parseObject(jsonStr, UserIsApplyVO.class);
				userIsApplyVOList.add(vo);
			}
			map.put("code", "200");// 查询成功
			map.put("data", userIsApplyVOList);
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
		@RequestMapping(value = "getLessonsByUserId")
		@ResponseBody
		public Map<String, Object> getLessonsByUserId(HttpServletRequest res, HttpServletResponse req,
				@RequestParam(name = "userId") String userId) {
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				// 根据userId查询出此人课程报名情况
				List<EduLessonStudentRelationEntity> lessonList = eduLessonInfoService.getLessonsByUserId(userId);
				List<LessonEnrollVO> result = new ArrayList<LessonEnrollVO>();
				for (EduLessonStudentRelationEntity entity : lessonList) {
					LessonEnrollVO vo = new LessonEnrollVO(entity);
					result.add(vo);
				}
				map.put("code", InterfaceCode.SUCCESS.getCode());// 成功
				map.put("message", InterfaceCode.SUCCESS.getMessage());
				map.put("data", result);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("code", InterfaceCode.FAIL_UNKNOWN_ERROR.getCode());// 失败
				map.put("message", e.getMessage());
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
	public Map<String, Object> getCertificateByUserId(String userId) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List<EduIssueInfoEntity> eduIssueInfoList = eduIssueService.findInfoByUserId(userId);
			map.put("code", "200");// 查询成功
			map.put("data", eduIssueInfoList);
		} catch (Exception e) {
			map.put("code", "201");// 查询失败
		}
		return map;
	}
	

}
