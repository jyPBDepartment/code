package com.jy.pc.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.pc.Entity.AgriculturalEntity;
import com.jy.pc.Entity.FarmworkEntity;
import com.jy.pc.Entity.PostInfoEntity;
import com.jy.pc.Service.AgriculturalService;
import com.jy.pc.Service.FarmworkService;
import com.jy.pc.Service.PostInfoService;

@Controller
@RequestMapping(value="main")
@ResponseBody
public class MainController {

	@Autowired
	private FarmworkService farmworkService;
	@Autowired
	private PostInfoService invitationService;
	@Autowired
	private AgriculturalService agriculturalService;
	
	@RequestMapping(value = "initEchart")
	public Map<String,Object> findSum(HttpServletRequest res,HttpServletResponse req) {

		Map<String,Object> map = new HashMap<String,Object>();//接收数据容器
		List<FarmworkEntity> farmworkEntity = farmworkService.findSum();//查询所有数据方法
		List<PostInfoEntity> invitationEntity =invitationService.findInva();
		List<AgriculturalEntity> AgriculturalEntity =agriculturalService.findAgrSum();
		map.put("state", "0");
		map.put("message", "查询成功");
		map.put("farmwork", farmworkEntity.size());
		map.put("invation", invitationEntity.size());
		map.put("agricultural", AgriculturalEntity.size());
		return map;
	}

}
