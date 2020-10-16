package com.jy.pc.DAO;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.DownloadStatisticsEntity;

public interface DownloadStatisticsDao extends JpaRepository<DownloadStatisticsEntity, String>{
	
	@Query(value="select * from w_download_statistics t where create_date =:date",nativeQuery = true)
	public DownloadStatisticsEntity queryByDate(@Param("date") String date);
	
	@Query(value="select sum(t.hn_statistics) as hnCount,sum(t.zg_statistics) as zgCount from w_download_statistics t ",nativeQuery = true)
	public Map<String,Object> queryStatistics();

}
