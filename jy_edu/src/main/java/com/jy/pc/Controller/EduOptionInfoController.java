package com.jy.pc.Controller;

import java.util.ArrayList;
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

import com.jy.pc.Entity.EduOptionInfoEntity;
import com.jy.pc.Service.EduOptionInfoService;

/**
 * 选项信息表Controller
 */
@Controller
@RequestMapping(value = "optionInfo")
public class EduOptionInfoController {
	@Autowired
	private EduOptionInfoService eduOptionInfoService;

	// 通过问题id查询
	@RequestMapping(value = "/findByQuestionId")
	@ResponseBody
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "idArray") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String[] array = id.split(",");
		List<String> list = new ArrayList<String>();
		for(int i=0;i<array.length;i++) {
			list.add(array[i]);
		}
		List<EduOptionInfoEntity> optionInfo = eduOptionInfoService.findQuestionIds(list);
		map.put("state", "0");
		map.put("data", optionInfo);
		return map;
	}

}
