package com.jy.pc.Service.impl;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.DownloadStatisticsDao;
import com.jy.pc.Entity.DownloadStatisticsEntity;
import com.jy.pc.Service.DownloadStatisticsService;

@Service
@Transactional
public class DownloadStatisticsServiceImpl implements DownloadStatisticsService{
	
	@Autowired
	private DownloadStatisticsDao downloadStatisticsDao;
	
	public void addOrUpdate(DownloadStatisticsEntity downloadStatisticsEntity) {
		downloadStatisticsDao.saveAndFlush(downloadStatisticsEntity);
	}
	
	public DownloadStatisticsEntity queryByDate(String date) {
		return downloadStatisticsDao.queryByDate(date);
	}
	
	public Map<String,Object> queryStatistics(){
		return downloadStatisticsDao.queryStatistics();
	}
}
