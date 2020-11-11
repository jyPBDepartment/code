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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jy.pc.Entity.EduCertificateInfoEntity;
import com.jy.pc.Entity.EduIssueInfoEntity;
import com.jy.pc.Entity.EduVocationInfoEntity;
import com.jy.pc.Service.EduIssueService;

/**
 * 证书管理相关
 * 
 * @author admin
 *
 */
@RequestMapping(value = "issue")
@RestController
public class EduIssueController {
	@Autowired
	private EduIssueService eduIssueService;

	// 移动端 -- 证书申请
	@RequestMapping(value = "/applyCertificate")
	@ResponseBody
	public Map<String, String> applyCertificate(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "userId") String userId, @RequestParam(name = "userName") String userName,
			@RequestParam(name = "certificateId") String certificateId) {
		Map<String, String> map = new HashMap<String, String>();
		EduIssueInfoEntity entity = new EduIssueInfoEntity();
		EduCertificateInfoEntity cfa = new EduCertificateInfoEntity();
		entity.setUserId(userId);
		entity.setUserName(userName);
		cfa.setId(certificateId);
		Date date = new Date();
		entity.setCreateDate(date);
		entity.setCertificate(cfa);
		eduIssueService.save(entity);
		map.put("state", "0");
		map.put("message", "申请成功");
		return map;
	}

	// 分页条件查询
	@RequestMapping(value = "/findPage")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "createBy") String createBy, @RequestParam(name = "name") String name,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "status") String status,
			@RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<EduIssueInfoEntity> list = eduIssueService.findListByParam(name, status, createBy, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", list);
		return map;
	}

	// 根据id返回详情信息
	@RequestMapping(value = "/findById")
	@ResponseBody
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		EduIssueInfoEntity entity = eduIssueService.findInfobyId(id);
		if (entity != null) {
			map.put("state", "0");
			map.put("data", entity);
		} else {
			map.put("state", "1");
		}
		return map;
	}

	@RequestMapping(value = "/findMgtPage")
	public Map<String, Object> findMgtPage(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "userName") String userName, @RequestParam(name = "card") String card,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "vocationId") String vocationId,
			@RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<EduIssueInfoEntity> list = eduIssueService.findMgtByParam(userName, card, vocationId, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", list);
		return map;
	}
}
