package com.jy.pc.Scheduling;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jy.pc.DAO.GrainPricesDAO;
import com.jy.pc.Entity.GrainPricesEntity;
import com.jy.pc.Utils.DbLogUtil;
import com.mysql.cj.util.StringUtils;

/**
 * 执行粮食买卖相关定时任务
 * 
 * @author admin
 *
 */
@Component
@EnableScheduling
public class GrainScheduling {
	@Autowired
	private GrainPricesDAO grainPricesDAO;
	@Autowired
	private DbLogUtil logger;

	/**
	 * 更新粮价走势图数据，每天下午6点自动更新 1.若当天已存在数据则不做处理 2.若数据库中无历史记录则不做处理
	 * 3.若当天无数据但数据库中有历史记录时，复制最新一条数据存入数据库
	 */
	@Transactional
	@Scheduled(cron = "0 00 18 * * ? ")
	//@Scheduled(cron = "0 51 11 * * ? ")
	public void grainPrices() {
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
		logger.initCustomizedLog("粮价走势图", "自动更新", "自动任务开始");
		System.out.println("自动任务开始");
		List<GrainPricesEntity> list = grainPricesDAO.findInfoByDate(now);
		try {
			if (list.isEmpty()) {
				// 今日未更新粮价走势数据
				GrainPricesEntity newestInfo = grainPricesDAO.findNewestInfo();
				if (newestInfo == null) {
					// 粮价数据未初始化或最新粮价数据异常
					logger.initCustomizedLog("粮价走势图", "自动更新", "历史数据异常，请联系系统管理员");
					System.out.println("历史数据异常，请联系系统管理员");
				} else {
					// 复制前一天的数据存入数据库中
					grainPricesDAO.copyToToday(calendar.getTime());
					logger.initCustomizedLog("粮价走势图", "自动更新", "更新数据");
					System.out.println("更新中......");
				}
			} else {
				// 今日已更新粮价走势数据
				logger.initCustomizedLog("粮价走势图", "自动更新", "今日已手动更新粮价走势数据");
				System.out.println("今日已手动更新粮价走势数据");
			}
		} catch (Exception e) {
			logger.initCustomizedLog("粮价走势图", "自动更新", "定时器异常: " + e.getMessage());
		} finally {
			logger.initCustomizedLog("粮价走势图", "自动更新", "自动任务结束");
		}
	}
}
