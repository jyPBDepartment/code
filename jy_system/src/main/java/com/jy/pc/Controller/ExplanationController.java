package com.jy.pc.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.jy.pc.Entity.ExplanationEntity;
import com.jy.pc.Service.ExplanstionService;

@Controller
@RequestMapping(value = "/appointment")
@ResponseBody
public class ExplanationController {
	@Autowired
	private ExplanstionService explanstionService;

	// 添加
	@RequestMapping(value = "/add")
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("explanstionEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		ExplanationEntity explanstionEntity = jsonObject.toJavaObject(ExplanationEntity.class);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );// 格式化时间		
//		String time=DateFormat.getDateTimeInstance().format(new Date());
//		try {
//			explanstionEntity.setCreateDate(sdf.parse(time));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		Date date = new Date();
		explanstionEntity.setCreateDate(date);
		explanstionService.save(explanstionEntity);
		map.put("message", "提交成功");
		return map;
	}

	//查询
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name,@RequestParam(name = "phoneNum") String phoneNum,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<ExplanationEntity> ExplanationList = explanstionService.findListByName(name, phoneNum, pageable);
		map.put("status", "0");
		map.put("message", "查询成功");
		map.put("data", ExplanationList);
		return map;
	}
}
