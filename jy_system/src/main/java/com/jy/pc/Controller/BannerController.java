package com.jy.pc.Controller;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

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
import com.jy.pc.Entity.ArticleEntity;
import com.jy.pc.Entity.BannerEntity;
import com.jy.pc.Entity.RoleEntity;
import com.jy.pc.Service.BannerService;

@Controller
@RequestMapping(value = "/banner")
public class BannerController {

	@Autowired
	private BannerService bannerService;

	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		String s = res.getParameter("bannerEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		
		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		sdf.format(date);
		BannerEntity bannerEntity = jsonObject.toJavaObject(BannerEntity.class);
		bannerEntity.setStatus("1");
		bannerEntity.setCreateDate(date);
		
		System.out.println("当前时间："+date.toString());
		try {
			bannerService.save(bannerEntity);
			map.put("status", 1);// 成功
			map.put("message", "保存成功");
		} catch (Exception e) {
			map.put("status", 0);// 失败
			map.put("message", "保存失败");
		}
		return map;
	}


	@RequestMapping(value = "/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam("id") String id, HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			bannerService.delete(id);
			map.put("status", 1);// 成功
			map.put("message", "删除成功");
		} catch (Exception e) {
			map.put("status", 0);// 失败
			map.put("message", "删除失败");
		}
		return map;
	}

	@RequestMapping(value = "/findPageInfo")
	@ResponseBody
	public Map<String, Object> findPageInfo(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();

		Pageable pageable = new PageRequest(page - 1, size);

		Page<BannerEntity> bannerList = bannerService.findPageInfo(name, pageable);

		map.put("status", "0");// 成功
		map.put("message", "查询成功");

		map.put("data", bannerList);
		return map;
	}

	@RequestMapping(value = "/findInfoById")
	@ResponseBody
	public Map<String, Object> findInfoById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		BannerEntity bannerEntity = bannerService.findInfoById(id);
		map.put("state", "0");
		map.put("data", bannerEntity);
		return map;
	}
	
	@RequestMapping(value = "/changeStatus")
	@ResponseBody
	public Map<String, Object> changeStatus(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id,@RequestParam(name = "status") String status) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		sdf.setTimeZone(TimeZone.getTimeZone("GMT-8"));
//		sdf.format(date);
		
		//获取当前系统时间，格式为yyyy-MM-dd HH:mm:ss
		TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8");
		TimeZone.setDefault(tz);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		ParsePosition pos = new ParsePosition(0);
		Date now =new Date();
		String tCurrentdate = df.format(now);
		Date tCurrentdates = df.parse(tCurrentdate, pos);
		BannerEntity bannerEntity = bannerService.findInfoById(id);
		bannerEntity.setStatus(status);
		bannerEntity.setUpdateDate(tCurrentdates);
		bannerService.save(bannerEntity);
		map.put("status", "0");// 成功
		map.put("message", "操作成功");
		return map;
	}
	
	
	
	//查询所有
		@RequestMapping(value = "/findAll")
		@ResponseBody
		public Map<String,Object> findAll(HttpServletRequest res,HttpServletResponse req) {

			Map<String,Object> map = new HashMap<String,Object>();
			List<BannerEntity> bannerList = bannerService.findId();
			map.put("status", "0");
			map.put("message", "查询成功");
			map.put("data", bannerList);
			return map;
		}
	

}
