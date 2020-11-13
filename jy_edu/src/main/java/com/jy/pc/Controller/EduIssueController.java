package com.jy.pc.Controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jy.pc.Entity.EduCertificateInfoEntity;
import com.jy.pc.Entity.EduIssueInfoEntity;
import com.jy.pc.Entity.EduVocationInfoEntity;
import com.jy.pc.Service.EduCertificateInfoService;
import com.jy.pc.Service.EduIssueService;
import com.jy.pc.Service.EduUserExamService;
import com.jy.pc.Service.EduVocationInfoService;

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
	@Autowired
	private EduVocationInfoService eduVocationInfoService;
	@Autowired
	private EduUserExamService eduUserExamService;
	@Autowired
	private EduCertificateInfoService eduCertificateInfoService;

	// 后台管理系统柱状图-考试分布人数
	@RequestMapping(value = "/ExamNumEchart")
	@ResponseBody
	public Map<String, Object> ExamNumEchart(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取所有职业类别
		List<EduVocationInfoEntity> vocations = eduVocationInfoService.findVocationId();
		String[] xAxis = new String[vocations.size()];
		int[] yAxis = new int[vocations.size()];
		int totalNum;
		for (int i = 0; i < vocations.size(); i++) {
			xAxis[i] = vocations.get(i).getName();
			// 查询该职业类别下的证书通过率
			totalNum = eduUserExamService.findByVocation(vocations.get(i).getId(), "").size();
			yAxis[i] = totalNum;
		}
		map.put("state", "0");
		map.put("message", "申请成功");
		map.put("xAxis", xAxis);
		map.put("yAxis", yAxis);
		return map;
	}

	// 后台管理系统柱状图-考试通过率
	@RequestMapping(value = "/passEchart")
	@ResponseBody
	public Map<String, Object> passEchart(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取所有职业类别
		List<EduVocationInfoEntity> vocations = eduVocationInfoService.findVocationId();
		String[] xAxis = new String[vocations.size()];
		String[] yAxis = new String[vocations.size()];
		int totalNum;
		int passNum;
		for (int i = 0; i < vocations.size(); i++) {
			xAxis[i] = vocations.get(i).getName();
			// 查询该职业类别下的证书通过率
			totalNum = eduUserExamService.findByVocation(vocations.get(i).getId(), "").size();
			passNum = eduUserExamService.findByVocation(vocations.get(i).getId(), "1").size();
			BigDecimal denominator = new BigDecimal(totalNum);
			BigDecimal numerator = new BigDecimal(passNum);
			if (denominator.compareTo(BigDecimal.ZERO) != 0) {
				yAxis[i] = numerator.divide(denominator).toString();
			}
		}
		map.put("state", "0");
		map.put("message", "申请成功");
		map.put("xAxis", xAxis);
		map.put("yAxis", yAxis);
		return map;
	}

	// 移动端 -- 证书申请
	@RequestMapping(value = "/applyCertificate")
	@ResponseBody
	public Map<String, String> applyCertificate(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "userId") String userId, @RequestParam(name = "userName") String userName,
			@RequestParam(name = "userTel") String userTel, @RequestParam(name = "userCard") String userCard,
			@RequestParam(name = "vocationId") String vocationId) {
		Map<String, String> map = new HashMap<String, String>();
		EduCertificateInfoEntity cfa = new EduCertificateInfoEntity();
		cfa.setId(eduCertificateInfoService.findInfobyVocation(vocationId).getId());
		EduIssueInfoEntity entity = new EduIssueInfoEntity();
		entity.setUserId(userId);
		entity.setUserName(userName);
		entity.setUserCard(userCard);
		entity.setUserTel(userTel);
		entity.setVocationId(vocationId);
		Date date = new Date();
		entity.setCreateDate(date);
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
