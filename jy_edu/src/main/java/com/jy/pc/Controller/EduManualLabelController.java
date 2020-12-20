package com.jy.pc.Controller;

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

import com.alibaba.fastjson.JSONObject;
import com.jy.pc.Entity.EduManualLabelInfoEntity;
import com.jy.pc.Service.EduManualLabelService;
/**
 * 标签 Controller
 * 
 * */
@Controller
@RequestMapping(value = "/manualLabel")
public class EduManualLabelController {
	@Autowired
	private EduManualLabelService eduManualLabelService;
	
	// 查询 分页
	@RequestMapping(value = "/findByName")
	@ResponseBody
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "createBy") String createBy,
			@RequestParam(name = "name") String name,@RequestParam(name = "status") String status,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		try {
			Page<EduManualLabelInfoEntity> manualLabelList = eduManualLabelService.findListByName(createBy,name, status, pageable);
			map.put("state", "0");// 成功
			map.put("message", "查询成功");
			map.put("data", manualLabelList);
		} catch (Exception e) {
			map.put("state", "1");// 失败
			map.put("message", "查询失败");
		}
		
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
		try {
			eduManualLabelService.save(eduManualLabelInfoEntity);
			map.put("state", "0");//成功
			map.put("message", "添加成功");
		} catch (Exception e) {
			map.put("state", "1");//失败
			map.put("message", "添加失败");
		}
		
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
		try {
			eduManualLabelService.update(eduManualLabelInfoEntity);
			map.put("state", "0");//成功
			map.put("message", "修改成功");
		} catch (Exception e) {
			map.put("state", "0");//失败
			map.put("message", "修改失败");
		}
	
		return map;
	}

	// 删除
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<EduManualLabelInfoEntity> manualLabelList = eduManualLabelService.findManualLink();
		for(int i=0;i<manualLabelList.size();i++) {
			if(manualLabelList.get(i).getId().equals(id)) {
				eduManualLabelService.delete(id);
				map.put("state", "0");
				map.put("message", "删除成功");
			}
			else {}
		}
		if(manualLabelList.size() <= 0) {}
		return map;
	}
			
	//通过id查询
	@RequestMapping(value = "/findById")
	@ResponseBody
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		EduManualLabelInfoEntity eduManualLabelInfoEntity = eduManualLabelService.findId(id);
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
		EduManualLabelInfoEntity eduManualLabelInfoEntity = eduManualLabelService.findId(id);
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
		eduManualLabelService.enable(eduManualLabelInfoEntity,result);
		return map;
	}
	
	// 查询有效标签
		@RequestMapping(value = "/label")
		@ResponseBody
		public Map<String, Object> findLabel(HttpServletRequest res, HttpServletResponse req){
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				List<EduManualLabelInfoEntity> manualLabelList = eduManualLabelService.findManualLabelId();
				map.put("state", "0");//成功
				map.put("data", manualLabelList);
			} catch (Exception e) {
				map.put("state", "1");//失败
			}
			
			return map;
		}

}
