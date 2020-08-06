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
import com.jy.pc.Entity.PowerInfoEntity;
import com.jy.pc.Service.PowerInfoService;

@Controller
@ResponseBody
@RequestMapping(value = "powerInfo")
public class PowerInfoController {

	@Autowired
	private PowerInfoService powerInfoService;

	// 权限添加
	@RequestMapping(value = "save")
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("powerInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		Date date = new Date();
		PowerInfoEntity powerInfoEntity = jsonObject.toJavaObject(PowerInfoEntity.class);
		powerInfoEntity.setCreateDate(date);
		powerInfoService.save(powerInfoEntity);
		map.put("message", "添加成功");
		return map;
	}

	// 权限修改前查询
	@RequestMapping(value = "findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		PowerInfoEntity powerInfo = powerInfoService.findBId(id);
		if (powerInfo != null) {
			map.put("status", "0");// 查询数据成功
			map.put("data", powerInfo);
		} else {
			map.put("status", "1");// 查询数据失败
		}
		return map;
	}

	// 下拉列表显示
	@RequestMapping(value = "findAll")
	public Map<String, Object> findAll(HttpServletRequest res, HttpServletResponse req) {

		Map<String, Object> map = new HashMap<String, Object>();// 接收数据容器
		List<PowerInfoEntity> power = powerInfoService.findAll(); // 查询所有数据方法
		map.put("status", "0");
		map.put("message", "查询成功");
		map.put("data", power);
		return map;
	}

	// 权限修改
	@RequestMapping(value = "update")
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req,@RequestParam(name = "subJurCode") String subJurCode) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("powerInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		Date date = new Date();
		PowerInfoEntity powerInfoEntity = jsonObject.toJavaObject(PowerInfoEntity.class);
		powerInfoEntity.setUpdateDate(date);
		if(powerInfoService.findSubJurCode(subJurCode)) {
			powerInfoEntity.setSubJurCode("");
			map.put("status", "1");
			map.put("message", "该菜单下存在子菜单，不可修改上级分类！");
			powerInfoService.update(powerInfoEntity);
		}else {
			powerInfoService.update(powerInfoEntity);
			map.put("status", "0");
			map.put("message", "该菜单下不存在子菜单，可修改上级分类！");
		}		
//		map.put("message", "修改成功");
		return map;
	}

	// 关联验证删除
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<PowerInfoEntity> powers = powerInfoService.findAccountLink();
		PowerInfoEntity powerEntity = powerInfoService.findBId(id);
		for (int i = 0; i < powers.size(); i++) {
			PowerInfoEntity a = powers.get(i);
			a.getId();
			if(powerInfoService.findSubJurCode(id)) {
				map.put("status", "1");
				map.put("message", "该菜单下存在子菜单，不可直接删除！");
				return map;
			}
			else if (powerEntity.getId().equals(a.getId())) {
				powerInfoService.delete(id);
				map.put("status", "0");
				map.put("message", "删除成功");
			} else {

			}
		}
		if (powers.size() <= 0) {
		}
		return map;
	}

	// 权限模糊查询与分页
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "jurName") String jurName, @RequestParam(name = "jurCode") String jurCode,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<PowerInfoEntity> powerList = powerInfoService.findListByName(jurName, jurCode, pageable);
		map.put("status", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", powerList);
		return map;
	}

	// 启用禁用
	@RequestMapping(value = "/enable")
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "auditStatus") String auditStatus, @RequestParam(name = "id") String id) {
		Map<String, String> map = new HashMap<String, String>();
		PowerInfoEntity powerInfoEntity = powerInfoService.findBId(id);
		powerInfoEntity.setAuditStatus(auditStatus);
		powerInfoEntity.getAuditStatus();
		if (auditStatus.equals("1")) {
			powerInfoEntity.setAuditStatus("1");
			map.put("state", "0");
			map.put("message", "启用成功");
		} else if (auditStatus.equals("0")) {
			powerInfoEntity.setAuditStatus("0");
			map.put("state", "0");
			map.put("message", "禁用成功");
		}
		powerInfoService.update(powerInfoEntity);
		return map;
	}

	/**
	 * 查询上级权限编码列表
	 * 
	 */
	@RequestMapping(value = "/findSubPowerList")
	public Map<String, Object> findSubPowerList(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<PowerInfoEntity> power = powerInfoService.findSubPowerList();
		
		 if (power != null) {
			map.put("status", "0");
			map.put("data", power);
		} else {
			map.put("status", "3");
		}
		return map;
	}

	// 账户关联
	@RequestMapping(value = "findCount")
	public Map<String, Object> findCount(HttpServletRequest res, HttpServletResponse req) {

		Map<String, Object> map = new HashMap<String, Object>();// 接收数据容器
		List<PowerInfoEntity> powerIn = powerInfoService.findCount(); // 查询所有数据方法
		map.put("status", "0");
		map.put("message", "查询成功");
		map.put("data", powerIn);
		return map;
	}
}
