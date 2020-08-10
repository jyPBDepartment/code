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
import com.jy.pc.Entity.PostInfoEntity;
import com.jy.pc.Service.PostInfoService;

@Controller
@RequestMapping(value = "/postInfo")
@ResponseBody
public class PostInfoController {
	@Autowired
	private PostInfoService postInfoService;

	// 查询 分页
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "createUser") String createUser,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<PostInfoEntity> invitationList = postInfoService.findListByName(name, createUser, pageable);
		map.put("status", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", invitationList);
		return map;
	}

	// 条件查询
	@RequestMapping(value = "/findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		PostInfoEntity invitationEntity = postInfoService.findId(id);
		if (invitationEntity != null) {
			map.put("status", "0");
			map.put("data", invitationEntity);
		} else {
			map.put("status", "1");
		}
		return map;
	}

	// 启用/禁用
	@RequestMapping(value = "/enable")
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") String status, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		PostInfoEntity invitationEntity = postInfoService.findId(id);
		invitationEntity.setStatus(status);
		invitationEntity.getStatus();
		Date date = new Date();
		if (status.equals("0")) {
			invitationEntity.setStatus("0");
			invitationEntity.setUpdateDate(date);
			map.put("status", "0");
			map.put("message", "启用成功");
		} else if (status.equals("1")) {
			invitationEntity.setStatus("1");
			invitationEntity.setUpdateDate(date);
			map.put("status", "1");
			map.put("message", "禁用成功");
		}
		postInfoService.update(invitationEntity);
		return map;
	}

	// 审核通过
	@RequestMapping(value = "/passPostInfo")
	public Map<String, String> passPostInfo(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("postInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		PostInfoEntity postInfoEntity = jsonObject.toJavaObject(PostInfoEntity.class);
		postInfoEntity.setAuditStatus("2");
		postInfoService.update(postInfoEntity);
		map.put("state", "0");
		map.put("message", "审核通过");
		return map;
	}

	// 审核拒绝
	@RequestMapping(value = "/refusePostInfo")
	public Map<String, String> refusePostInfo(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("postInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		PostInfoEntity postInfoEntity = jsonObject.toJavaObject(PostInfoEntity.class);
		postInfoEntity.setAuditStatus("3");
		postInfoService.update(postInfoEntity);
		map.put("state", "0");
		map.put("message", "审核驳回");
		return map;
	}

}