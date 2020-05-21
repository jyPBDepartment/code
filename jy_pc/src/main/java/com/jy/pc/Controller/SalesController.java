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
import com.jy.pc.Entity.SalesEntity;
import com.jy.pc.Service.SalesService;

@Controller
@RequestMapping(value="/sales")
public class SalesController {
	@Autowired
	private SalesService salesService;
	//业务员添加
		@RequestMapping(value="/save")
		@ResponseBody
		public Map<String, String> save(HttpServletRequest res,HttpServletResponse req,@RequestParam(name="name")String name,@RequestParam(name="phone")String phone,@RequestParam(name="organId")String organId,@RequestParam(name="context")String context,@RequestParam(name="state")int state) {
			
			System.out.println("添加");
			SalesEntity salesEntity = new SalesEntity();
			
			salesEntity.setName(name);
			salesEntity.setPhone(phone);
			salesEntity.setOrganId(organId);
			
			String createTime = DateFormat.getDateTimeInstance().format(new Date());
			salesEntity.setCreateTime(createTime);
			salesEntity.setUpdateTime(createTime);
			salesEntity.setContext(context);
			salesEntity.setState(state);
			salesService.save(salesEntity);
			Map<String, String> map = new HashMap<String, String>();
			map.put("message","添加成功");
			
			
		
			req.setHeader("Access-Control-Allow-Origin", "*");
			req.setHeader("Cache-Control", "no-cache");
			return map;
		}
		//修改前查询
		 @RequestMapping(value = "findById")
			@ResponseBody
			public Map<String,Object> findById(HttpServletRequest res,HttpServletResponse req,@RequestParam(name="id")String id) {

				Map<String,Object> map = new HashMap<String,Object>();
				SalesEntity sales = salesService.findBId(id);
				
				if(sales!=null) {
					map.put("status", "0");//查询数据成功
					map.put("data", sales);
				}else {
					map.put("status", "1");//查询数据失败
				}
				
				
				req.setHeader("Access-Control-Allow-Origin", "*");
				req.setHeader("Cache-Control", "no-cache");
				return map;
			}
		//业务员修改
		@RequestMapping(value = "update")
		@ResponseBody
		public Map<String, String> update(HttpServletRequest res,HttpServletResponse req) {

			Map<String, String> map = new HashMap<String, String>();
			String s = res.getParameter("salesEntity");			
			JSONObject jsonObject = JSONObject.parseObject(s);
			SalesEntity salesEntity = jsonObject.toJavaObject(SalesEntity.class);
			String updateTime = DateFormat.getDateTimeInstance().format(new Date());
			
			salesEntity.setUpdateTime(updateTime);
			System.out.println("打印："+salesEntity.getId());
			salesService.update(salesEntity);
			map.put("message","修改成功");
			
			req.setHeader("Access-Control-Allow-Origin", "*");
			req.setHeader("Cache-Control", "no-cache");
			return map;
		}
		//业务员删除
		@RequestMapping(value = "delete")
		@ResponseBody
		public Map<String,Object> delete(HttpServletRequest res,HttpServletResponse req,@RequestParam(name="id")String id) {
			Map<String,Object> map = new HashMap<String,Object>();//接收数据容器
			salesService.delete(id);
			
			map.put("status", "0");
			map.put("message", "删除成功");
			
			req.setHeader("Access-Control-Allow-Origin", "*");
			req.setHeader("Cache-Control", "no-cache");
			return map;
		}
		//业务员详情查询
		@RequestMapping(value = "findAll")
		@ResponseBody
		public Map<String,Object> findAll(HttpServletRequest res,HttpServletResponse req) {

			Map<String,Object> map = new HashMap<String,Object>();//接收数据容器
			List<SalesEntity> salesList = salesService.findAll();//查询所有数据方法
			map.put("status", "0");
			map.put("message", "查询成功");
			
			map.put("data", salesList);
			req.setHeader("Access-Control-Allow-Origin", "*");
			req.setHeader("Cache-Control", "no-cache");
			return map;
		}
		
		 //业务员模糊查询
		 @RequestMapping(value = "findByName")
			@ResponseBody
			public Map<String,Object> findByName(HttpServletRequest res,HttpServletResponse req,@RequestParam(name="name")String name,@RequestParam(name="phone")String phone,@RequestParam(name="page")Integer page,
					@RequestParam(name="size")Integer size) {
				
				Map<String,Object> map = new HashMap<String,Object>();
				Pageable pageable = new PageRequest(page-1,size);
				
				Page<SalesEntity> salesList= salesService.findListByName(name, phone, pageable);
//				List<SalesEntity> salesList = salesService.findListByName(name, phone);
				
				
				map.put("status", "0");//成功
				map.put("message","查询成功");
				
				map.put("data", salesList);
				
				req.setHeader("Access-Control-Allow-Origin", "*");
				req.setHeader("Cache-Control", "no-cache");
				
				return map;
			}
}
