package com.jy.pc.Service.Impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.jy.pc.DAO.GrainAreaInfoDao;
import com.jy.pc.DAO.GrainPricesDAO;
import com.jy.pc.Entity.GrainAreaInfoEntity;
import com.jy.pc.Entity.GrainPricesEntity;
import com.jy.pc.Service.GrainPricesService;
import com.jy.pc.Utils.ImportExeclUtil;

@Service
@Transactional
public class GrainPricesServiceImpl implements GrainPricesService {

	@Autowired
	private GrainPricesDAO grainPricesDAO;
	@Autowired
	private GrainAreaInfoDao grainAreaDao;

	public GrainPricesEntity saveOrUpdate(GrainPricesEntity grainPricesEntity) {
		return grainPricesDAO.save(grainPricesEntity);
	}

	public void delete(String id) {
		grainPricesDAO.deleteById(id);

	}

	public GrainPricesEntity findInfoById(String id) {
		return grainPricesDAO.findInfoById(id);
	}

	// 根据参数查询 分页
	public Page<GrainPricesEntity> findPageByParam(String priceDefinedType, String province, String city,
			String district, Pageable pageable) {
		return grainPricesDAO.findPageByParam(priceDefinedType, province, city, district, pageable);
	}

	// 根据参数查询 分页
	public List<GrainPricesEntity> findListByType(String type,String province,String city,String district) {

		int queryParam = 0;
		switch (type) {
		case "0":
			queryParam = 7;
			break;
		case "1":
			queryParam = 30;
			break;
		default:
			break;
		}
		return grainPricesDAO.findListByArea(province, city, district,queryParam);
		//return grainPricesDAO.findListByType(queryParam);
	}

	public List<GrainPricesEntity> findInfoByDate(Date now) {
		return grainPricesDAO.findInfoByDate(now);
	}

