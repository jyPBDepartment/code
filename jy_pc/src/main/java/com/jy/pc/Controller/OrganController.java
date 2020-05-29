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

import com.jy.pc.Entity.OrganEntity;
import com.jy.pc.Service.OrganService;

@Controller
@RequestMapping(value="/organ")
public class OrganController {
	@Autowired
	private OrganService organService;
	//机构添加
	@RequestMapping(value="/save")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest res,HttpServletResponse req,@RequestParam(name="name")String name,@RequestParam(name="superId")String superId,@RequestParam(name="context")String context) {
		
		System.out.println("添加");
		OrganEntity organEntity = new OrganEntity();
		organEntity.setName(name);
		organEntity.setSuperId(superId);
		
		
		String createTime = DateFormat.getDateTimeInstance().format(new Date());
		organEntity.setCreateTime(createTime);
		organEntity.setUpdateTime(createTime);
		organEntity.setContext(context);
		
		organService.save(organEntity);		
		Map<String, String> map = new HashMap<String, String>();
		map.put("message","添加成功");
		
		return map;
	}
	//机构修改
	@RequestMapping(value = "update")
	@ResponseBody
	public Map<String, String> update(HttpServletRequest res,HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s= res.getParameter("organEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		OrganEntity organEntity = jsonObject.toJavaObject(OrganEntity.class);
		String updateTime = DateFormat.getDateTimeInstance().format(new Date());
		organEntity.setUpdateTime(updateTime);
		organService.update(organEntity);
		map.put("message","修改成功");
		return map;
	}
	//机构删除
	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String,Object> delete(HttpServletRequest res,HttpServletResponse req,@RequestParam(name="id")String id) {
		Map<String,Object> map = new HashMap<String,Object>();//接收数据容器
		organService.delete(id);
		map.put("status", "0");
		map.put("message", "删除成功");
		return map;
	}
	//机构详情查询
	@RequestMapping(value = "findAll")
	@ResponseBody
	public Map<String,Object> findAll(HttpServletRequest res,HttpServletResponse req) {

		Map<String,Object> map = new HashMap<String,Object>();//接收数据容器
		List<OrganEntity> organList = organService.findAll();//查询所有数据方法
		map.put("status", "0");
		map.put("message", "查询成功");
		map.put("data", organList);
		return map;
	}
	//机构禁用
	@RequestMapping(value="/enable")
	@ResponseBody
	 public Map<String, String> opensulf(HttpServletRequest res,HttpServletResponse req,
	   @RequestParam(name="state")Integer state,@RequestParam(name="id")String id) {
	  
	  Map<String, String> map = new HashMap<String, String>();
	  OrganEntity organEntity = organService.findBId(id);
	  organEntity.setState(state);
	  organEntity.getState();
	 
	  if(state.equals(0)) {
	   organEntity.setState(1);
	   map.put("status", "0");
	   map.put("message","禁用成功");
   
	  }
	  else if(state.equals(1)){
	   organEntity.setState(0);
	   map.put("status", "0");
	   map.put("message","启用成功");
	  }
	  organService.update(organEntity);
	  return map;
	 }
	 
	 //修改前查询
	 @RequestMapping(value = "findById")
		@ResponseBody
		public Map<String,Object> findById(HttpServletRequest res,HttpServletResponse req,@RequestParam(name="id")String id) {

			Map<String,Object> map = new HashMap<String,Object>();
			OrganEntity organ = organService.findBId(id);
			
			if(organ!=null) {
				map.put("status", "0");//查询数据成功
				map.put("data", organ);
			}else {
				map.put("status", "1");//查询数据失败
			}
			return map;
		}
	 //机构模糊查询
	 @RequestMapping(value = "findByName")
		@ResponseBody
		public Map<String,Object> findByName(HttpServletRequest res,HttpServletResponse req,@RequestParam(name="name")String name,@RequestParam(name="superId")String superId,@RequestParam(name="page")Integer page,
				@RequestParam(name="size")Integer size) {
			
			Map<String,Object> map = new HashMap<String,Object>();
			Pageable pageable = new PageRequest(page-1,size);
			Page<OrganEntity> organList= organService.findListByName(name, superId, pageable);
			map.put("status", "0");//成功
			map.put("message","查询成功");
			map.put("data", organList);
			return map;
		}
	 
	
}
