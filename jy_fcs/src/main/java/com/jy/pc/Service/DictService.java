package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.DictDataEntity;
import com.jy.pc.Entity.DictTypeEntity;

public interface DictService {
	public void insertType(DictTypeEntity dictType);

	public void insertData(DictDataEntity dictData);

	public void enableType(DictTypeEntity dictType, boolean result);

	public void enableData(DictDataEntity dictData, boolean result);
	
	public void updateType(DictTypeEntity dictType);
	
	public void updateData(DictDataEntity dictData);
	
	public Page<DictTypeEntity> findPageByName(String name, String status, Pageable pageable); 
	
	public List<DictDataEntity> findListByType(String type);
	
	public void deleteType(String id);
	
	public void deleteData(String id);
	
	public void removeData(String type);
	
	public DictTypeEntity findTypeById(String id);
	
	public DictDataEntity findDataById(String id);
}