	// 读取excel数据并写入数据库
	@Transactional
	@Override
	public Map<String, Object> importExcel(MultipartFile uploadFile, boolean isExcel2003, String createBy)
			throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		int priceAddCount = 0;
		int priceUpdateCount = 0;
		int areaCount = 0;
		ImportExeclUtil util = new ImportExeclUtil();
		List<List<String>> dataList = util.read(uploadFile, isExcel2003);
		GrainPricesEntity priceEntity;
		GrainAreaInfoEntity provinceEntity = null;
		GrainAreaInfoEntity cityEntity = null;
		GrainAreaInfoEntity districtEntity = null;
		String provinceId = "";
		String cityId = "";
		String districtId = "";
		String province = "";// 省
		String city = "";// 市
		String district = "";// 区
		String min = "";// 最低价
		String max = "";// 最高价
		// 默认此条记录为县粮价信息
		int level = 3;
		String areaId;
		Date now = new Date();
		try {
			// 遍历获取每行数据
			for (int i = 0; i < dataList.size(); i++) {
				List<String> rowList = new ArrayList<String>();
				rowList = dataList.get(i);
				// 遍历获取每列数据
				for (int j = 0; j < rowList.size(); j++) {
					province = rowList.get(0);// 省
					city = rowList.get(1);// 市
					district = rowList.get(2);// 区
					min = new BigDecimal(rowList.get(3)).divide(new BigDecimal(2000), 2, RoundingMode.HALF_UP)
							.toString();// 最低价
					max = new BigDecimal(rowList.get(4)).divide(new BigDecimal(2000), 2, RoundingMode.HALF_UP)
							.toString();// 最高价
					if (StringUtils.isEmpty(city) && StringUtils.isEmpty(district) && StringUtils.isEmpty(province)) {
						result.put("code", 500);
						result.put("msg", "第" + i + "行数据省市县均为空");
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return result;
					}
					if (StringUtils.isEmpty(min) || StringUtils.isEmpty(max)) {
						result.put("code", 500);
						result.put("msg", "第" + i + "行数据最低粮价或最高粮价为空");
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return result;
					}
					if (!min.matches("-?[0-9]+.?[0-9]*") || !max.matches("-?[0-9]+.?[0-9]*")) {
						result.put("code", 500);
						result.put("msg", "第" + i + "行粮价数据格式异常[" + min + "/" + max + "]");
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return result;
					}
				}
				/*判断该数据层级,是省市县哪一层级*/
				if (StringUtils.isNotBlank(province) && StringUtils.isNotBlank(city)
						&& StringUtils.isNotBlank(district)) {
					level = 3;
				}
				if (StringUtils.isNotBlank(province) && StringUtils.isNotBlank(city) && StringUtils.isEmpty(district)) {
					level = 2;
				}
				if (StringUtils.isNotBlank(province) && StringUtils.isEmpty(city) && StringUtils.isEmpty(district)) {
					level = 1;
				}
				provinceEntity = grainAreaDao.findInfoByName(province, "");
				if (provinceEntity == null) {
					//若该条记录中"省份"未存入数据库
					GrainAreaInfoEntity grand = new GrainAreaInfoEntity();
					grand.setName(province);
					grand.setImportDate(now);
					grand.setLevel(1);
					grainAreaDao.save(grand);
					provinceId = grand.getId();
					areaCount++;
				} else {
					provinceId = provinceEntity.getId();
				}
				cityEntity = grainAreaDao.findInfoByName(city, provinceId);
				if (cityEntity == null) {
					if (StringUtils.isNotBlank(city)) {
						//若该条记录"市"未存入数据库,且"市"不为空
						GrainAreaInfoEntity parent = new GrainAreaInfoEntity();
						parent.setName(city);
						parent.setImportDate(now);
						parent.setLevel(2);
						parent.setParentId(provinceId);
						grainAreaDao.save(parent);
						cityId = parent.getId();
						areaCount++;
					}
				} else {
					cityId = cityEntity.getId();
				}
				districtEntity = grainAreaDao.findInfoByName(district, cityId);
				if (districtEntity == null) {
					if (StringUtils.isNotBlank(city) && StringUtils.isNotBlank(district)) {
						//若该条记录"县"未存入数据库,且"县"不为空
						GrainAreaInfoEntity area = new GrainAreaInfoEntity();
						area.setName(district);
						area.setImportDate(now);
						area.setLevel(3);
						area.setParentId(cityId);
						grainAreaDao.save(area);
						districtId = area.getId();
						areaCount++;
					}
				}
				switch (level) {
				case 1:
					areaId = provinceId;
					break;
				case 2:
					areaId = cityId;
					break;
				case 3:
					areaId = districtId;
					break;
				default:
					areaId = "";
					break;
				}
				// 存在地区数据,需要新增粮价信息,先查询当日地区粮价
				priceEntity = grainPricesDAO.findInfoByArea(province, city, district);
				if (priceEntity == null) {
					GrainPricesEntity newPrice = new GrainPricesEntity();
					newPrice.setCreateDate(now);
					newPrice.setMinPrice(min);
					newPrice.setMaxPrice(max);
					newPrice.setPriceDate(now);
					newPrice.setProvince(provinceId);
					newPrice.setCity(cityId);
					newPrice.setDistrict(districtId);
					newPrice.setPriceDefinedType("0");
					newPrice.setAreaId(areaId);
					newPrice.setCreateUser(createBy);
					grainPricesDAO.save(newPrice);
					priceAddCount++;
				} else {
					if (!min.equals(priceEntity.getMinPrice()) || !max.equals(priceEntity.getMaxPrice())) {
						priceEntity.setMinPrice(min);
						priceEntity.setMaxPrice(max);
						grainPricesDAO.save(priceEntity);
						priceUpdateCount++;
					}
				}
			}
			result.put("code", "200");
			result.put("msg", "导入成功");
			result.put("priceAddCount", priceAddCount);
			result.put("priceUpdateCount", priceUpdateCount);
			result.put("areaCount", areaCount);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.put("code", "500");
			result.put("msg", e.getMessage());
		}
		return result;
	}

	@Override
	public List<GrainAreaInfoEntity> getAreaByLevel(String parentId, int level) {
		List<GrainAreaInfoEntity> result = new ArrayList<GrainAreaInfoEntity>();
		if (StringUtils.isEmpty(parentId)) {
			result = grainAreaDao.getProvince();
		} else {
			result = grainAreaDao.findInfoByParentId(parentId);
		}
		return result;
	}

}
