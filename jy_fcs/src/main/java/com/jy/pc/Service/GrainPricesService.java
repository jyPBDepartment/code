package com.jy.pc.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.jy.pc.Entity.GrainAreaInfoEntity;
import com.jy.pc.Entity.GrainPricesEntity;

public interface GrainPricesService {

	public GrainPricesEntity saveOrUpdate(GrainPricesEntity grainPricesEntity);

	public void delete(String id);

	public GrainPricesEntity findInfoById(String id);

	// 根据参数查询 分页
	public Page<GrainPricesEntity> findPageByParam(String priceDefinedType, String province, String city,
			String district, Pageable pageable);

	public List<GrainPricesEntity> findListByType(String type,String province,String city,String district);

	public List<GrainPricesEntity> findInfoByDate(Date now);

	// 解析EXCEL并存入数据库
	public Map<String, Object> importExcel(MultipartFile uploadFile, boolean isExcel2003, String createBy)
			throws IOException;

	public List<GrainAreaInfoEntity> getAreaByLevel(String parentId, int level);
}
