package com.jy.pc.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jy.pc.Entity.ClassificationEntity;
import com.jy.pc.Entity.FarmworkEntity;
import com.jy.pc.Service.ClassificationService;
import com.jy.pc.Service.FarmworkService;

@Controller
@ResponseBody
@RequestMapping(value = "farmwork")
@RestController
public class FarmworkController {

	@Autowired
	private FarmworkService farmworkService;
	@Autowired
	private ClassificationService ClassificationService;

	// 农活预约添加
	@RequestMapping(value = "/save")
	public Map<String, String> addPostInfo(HttpServletRequest res, HttpSession session, HttpServletResponse req,
			FarmworkEntity farmworkEntity) {
		Map<String, String> map = new HashMap<String, String>();
		Date date = new Date();
		farmworkEntity.setBeginDate(date);
		farmworkEntity.setStatus("0");
		ClassificationEntity classificationEntity = ClassificationService
				.findBId(farmworkEntity.getClassiOperateType());
//		farmworkEntity.setOperateType(classificationEntity.getCode());
		farmworkService.save(farmworkEntity);
		map.put("status", "0");
		map.put("message", "添加成功");
		return map;
	}
}
