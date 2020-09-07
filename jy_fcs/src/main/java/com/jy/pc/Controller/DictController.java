package com.jy.pc.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jy.pc.Entity.DictDataEntity;
import com.jy.pc.Entity.DictTypeEntity;
import com.jy.pc.Service.DictService;

@Controller
@ResponseBody
@RequestMapping(value = "/dict")
public class DictController {
	@Autowired
	private DictService dictService;

	// 根据id获取字典类型信息
	@RequestMapping(value = "/findTypeById")
	public Map<String, Object> findTypeById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		DictTypeEntity type = dictService.findTypeById(id);
		map.put("state", "0");
		map.put("message", "查询成功");
		map.put("data", type);
		return map;
	}

	// 根据id获取字典键值信息
	@RequestMapping(value = "/findDataById")
	public Map<String, Object> findDataById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		DictDataEntity data = dictService.findDataById(id);
		map.put("state", "0");
		map.put("message", "查询成功");
		map.put("data", data);
		return map;
	}

	// 修改字典键值
	@RequestMapping(value = "/updateData")
	public Map<String, String> updateData(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("dataEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		Date date = new Date();
		DictDataEntity dictDataEntity = jsonObject.toJavaObject(DictDataEntity.class);
		dictDataEntity.setCreateDate(date);
		dictDataEntity.setStatus("0");
		try {
			dictService.updateData(dictDataEntity);
			map.put("state", "0");
			map.put("message", "修改成功");
		} catch (Exception e) {
			map.put("state", "1");
			map.put("message", "修改失败");
		}
		return map;
	}

	// 修改字典类型
	@RequestMapping(value = "/updateType")
	public Map<String, String> updateType(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("typeEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		Date date = new Date();
		DictTypeEntity dictTypeEntity = jsonObject.toJavaObject(DictTypeEntity.class);
		dictTypeEntity.setCreateDate(date);
		dictTypeEntity.setStatus("0");
		try {
			dictService.updateType(dictTypeEntity);
			map.put("state", "0");
			map.put("message", "修改成功");
		} catch (Exception e) {
			map.put("state", "1");
			map.put("message", "修改失败");
		}
		return map;
	}

	// 根据type清空键值
	@RequestMapping(value = "/removeData")
	public Map<String, Object> removeData(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "type") String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		dictService.removeData(type);
		map.put("state", "0");
		map.put("message", "清空成功");
		return map;
	}

	// 根据ID删除字典类别
	@RequestMapping(value = "/deleteDictType")
	public Map<String, Object> deleteDictType(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		dictService.deleteType(id);
		map.put("state", "0");
		map.put("message", "删除成功");
		return map;
	}

	// 根据ID删除字典键值
	@RequestMapping(value = "/deleteDictData")
	public Map<String, Object> deleteDictData(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		dictService.deleteData(id);
		map.put("state", "0");
		map.put("message", "删除成功");
		return map;
	}

	// 根据字典类型获取对应所有键值
	@RequestMapping(value = "/findDataByType")
	public Map<String, Object> findDataByType(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "type") String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<DictDataEntity> dataList = dictService.findListByType(type);
		map.put("state", "0");
		map.put("message", "添加成功");
		map.put("data", dataList);
		return map;
	}

	// 新增字典键值
	@RequestMapping(value = "/addData")
	public Map<String, String> addData(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("dataEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		Date date = new Date();
		DictDataEntity dictDataEntity = jsonObject.toJavaObject(DictDataEntity.class);
		dictDataEntity.setCreateDate(date);
		dictDataEntity.setStatus("0");
		try {
			dictService.insertData(dictDataEntity);
			map.put("state", "0");
			map.put("message", "添加成功");
		} catch (Exception e) {
			map.put("state", "1");
			map.put("message", "添加失败");
		}
		return map;
	}

	// 新增字典类型
	@RequestMapping(value = "/addType")
	public Map<String, String> addType(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("typeEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		Date date = new Date();
		DictTypeEntity dictTypeEntity = jsonObject.toJavaObject(DictTypeEntity.class);
		dictTypeEntity.setCreateDate(date);
		dictTypeEntity.setStatus("0");
		try {
			dictService.insertType(dictTypeEntity);
			map.put("state", "0");
			map.put("message", "添加成功");
		} catch (Exception e) {
			map.put("state", "1");
			map.put("message", "添加失败");
		}
		return map;
	}

	// 模糊查询与分页
	@RequestMapping(value = "/findTypeByName")
	public Map<String, Object> findTypeByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "status") String status,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<DictTypeEntity> typePage = dictService.findPageByName(name, status, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", typePage);
		return map;
	}

	// 启用禁用字典类型
	@RequestMapping(value = "/enableType")
	public Map<String, String> enableType(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") String status, @RequestParam(name = "id") String id) {
		Map<String, String> map = new HashMap<String, String>();
		DictTypeEntity type = dictService.findTypeById(id);
		Date date = new Date();
		boolean result = "0".equals(status) ? true : false;
		type.setStatus(status);
		type.setUpdateDate(date);
		if ("0".equals(status)) {
			map.put("state", "0");
			map.put("message", "启用成功");
		}
		if ("1".equals(status)) {
			map.put("state", "1");
			map.put("message", "禁用成功");
		}
		dictService.enableType(type, result);
		return map;
	}

	// 启用禁用字典键值
	@RequestMapping(value = "/enableData")
	public Map<String, String> enableData(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") String status, @RequestParam(name = "id") String id) {
		Map<String, String> map = new HashMap<String, String>();
		DictDataEntity data = dictService.findDataById(id);
		Date date = new Date();
		boolean result = "0".equals(status) ? true : false;
		data.setStatus(status);
		data.setUpdateDate(date);
		if ("0".equals(status)) {
			map.put("state", "0");
			map.put("message", "启用成功");
		}
		if ("1".equals(status)) {
			map.put("state", "1");
			map.put("message", "禁用成功");
		}
		dictService.enableData(data, result);
		return map;
	}
}
