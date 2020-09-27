package com.jy.pc.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.pc.Entity.GrainPricesHistoryEntity;
import com.jy.pc.Service.GrainPricesHistoryService;

@Controller
@RequestMapping(value="/grainPricesHistory")
public class GrainPricesHistoryController {

	@Autowired
	private GrainPricesHistoryService grainPricesHistoryService;
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String,Object> delete(HttpServletRequest res, HttpServletResponse req,@RequestParam("id") String id){
		
		Map<String,Object> map =new HashMap<String,Object>();
		grainPricesHistoryService.delete(id);
		map.put("status", "0");
		map.put("message", "删除成功");
		return map;
		
	}
	
	@RequestMapping(value = "/findById")
	@ResponseBody
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam("id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		GrainPricesHistoryEntity grainPricesHistoryEntity = grainPricesHistoryService.findInfoById(id);
		map.put("status", "0");
		map.put("message", "查询成功");
		map.put("data", grainPricesHistoryEntity);
		return map;

	}
	
	// 根据参数查询 分页
	@RequestMapping(value = "/findPageByParam")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "phone") String phone,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "auditStatus") String auditStatus,
			@RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
//		Page<AccountInfoEntity> accountInfoList = accountInfoService.findListByName(name, phone, auditStatus, pageable);
		map.put("status", "0");// 成功
		map.put("message", "查询成功");
//		map.put("data", accountInfoList);
		return map;
	}
}
