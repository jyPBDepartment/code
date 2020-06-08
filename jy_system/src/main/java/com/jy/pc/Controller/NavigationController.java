package com.jy.pc.Controller;

import java.text.DateFormat;
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
import com.jy.pc.Entity.NavigationEntity;
import com.jy.pc.Entity.WebsiteInfoEntity;
import com.jy.pc.Service.NavigationService;

@Controller
@ResponseBody
@RequestMapping(value = "/navigation")
public class NavigationController {
	@Autowired
	private NavigationService navigationService;

////	导航添加
//	@RequestMapping(value="save")
//	public Map<String,String> save(HttpServletRequest res,HttpServletResponse req,@RequestParam(name="name")String name,
//			@RequestParam(name="subId")String subId,@RequestParam(name="dropDownEnName")String dropDownEnName,
//			@RequestParam(name="url")String url,@RequestParam(name="path")String path,@RequestParam(name="status")String status){
//				NavigationEntity navigationEntity = new NavigationEntity();
//				navigationEntity.setName(name);
//				navigationEntity.setSubId(subId);
//				navigationEntity.setDropDownEnName(dropDownEnName);
//				navigationEntity.setUrl(url);
//				navigationEntity.setPath(path);
//				navigationEntity.setStatus("0");
//				String createDateTime = DateFormat.getDateTimeInstance().format(new Date());
//				navigationEntity.setCreateDateTime(createDateTime);
//				navigationEntity.setUpdateTime(createDateTime);
//				navigationService.save(navigationEntity);
//				Map<String, String> map = new HashMap<String, String>();
//				map.put("message","添加成功");
//				return map;
//	}

	@RequestMapping(value = "save")
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("navigationEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		NavigationEntity navigationEntity = jsonObject.toJavaObject(NavigationEntity.class);
		String createDateTime = DateFormat.getDateTimeInstance().format(new Date());
		navigationEntity.setCreateDateTime(createDateTime);
		navigationEntity.setUpdateTime(createDateTime);
		navigationService.save(navigationEntity);
	
		map.put("message", "添加成功");
		return map;
	}

	// 导航修改前查询
	@RequestMapping(value = "findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		NavigationEntity navigation = navigationService.findBId(id);
		if (navigation != null) {
			map.put("status", "0");// 查询数据成功
			map.put("data", navigation);
		} else {
			map.put("status", "1");// 查询数据失败
		}
		return map;
	}
	//下拉列表显示
	@RequestMapping(value = "findAll")
	public Map<String,Object> findAll(HttpServletRequest res,HttpServletResponse req) {

		Map<String,Object> map = new HashMap<String,Object>();//接收数据容器
		List<NavigationEntity> navigationList = navigationService.findAll();//查询所有数据方法
		map.put("status", "0");
		map.put("message", "查询成功");
		map.put("data", navigationList);
		return map;
	}
	// 导航修改
	@RequestMapping(value = "update")
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("navigationEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		NavigationEntity navigationEntity = jsonObject.toJavaObject(NavigationEntity.class);
		String updateTime = DateFormat.getDateTimeInstance().format(new Date());
		navigationEntity.setUpdateTime(updateTime);
		navigationService.update(navigationEntity);
		map.put("message", "修改成功");
		return map;
	}

	// 导航删除
	@RequestMapping(value = "delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();// 接收数据容器
		navigationService.delete(id);
		map.put("status", "0");
		map.put("message", "删除成功");
		return map;
	}

	// 导航模糊查询与分页
	@RequestMapping(value = "findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<NavigationEntity> navigationList = navigationService.findListByName(name, pageable);
		map.put("status", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", navigationList);
		System.out.println(navigationList);
		return map;
	}

	// 导航禁用
	@RequestMapping(value = "/enable")
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") String status, @RequestParam(name = "id") String id) {
		Map<String, String> map = new HashMap<String, String>();
		NavigationEntity navigationEntity = navigationService.findBId(id);
		navigationEntity.setStatus(status);
		navigationEntity.getStatus();
		if (status.equals("0")) {
			navigationEntity.setStatus("1");
			map.put("state", "0");
			map.put("message", "禁用成功");
		} else if (status.equals("1")) {
			navigationEntity.setStatus("0");
			map.put("state", "0");
			map.put("message", "启用成功");
		}
		navigationService.update(navigationEntity);
		return map;
	}
	
	/**
	 * 查询上级导航列表
	 * 
	 * */
	@RequestMapping(value = "/findSubNavList")
	public Map<String, Object> findSubNavList(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<NavigationEntity> websiteInfoEntity = navigationService.findSubNavList();
		if (websiteInfoEntity != null) {
			map.put("status", "0");
			map.put("data", websiteInfoEntity);
		} else {
			map.put("status", "1");
		}
		return map;
	}
}
