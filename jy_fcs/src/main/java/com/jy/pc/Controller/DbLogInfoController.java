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

import com.jy.pc.Entity.DbLogInfoEntity;
import com.jy.pc.Service.DbLogInfoService;

@Controller
@RequestMapping(value = "/dbLogInfo")
@ResponseBody
public class DbLogInfoController {
	@Autowired
	private DbLogInfoService dbLogInfoService;

	// 查询日志 分页
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "module") String module, @RequestParam(name = "action") String action,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<DbLogInfoEntity> logList = dbLogInfoService.findListByContent(module, action, pageable);
		map.put("status", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", logList);
		return map;
	}


	// 日志删除
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		dbLogInfoService.delete(id);
		map.put("status", "0");
		map.put("message", "删除成功");
		return map;
	}

}
