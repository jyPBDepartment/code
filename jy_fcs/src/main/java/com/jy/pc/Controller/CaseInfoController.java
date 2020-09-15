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
import com.jy.pc.Entity.CaseInfoEntity;
import com.jy.pc.Entity.ClassificationEntity;
import com.jy.pc.Entity.KeyWordEntity;
import com.jy.pc.Enum.ClassificationEnum;
import com.jy.pc.Service.CaseInfoService;
import com.jy.pc.Service.ClassificationService;
import com.jy.pc.Service.KeyWordService;
import com.jy.pc.Utils.Aes;

@Controller
@ResponseBody
@RequestMapping(value = "/caseInfo")
public class CaseInfoController {

	@Autowired
	private CaseInfoService caseInfoService;
	@Autowired
	private ClassificationService classificationService;
	@Autowired
	private KeyWordService keyWordService;

	// 接口 -- 根据id获取信息
	@RequestMapping(value = "findLatestCaseInfoById")
	public Map<String, Object> findLatestCaseInfoById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		CaseInfoEntity caseInfo = caseInfoService.findBId(id);
		if (caseInfo != null) {
			map.put("state", "0");// 查询数据成功
			map.put("message", "查询成功");
			map.put("data", caseInfo);
		} else {
			map.put("state", "1");// 查询数据失败
			map.put("message", "查询失败");
		}
		return map;
	}

	// 接口 -- 查询病虫害列表（分页）
	@RequestMapping(value = "/findCasePage")
	public Map<String, Object> findCasePage(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "cropsTypeCode", defaultValue = "") String cropsTypeCode,
			@RequestParam(name = "dipTypeCode", defaultValue = "") String dipTypeCode,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<CaseInfoEntity> caseIn = caseInfoService.findPage(name, cropsTypeCode, dipTypeCode, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", caseIn);
		return map;
	}

	// 接口 -- 获取病虫害关键词
	@RequestMapping(value = "/findCaseKey")
	public Map<String, Object> findCaseKey(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<KeyWordEntity> keyList = keyWordService.findListByClass(ClassificationEnum.KEYWORD_CASEINFO.getCode());
		if (keyList != null) {
			map.put("state", "0");
			map.put("data", keyList);
		} else {
			map.put("state", "1");
		}
		return map;
	}

	// 添加

	@RequestMapping(value = "/save")

	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Aes aes = new Aes();
		String s = "";
		Map<String, String> map = new HashMap<String, String>();
		String temp = res.getParameter("caseInfoEntity");
		try {
			s = aes.desEncrypt(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.parseObject(s);
		Date date = new Date();
		CaseInfoEntity caseInfoEntity = jsonObject.toJavaObject(CaseInfoEntity.class);
		caseInfoEntity.setCreateDate(date);
		caseInfoEntity.setAuditStatus("0");

		ClassificationEntity classificationEntity = classificationService.findBId(caseInfoEntity.getClassiCode());
		caseInfoEntity.setCropsTypeCode(classificationEntity.getName());

		ClassificationEntity classification = classificationService.findBId(caseInfoEntity.getClassiDipCode());
		caseInfoEntity.setDipTypeCode(classification.getName());

		String keywords = jsonObject.getString("keys");
		try {
			caseInfoService.saveWithKeyword(caseInfoEntity, keywords);
			map.put("state", "0");

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
		CaseInfoEntity caseInfo = caseInfoService.findBId(id);
		if (caseInfo != null) {
			map.put("state", "0");// 查询数据成功
			map.put("data", caseInfo);
		} else {
			map.put("state", "1");// 查询数据失败
		}
		return map;
	}

	// 修改
	@RequestMapping(value = "update")
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {
		Aes aes = new Aes();
		String s = "";
		Map<String, String> map = new HashMap<String, String>();
		String temp = res.getParameter("caseInfoEntity");
		try {
			s = aes.desEncrypt(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.parseObject(s);
		Date date = new Date();
		CaseInfoEntity caseInfoEntity = jsonObject.toJavaObject(CaseInfoEntity.class);
		caseInfoEntity.setUpdateDate(date);

		ClassificationEntity classificationEntity = classificationService.findBId(caseInfoEntity.getClassiCode());
		caseInfoEntity.setCropsTypeCode(classificationEntity.getName());

		ClassificationEntity classification = classificationService.findBId(caseInfoEntity.getClassiDipCode());
		caseInfoEntity.setDipTypeCode(classification.getName());
		String keywords = jsonObject.getString("keys");
		caseInfoService.updateWithKeyword(caseInfoEntity, keywords);
		map.put("message", "修改成功");
		return map;
	}

	// 删除
	@RequestMapping(value = "delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();// 接收数据容器
		caseInfoService.delete(id);
		map.put("state", "0");
		map.put("message", "删除成功");
		return map;
	}

	// 模糊查询与分页
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "auditStatus") String auditStatus,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
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

	/**
	 * 接口
	 */
	// 查询所有病虫害信息的最新3条记录
	@RequestMapping(value = "findLatestCaseInfo")
	public Map<String, Object> findCaseInfo(HttpServletRequest res, HttpServletResponse req) {

		Map<String, Object> map = new HashMap<String, Object>();// 接收数据容器
		List<CaseInfoEntity> caseInfoEntity = caseInfoService.findCaseInfo();
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", caseInfoEntity);
		return map;
	}

	// 修改前查询
	@RequestMapping(value = "findCaseId")
	public Map<String, Object> findCaseId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		CaseInfoEntity caseInfo = caseInfoService.findBId(id);
		if (caseInfo != null) {
			map.put("state", "0");// 查询数据成功
			map.put("message", "查询成功");
			map.put("data", caseInfo);
		} else {
			map.put("state", "1");// 查询数据失败
			map.put("message", "查询失败");
		}
		return map;
	}

	// 关键字搜索病虫害信息
	@RequestMapping(value = "/findCaseInfoByKey")
	public Map<String, Object> findCaseInfoByKey(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<CaseInfoEntity> caseInfo = caseInfoService.findCaseInfoByKey(name);
		for (int i = 0; i < caseInfo.size(); i++) {
			map.put("data", caseInfo);
		}
		map.put("state", "0");
		map.put("message", "查询成功");
		return map;
	}
}
