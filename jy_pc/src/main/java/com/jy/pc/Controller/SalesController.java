package com.jy.pc.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.jy.pc.Entity.LinkEntity;
import com.jy.pc.Entity.OrganEntity;
import com.jy.pc.Entity.SalesEntity;
import com.jy.pc.Service.LinkService;
import com.jy.pc.Service.OrganService;
import com.jy.pc.Service.SalesService;

@Controller
@RequestMapping(value="/sales")
public class SalesController {
	@Autowired
	private SalesService salesService;
	@Autowired
	private LinkService linkService;
	@Autowired
	private OrganService organService;
	//业务员添加
		@RequestMapping(value="/save")
		@ResponseBody
		public Map<String, String> save(HttpServletRequest res,HttpServletResponse req) {
			
			String s = res.getParameter("salesEntity");
			JSONObject jsonObject = JSONObject.parseObject(s);
			SalesEntity salesEntity = jsonObject.toJavaObject(SalesEntity.class);
			//自动获取系统时间
			
			String createTime = DateFormat.getDateTimeInstance().format(new Date());
			salesEntity.setCreateTime(createTime);
			salesEntity.setUpdateTime(createTime);
			
			SalesEntity sales = salesService.save(salesEntity);
			LinkEntity linkEntity = new LinkEntity();
			OrganEntity organEntity = new OrganEntity();
			organEntity=organService.findBId(sales.getOrganId());
			sales.setOrganName(organEntity.getName());
			linkEntity.setOrganId(sales.getOrganId());
			linkEntity.setSalesId(salesEntity.getId());
			linkService.save(linkEntity);
			Map<String, String> map = new HashMap<String, String>();
			map.put("message","添加成功");
			
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
			
			SalesEntity sales = salesService.update(salesEntity);
			//修改关联的机构ID
			OrganEntity organEntity = new OrganEntity();
			organEntity=organService.findBId(sales.getOrganId());
			salesEntity.setOrganName(organEntity.getName());
			salesService.update(salesEntity);
			//修改关联表
			LinkEntity linkEntity = new LinkEntity();
			linkEntity = linkService.findBId(salesEntity.getId());
			linkEntity.setOrganId(salesEntity.getOrganId());
			linkService.update(linkEntity);
			
			map.put("status", "0");
			map.put("message","修改成功");
			return map;
		}
		//业务员删除
		@RequestMapping(value = "delete")
		@ResponseBody
		public Map<String,Object> delete(HttpServletRequest res,HttpServletResponse req,@RequestParam(name="id")String id) {
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			SalesEntity salesEntity = new SalesEntity();
			salesEntity = salesService.findBId(id);
			LinkEntity linkEntity = new LinkEntity();
			linkEntity = linkService.findBId(salesEntity.getId());
			linkService.delete(linkEntity.getId());
			salesService.delete(id);

			map.put("status", "0");
			map.put("message", "删除成功");
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
				map.put("status", "0");//成功
				map.put("message","查询成功");
				map.put("data", salesList);
				
				return map;
			}
}
