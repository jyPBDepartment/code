package com.jy.pc.Service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.AgriculturalEntity;

public interface AgriculturalService {
	// 农服关联Echart图
	public List<AgriculturalEntity> findAgrSum();

	//添加
	public AgriculturalEntity save(AgriculturalEntity agriculturalEntity);

	// 农服findById
	public AgriculturalEntity findBId(String id);

	// 根据id查询农服信息详情
	public AgriculturalEntity findId(String id);

	// 根据id查询我的农服，农机，粮食买卖信息详情（h5）
	public AgriculturalEntity findMineId(String id);

	// 农服分页与模糊查询
	public Page<AgriculturalEntity> findListByName(String type, String name, String status, Pageable pageable);

	// 农服状态修改
	public AgriculturalEntity update(AgriculturalEntity agriculturalEntity);

	// 农服查询标题名称
	public List<AgriculturalEntity> findListByAgrName(String name);

	// 农服查询最近三条
	public List<AgriculturalEntity> findListByTime();

	// 不同状态加载不同的发布
	public List<AgriculturalEntity> findStatusPass(String status);

	// 获取农服预约信息
	public List<AgriculturalEntity> findAppointment();

	// 农服审核
	AgriculturalEntity audit(AgriculturalEntity agriculturalEntity, boolean result);

	// 根据类别获取信息
	List<AgriculturalEntity> findListByType(String type);

	// 根据类别获取信息（前三条）
	List<AgriculturalEntity> findListByTime(String type);

	Page<AgriculturalEntity> findListByType(String type, Pageable pageable);

	// 搜索发布信息中农服信息(标题名称)
	Page<List<Map<String, Object>>> findAgriInfo(String name, String type, String transactionTypeCode,
			String transactionCategoryCode, String identityCode,String address, String sort,String userId,Pageable pageable);

	// 搜索发布信息中农服信息（类型、类别）
	Page<AgriculturalEntity> findAgriType(String transactionTypeCode, String transactionCategoryCode,
			String identityCode, String type, Pageable pageable);

	// 搜索我的发布信息（接口,标题名称）
	Page<AgriculturalEntity> findMyPublication(String status, String type, String userId, Pageable pageable);
	
	// 农服修改
	public AgriculturalEntity updateAgr(AgriculturalEntity agriculturalEntity,String id,String[] addItem,String[] deleteItem,String transactionTypeCode,String transactionCategoryCode,String[] deleteSurplus);
	// 农服添加
	public AgriculturalEntity saveAgr(String[] addItem,AgriculturalEntity agriculturalEntity);
	// 删除
	public void delete(String id);

}
