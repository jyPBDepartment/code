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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jy.pc.Entity.ClassificationEntity;
import com.jy.pc.Entity.FarmworkEntity;
import com.jy.pc.Service.AgriculturalService;
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

	//根据预约信息ID取消预约
	@RequestMapping(value = "/cancelById")
	public Map<String, Object> cancelById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		FarmworkEntity agricultural = farmworkService.findById(id);
		if (agricultural != null) {
			map.put("status", "0");// 取消成功
			map.put("message", "取消成功");
		} else {
			map.put("status", "1");// 取消失败
		}
		agricultural.setStatus("3");
		farmworkService.save(agricultural);
		return map;
	}
	
	//根据预约信息ID返回联系方式
	
	//获取预约详情
	@RequestMapping(value = "/findDetail")
	public Map<String, Object> findDetail(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		FarmworkEntity agricultural = farmworkService.findById(id);
		if (agricultural != null) {
			map.put("status", "0");// 查询数据成功
			map.put("data", agricultural);
		} else {
			map.put("status", "1");// 查询数据失败
		}
		return map;
	}
	
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
