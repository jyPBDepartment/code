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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jy.pc.Entity.EduLessonInfoEntity;
import com.jy.pc.Entity.EduLessonStudentRelationEntity;
import com.jy.pc.Entity.EduVocationInfoEntity;
import com.jy.pc.Enum.InterfaceCode;
import com.jy.pc.Service.EduLessonInfoService;
import com.jy.pc.VO.LessonEnrollVO;
import com.jy.pc.VO.LessonListVO;

/**
 * 线下课程相关
 * 
 * @author admin
 *
 */
@RequestMapping(value = "lesson")
@RestController
public class EduLessonInfoController {
	@Autowired
	private EduLessonInfoService eduLessonInfoService;

	// 移动端-用户报名课程Enroll
	@RequestMapping(value = "enrollLesson")
	public Map<String, Object> enrollLesson(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "userId") String userId, @RequestParam(name = "lessonId") String lessonId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			EduLessonInfoEntity lesson = eduLessonInfoService.findInfobyId(lessonId);
			List<EduLessonStudentRelationEntity> relaList = eduLessonInfoService.findRelaById(lessonId, "");
			if (relaList.size() >= lesson.getStuLimit()) {
				//当前报名人数已到上限
				map.put("code", InterfaceCode.LESSON_NUM_LIMIT.getCode());// 成功
				map.put("message", "报名人数已达上限");
				return map;
			}
			eduLessonInfoService.enrollLesson(lesson,userId);
			map.put("code", InterfaceCode.SUCCESS.getCode());// 成功
			map.put("message", "报名成功");
		} catch (Exception e) {
			map.put("code", InterfaceCode.FAIL_UNKNOWN_ERROR.getCode());// 失败
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 移动端 -我的-报名课程列表加载
	@RequestMapping(value = "getLessonsByUserId")
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
			map.put("code", InterfaceCode.FAIL_UNKNOWN_ERROR.getCode());// 失败
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 移动端 - 首页-线下课程加载
	@RequestMapping(value = "getListByLessonDay")
	public Map<String, Object> getListByReading(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<EduLessonInfoEntity> lessonList = eduLessonInfoService.getListByReading();
			List<LessonListVO> result = new ArrayList<LessonListVO>();
			for (EduLessonInfoEntity entity : lessonList) {
				LessonListVO vo = new LessonListVO(entity);
				result.add(vo);
			}
			map.put("code", InterfaceCode.SUCCESS.getCode());// 成功
			map.put("message", InterfaceCode.SUCCESS.getMessage());
			map.put("data", result);
		} catch (Exception e) {
			map.put("code", InterfaceCode.FAIL_UNKNOWN_ERROR.getCode());// 失败
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 返回报名情况
	@RequestMapping(value = "findStuListByLesson")
	public Map<String, Object> findRelaById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "lessonId") String lessonId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<EduLessonStudentRelationEntity> lessonList = eduLessonInfoService.findRelaById(lessonId, name);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", lessonList);
		return map;
	}

	// 分页条件查询
	@RequestMapping(value = "/findPage")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "createBy") String createBy, @RequestParam(name = "name") String name,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "status") String status,
			@RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<EduLessonInfoEntity> lessonList = eduLessonInfoService.findListByParam(name, status, createBy, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", lessonList);
		return map;
	}

	// 根据id返回详情信息
	@RequestMapping(value = "/findById")
	@ResponseBody
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		EduLessonInfoEntity lessonEntity = eduLessonInfoService.findInfobyId(id);
		if (lessonEntity != null) {
			map.put("state", "0");
			map.put("data", lessonEntity);
		} else {
			map.put("state", "1");
		}
		return map;
	}

	// 根据id删除课程信息
	@RequestMapping(value = "/delete")
	public Map<String, String> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		eduLessonInfoService.delete(id);
		map.put("state", "0");
		map.put("message", "删除成功");
		return map;
	}

	// 切换生效状态
	@RequestMapping(value = "/enableSwitch")
	public Map<String, String> enable(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") int status, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		EduLessonInfoEntity entity = eduLessonInfoService.findInfobyId(id);
		Date date = new Date();
		entity.setUpdateDate(date);
		entity.setStatus(status);
		boolean result = true;
		if (status == 0) {
			map.put("status", "0");
			map.put("message", "启用成功");
		}
		if (status == 1) {
			map.put("status", "1");
			map.put("message", "禁用成功");
			result = false;
		}
		eduLessonInfoService.enable(entity, result);
		return map;
	}

	// 添加
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("lessonEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		EduLessonInfoEntity lessonEntity = jsonObject.toJavaObject(EduLessonInfoEntity.class);
		EduVocationInfoEntity vocation = new EduVocationInfoEntity();
		vocation.setId(lessonEntity.getVocationId());
		lessonEntity.setVocation(vocation);
		lessonEntity.setStatus(1);
		lessonEntity.setEnrollStatus(1);
		Date date = new Date();
		lessonEntity.setCreateDate(date);
		lessonEntity.setStatus(1);
		eduLessonInfoService.save(lessonEntity);
		map.put("state", "0");
		map.put("message", "添加成功");
		return map;
	}

	// 修改
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("lessonEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		EduLessonInfoEntity lessonEntity = jsonObject.toJavaObject(EduLessonInfoEntity.class);
		Date date = new Date();
		lessonEntity.setCreateDate(date);
		lessonEntity.setStatus(1);
		eduLessonInfoService.update(lessonEntity);
		map.put("state", "0");
		map.put("message", "修改成功");
		return map;
	}
}
