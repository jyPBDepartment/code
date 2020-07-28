package com.jy.pc.Controller;

import java.util.Date;
import java.util.HashMap;
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
import com.jy.pc.Entity.CaseInfoEntity;
import com.jy.pc.Entity.ClassificationEntity;
import com.jy.pc.Service.CaseInfoService;
import com.jy.pc.Service.ClassificationService;

@Controller
@ResponseBody
@RequestMapping(value="/caseInfo")
public class CaseInfoController {

	@Autowired
	private CaseInfoService caseInfoService;
	@Autowired
	private ClassificationService classificationService;
	
	//添加
//	@RequestMapping(value = "save")
//	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
//		Map<String, String> map = new HashMap<String, String>();
//		String s = res.getParameter("caseInfoEntity");
//		JSONObject jsonObject = JSONObject.parseObject(s);
//		Date date = new Date();
//		CaseInfoEntity caseInfoEntity = jsonObject.toJavaObject(CaseInfoEntity.class);
//		caseInfoEntity.setCreateDate(date);
//		caseInfoService.save(caseInfoEntity);
//		map.put("message", "添加成功");
//		return map;
//	}
	// 农服添加

	@RequestMapping(value = "/save")

	public Map<String, Object> save(HttpServletRequest res, HttpServletResponse req) {

		String s = res.getParameter("caseInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);

		Date date = new Date();

		CaseInfoEntity caseInfoEntity = jsonObject.toJavaObject(CaseInfoEntity.class);
		caseInfoEntity.setCreateDate(date);

		ClassificationEntity classificationEntity = new ClassificationEntity();
		classificationEntity = classificationService.findBId(caseInfoEntity.getClassiCode());
		caseInfoEntity.setCropsTypeCode(classificationEntity.getCode());
		
		classificationEntity = classificationService.findBId(caseInfoEntity.getDipTypeCode());
		caseInfoEntity.setDipTypeCode(classificationEntity.getCode());

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			caseInfoService.save(caseInfoEntity);
			map.put("state", "0");
			map.put("data", caseInfoEntity);
			map.put("message", "添加成功");
		} catch (Exception e) {
			map.put("state", "1");
			map.put("message", "添加失败");
		}
		return map;
	}
	// 修改前查询
	@RequestMapping(value = "findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		CaseInfoEntity caseInfo= caseInfoService.findBId(id);
		if (caseInfo != null) {
			map.put("state", "0");// 查询数据成功
			map.put("data", caseInfo);
		} else {
			map.put("state", "1");// 查询数据失败
		}
		return map;
	}
	// 分类修改
	@RequestMapping(value = "update")
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("caseInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		Date date = new Date();
		CaseInfoEntity caseInfoEntity = jsonObject.toJavaObject(CaseInfoEntity.class);
		caseInfoEntity.setUpdateDate(date);
		caseInfoService.update(caseInfoEntity);
		map.put("message", "修改成功");
		return map;
	}

	// 分类删除
	@RequestMapping(value = "delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();// 接收数据容器
		caseInfoService.delete(id);
		map.put("state", "0");
		map.put("message", "删除成功");
		return map;
	}
	

	// 分类模糊查询与分页
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name,@RequestParam(name = "auditStatus") String auditStatus, 
			@RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<CaseInfoEntity> caseIn = caseInfoService.findListByName(name, auditStatus, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", caseIn);
		return map;
	}

	// 启用禁用
	@RequestMapping(value = "/enable")
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "auditStatus") String auditStatus, @RequestParam(name = "id") String id) {
		Map<String, String> map = new HashMap<String, String>();
		CaseInfoEntity caseInfoEntity = caseInfoService.findBId(id);
		caseInfoEntity.setAuditStatus(auditStatus);
		caseInfoEntity.getAuditStatus();
		if (auditStatus.equals("1")) {
			caseInfoEntity.setAuditStatus("1");
			map.put("state", "0");
			map.put("message", "启用成功");
		} else if (auditStatus.equals("0")) {
			caseInfoEntity.setAuditStatus("0");
			map.put("state", "0");
			map.put("message", "禁用成功");
		}
		caseInfoService.update(caseInfoEntity);
		return map;
	}
}
