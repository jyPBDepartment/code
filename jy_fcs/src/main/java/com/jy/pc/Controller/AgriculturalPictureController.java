package com.jy.pc.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.pc.Entity.AgriculturalEntity;
import com.jy.pc.Entity.AgriculturalPictureEntity;
import com.jy.pc.Service.AgriculturalPictureService;
import com.jy.pc.Service.AgriculturalService;

@Controller
@ResponseBody
@RequestMapping(value = "agriculturalPicture")

public class AgriculturalPictureController {
	@Autowired
	private AgriculturalService agriculturalService;
	@Autowired
	private AgriculturalPictureService agriculturalPictureService;
	
	
	@RequestMapping(value = "findId")
	public Map<String, Object> findId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		AgriculturalEntity agricultural = agriculturalService.findId(id);
		List<AgriculturalPictureEntity> agrPic = agriculturalPictureService.findByAgrId(id);
			map.put("state", "0");// 查询数据成功
			map.put("data", agricultural);
			map.put("dataPic", agrPic);
			return map;
	}

}
