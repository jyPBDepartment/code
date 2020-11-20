package com.jy.pc.Service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.EduManualInfoEntity;

/**
 * 手册Service
 */
public interface EduManualInfoService {

	// 分页与模糊查询
	public Page<EduManualInfoEntity> findListByName(String title, String createBy, String vocationId, String labelId,
			Pageable pageable);

	// 通过id查询
	public EduManualInfoEntity findId(String id);

	// 添加
	public EduManualInfoEntity save(EduManualInfoEntity eduManualInfoEntity);

	// 修改
	public void update(EduManualInfoEntity eduManualInfoEntity);

	// 删除
	public void delete(String id);

	// 调整状态
	void enable(EduManualInfoEntity eduManualInfoEntity, boolean result);

	// app我的收藏/学习记录
	List<EduManualInfoEntity> getManualListByUserId(String userId, int isCollection) throws ServiceException;

	// 移动端-首页-热门课程加载
	public List<EduManualInfoEntity> getListByReading();

	//手册列表接口
	public Page<EduManualInfoEntity> findManualByName(String title, String createBy, String vocationId, String labelId,
			Pageable pageable);
	
	//通过职业类别查询手册
	public List<EduManualInfoEntity> findManualVocationId(String vocationId);
}
