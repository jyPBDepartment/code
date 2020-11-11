package com.jy.pc.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.pc.Entity.EduExamPaperInfoEntity;
import com.jy.pc.Entity.EduUserExamEntity;
import com.jy.pc.Entity.EduVocationInfoEntity;
import com.jy.pc.Service.EduExamPaperInfoService;
import com.jy.pc.Service.EduUserExamService;
import com.jy.pc.Service.EduVocationInfoService;

/**
 * 	学习考试控制层
 * @author Stephen
 * 
 * */
@Controller
@RequestMapping("/studyExam")
public class EduStudyExamController {
	
	@Autowired
	private EduVocationInfoService eduVocationInfoService;
	@Autowired
	private EduExamPaperInfoService eduExamPaperInfoService;
	@Autowired
	private EduUserExamService eduUserExamService;
	
	@RequestMapping("/findStudyExamInfo")
	@ResponseBody
	public Map<String, Object> findStudyExamInfo(String userId) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<EduVocationInfoEntity> eduVocationList = eduVocationInfoService.findVocationId();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < eduVocationList.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<EduExamPaperInfoEntity> eduExamPaperList = eduExamPaperInfoService
					.getExamListByVocationId(eduVocationList.get(i).getId());
			int count = 0;
			map.put("id", eduVocationList.get(i).getId());
			map.put("name", eduVocationList.get(i).getName());
			
			if(eduExamPaperList.size()>0) {
				for (int j = 0; j < eduExamPaperList.size(); j++) {
					EduUserExamEntity eduUserExamEntity = eduUserExamService.isPass(userId,
							eduExamPaperList.get(j).getId());
					if (eduUserExamEntity != null) {
						if (eduUserExamEntity.getIsPass() == 1) {
							count = count + 1;
						}
					} else {
						count = 0;
					}
				}
				if (count == eduExamPaperList.size()) {
					map.put("isPass", 1);
				} else {
					map.put("isPass", 0);
				}
			}else {
				map.put("isPass", 0);
			}
			
			list.add(map);
		}
		returnMap.put("state", 0);
		returnMap.put("data", list);
		
		return returnMap;
	}
}
