package com.jy.pc.Service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.GrainPricesDAO;
import com.jy.pc.Entity.GrainPricesEntity;
import com.jy.pc.Service.GrainPricesService;

@Service
@Transactional
public class GrainPricesServiceImpl implements GrainPricesService {

	@Autowired
	private GrainPricesDAO grainPricesDAO;

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
	public Page<GrainPricesEntity> findPageByParam(String priceDefinedType,Pageable pageable){
		return grainPricesDAO.findPageByParam(priceDefinedType,pageable);
	}
	

}
