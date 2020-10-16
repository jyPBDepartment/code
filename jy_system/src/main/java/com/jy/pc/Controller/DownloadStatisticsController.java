package com.jy.pc.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.pc.Entity.DownloadStatisticsEntity;
import com.jy.pc.Service.DownloadStatisticsService;

@Controller
@RequestMapping(value="downloadStatistics")
@ResponseBody
public class DownloadStatisticsController {

	@Autowired
	private DownloadStatisticsService downloadStatisticsService;
	
	@RequestMapping(value = "/add")
	public Map<String,Object> addOrUpdate(@RequestParam("type") String type) {
		Map<String,Object> map = new HashMap<String,Object>();
		int hnStatistics = 0;
		int zgStatistics = 0;

		DownloadStatisticsEntity downloadStatisticsEntity = new DownloadStatisticsEntity();
		if (type.equals("hn")) {
			hnStatistics = hnStatistics + 1;
		} else {
			zgStatistics = zgStatistics + 1;
		}

		downloadStatisticsEntity.setCreateDate(new Date());
		downloadStatisticsEntity.setHnStatistics(hnStatistics);
		downloadStatisticsEntity.setZgStatistics(zgStatistics);
		downloadStatisticsService.addOrUpdate(downloadStatisticsEntity);
		map.put("status", "200");
		return map;
	}
	
	@RequestMapping(value="/queryStatistics")
	public Map<String,Object> queryStatistics() {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap = downloadStatisticsService.queryStatistics();
		map.put("status", "200");
		if(!returnMap.isEmpty()) {
			map.put("hnCount", returnMap.get("hnCount"));
			map.put("zgCount", returnMap.get("zgCount"));
		}else {
			map.put("hnCount", 0);
			map.put("zgCount", 0);
		}
		
		return map;
	}
}
