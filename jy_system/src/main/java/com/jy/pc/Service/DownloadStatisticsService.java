package com.jy.pc.Service;

import java.util.Map;

import com.jy.pc.Entity.DownloadStatisticsEntity;

public interface DownloadStatisticsService {
	
	
	public void addOrUpdate(DownloadStatisticsEntity downloadStatisticsEntity);
	
	public DownloadStatisticsEntity queryByDate(String date);
	
	public Map<String,Object> queryStatistics();

}
