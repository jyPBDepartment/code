package com.jy.pc.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jy.pc.Entity.AccountInfoEntity;
import com.jy.pc.Entity.AccountPowerInfoEntity;
import com.jy.pc.Entity.PostInfoEntity;
import com.jy.pc.Service.PostInfoService;

@Controller
@RequestMapping(value = "/invitation")
@ResponseBody
public class PostInfoController {
	@Autowired
	private PostInfoService invitstionServie;
	// 查询 分页
		@RequestMapping(value = "/findByName")
		public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
				@RequestParam(name = "name") String name, @RequestParam(name = "createUser") String createUser,
				@RequestParam(name = "page") Integer page,
				@RequestParam(name = "size") Integer size) {

			Map<String, Object> map = new HashMap<String, Object>();
			Pageable pageable = new PageRequest(page - 1, size);
			Page<PostInfoEntity> invitationList = invitstionServie.findListByName(name, createUser, pageable);
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
			PostInfoEntity invitationEntity = invitstionServie.findId(id);
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
				@RequestParam(name = "auditStatus") String auditStatus, @RequestParam(name = "id") String id) {

			Map<String, String> map = new HashMap<String, String>();
			PostInfoEntity invitationEntity = invitstionServie.findId(id);
			invitationEntity.setAuditStatus(auditStatus);
			invitationEntity.getAuditStatus();
			Date date = new Date();
			if (auditStatus.equals("0")) {
				invitationEntity.setAuditStatus("0");
				invitationEntity.setUpdateDate(date);
				map.put("status", "0");
				map.put("message", "启用成功");
			} else if (auditStatus.equals("1")) {
				invitationEntity.setAuditStatus("1");
				invitationEntity.setUpdateDate(date);
				map.put("status", "1");
				map.put("message", "禁用成功");
			}
			invitstionServie.update(invitationEntity);
			return map;
		}

}
