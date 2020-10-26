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
import com.jy.pc.Entity.EduManualLabelInfoEntity;
import com.jy.pc.Service.EduManualLabelService;

@Controller
@RequestMapping(value = "/manualLabel")
public class EduManualLabelController {
	@Autowired
	private EduManualLabelService EduManualLabelService;
	
	// 查询 分页
	@RequestMapping(value = "/findByName")
	@ResponseBody
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "createBy") String createBy,
			@RequestParam(name = "name") String name,@RequestParam(name = "status") String status,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<EduManualLabelInfoEntity> manualLabelList = EduManualLabelService.findListByName(createBy,name, status, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", manualLabelList);
		return map;
	}

	// 添加
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("eduManualLabelInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		EduManualLabelInfoEntity eduManualLabelInfoEntity = jsonObject.toJavaObject(EduManualLabelInfoEntity.class);
		Date date = new Date();
		eduManualLabelInfoEntity.setCreateDate(date);
		eduManualLabelInfoEntity.setStatus(1);
		EduManualLabelService.save(eduManualLabelInfoEntity);
		map.put("state", "0");
		map.put("message", "添加成功");
		return map;
	}
			
	// 修改
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("eduManualLabelInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		EduManualLabelInfoEntity eduManualLabelInfoEntity = jsonObject.toJavaObject(EduManualLabelInfoEntity.class);
		Date date = new Date();
		eduManualLabelInfoEntity.setUpdateDate(date);
		eduManualLabelInfoEntity.setStatus(1);
		EduManualLabelService.update(eduManualLabelInfoEntity);
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
		EduManualLabelService.delete(id);
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
		EduManualLabelInfoEntity eduManualLabelInfoEntity = EduManualLabelService.findId(id);
		if (eduManualLabelInfoEntity != null) {
			map.put("state", "0");
			map.put("data", eduManualLabelInfoEntity);
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
		EduManualLabelInfoEntity eduManualLabelInfoEntity = EduManualLabelService.findId(id);
		eduManualLabelInfoEntity.setStatus(status);
		eduManualLabelInfoEntity.setUpdateDate(date);
		eduManualLabelInfoEntity.setUpdateBy(updateUser);
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
		EduManualLabelService.enable(eduManualLabelInfoEntity,result);
		return map;
	}

}
