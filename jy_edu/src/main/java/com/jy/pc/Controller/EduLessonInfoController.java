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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jy.pc.Entity.EduLessonInfoEntity;
import com.jy.pc.Service.EduLessonInfoService;

@RequestMapping(value = "lesson")
@RestController
public class EduLessonInfoController {
	@Autowired
	private EduLessonInfoService eduLessonInfoService;

	// 分页条件查询
	@RequestMapping(value = "/findPage")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "status") String status, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<EduLessonInfoEntity> lessonList = eduLessonInfoService.findListByParam(name, status, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", lessonList);
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

}
