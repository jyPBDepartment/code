package com.jy.pc.Scheduling;



import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


/**
 * 线下课程相关定时任务
 * 
 * @author admin
 *
 */
@Component
@EnableScheduling
public class LessonScheduling {
	//获取未结束、生效、课程开始日期为今天的线下课程
	//遍历集合，修改其状态为
}
