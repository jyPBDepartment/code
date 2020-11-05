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
import com.jy.pc.Entity.EduPictureInfoEntity;
import com.jy.pc.Service.EduPictureService;
/**
 *  图片设置Controller
 * */
@Controller
@RequestMapping(value = "/pictureInfo")
public class EduPictureController {
	@Autowired
	private EduPictureService eduPictureService;
	
	// 查询 分页
	@RequestMapping(value = "/findByName")
	@ResponseBody
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "createBy") String createBy,
			@RequestParam(name = "picType") String picType,@RequestParam(name = "status") String status,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<EduPictureInfoEntity> pictureInfoList = eduPictureService.findListByName(createBy,picType, status, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", pictureInfoList);
		return map;
	}

	// 添加
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("eduPictureInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		EduPictureInfoEntity eduPictureInfoEntity = jsonObject.toJavaObject(EduPictureInfoEntity.class);
		Date date = new Date();
		eduPictureInfoEntity.setCreateDate(date);
		eduPictureInfoEntity.setStatus(1);
		eduPictureInfoEntity.setSort("1");
		eduPictureService.save(eduPictureInfoEntity);
		map.put("state", "0");
		map.put("message", "添加成功");
		return map;
	}
		
	// 修改
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("eduPictureInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		EduPictureInfoEntity eduPictureInfoEntity = jsonObject.toJavaObject(EduPictureInfoEntity.class);
		Date date = new Date();
		eduPictureInfoEntity.setUpdateDate(date);
		eduPictureInfoEntity.setStatus(1);
		eduPictureService.update(eduPictureInfoEntity);
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
		EduPictureInfoEntity eduPictureInfoEntity = eduPictureService.findId(id);
		//判断状态生效是不可删除
		if(eduPictureInfoEntity.getStatus() == 1) {
			eduPictureService.delete(id);
			map.put("state", "0");
			map.put("message", "删除成功");			
		}else {
			map.put("state", "1");
			map.put("message", "删除失败,数据生效时不能被删除！");			
		}
		return map;
	}
		
	//通过id查询
	@RequestMapping(value = "/findById")
	@ResponseBody
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		EduPictureInfoEntity eduPictureInfoEntity = eduPictureService.findId(id);
		if (eduPictureInfoEntity != null) {
			map.put("state", "0");
			map.put("data", eduPictureInfoEntity);
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
		EduPictureInfoEntity eduPictureInfoEntity = eduPictureService.findId(id);
		List<EduPictureInfoEntity> pictureOne = eduPictureService.findTypeOne();
		List<EduPictureInfoEntity> pictureTwo = eduPictureService.findTypeTwo();
		List<EduPictureInfoEntity> picturethree = eduPictureService.findTypeThree();
		boolean result = true;
		//禁用
		if (status.equals(1)) {
			eduPictureInfoEntity.setStatus(status);
			eduPictureInfoEntity.setUpdateDate(date);
			eduPictureInfoEntity.setUpdateBy(updateUser);
			map.put("state", "1");
			map.put("message", "禁用成功");
			result = false;
		}
		//启用
		if (status.equals(0)) {
			//轮播图
			if(eduPictureInfoEntity.getPicType() == 0) {
				if(pictureOne.size() <3) {
					eduPictureInfoEntity.setStatus(status);
					eduPictureInfoEntity.setUpdateDate(date);
					eduPictureInfoEntity.setUpdateBy(updateUser);
					map.put("state", "0");
					map.put("message", "启用成功");
				}else {
					map.put("state", "2");
					map.put("message", "轮播图最多可启用3张图片！");
				}
			}
			//学习手册
			if(eduPictureInfoEntity.getPicType() == 1) {
				if(pictureTwo.size() <1) {
					eduPictureInfoEntity.setStatus(status);
					eduPictureInfoEntity.setUpdateDate(date);
					eduPictureInfoEntity.setUpdateBy(updateUser);
					map.put("state", "0");
					map.put("message", "启用成功");
				}else {
					map.put("state", "2");
					map.put("message", "学习手册最多可启用1张图片！");
				}
			}
			//考试
			if(eduPictureInfoEntity.getPicType() == 2) {
				if(picturethree.size() <1) {
					eduPictureInfoEntity.setStatus(status);
					eduPictureInfoEntity.setUpdateDate(date);
					eduPictureInfoEntity.setUpdateBy(updateUser);
					map.put("state", "0");
					map.put("message", "启用成功");
				}else {
					map.put("state", "2");
					map.put("message", "考试最多可启用1张图片！");
				}
			}
		}
		eduPictureService.enable(eduPictureInfoEntity,result);
		return map;
	}
		
	// 修改排序
	@RequestMapping(value = "/changeSort")
	@ResponseBody
	public Map<String, String> changeSort(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id, @RequestParam(name = "sort") String sort) {

		Date date = new Date();
		Map<String, String> map = new HashMap<String, String>();
		EduPictureInfoEntity eduPictureInfoEntity = eduPictureService.findId(id);
		List<EduPictureInfoEntity> eduPictureList=eduPictureService.findSort();
		for(int i=0;i<eduPictureList.size();i++) {
			if(eduPictureList.get(i).getSort().contains(sort) == true) {
				map.put("state", "1");
				map.put("message", "排序不能重复");
				return map;
			}
		}
		eduPictureInfoEntity.setSort(sort);
		eduPictureInfoEntity.setUpdateDate(date);
		eduPictureService.changeSort(eduPictureInfoEntity);
		map.put("state", "0");
		map.put("message", "修改成功");
		return map;
	}
	
	@RequestMapping(value = "/getListByType")
	@ResponseBody
	public Map<String, Object> getListByType(int picType){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<EduPictureInfoEntity> eduPictureInfoList = eduPictureService.findListByPicType(picType);
			map.put("code", "200");
			map.put("data", eduPictureInfoList);
		}catch(Exception e) {
			map.put("code", "201");
			map.put("data", e.getMessage());
		}
		
		return map;
	}
	
}
