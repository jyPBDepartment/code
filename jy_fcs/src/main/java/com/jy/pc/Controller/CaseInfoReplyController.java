package com.jy.pc.Controller;

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

import com.jy.pc.Entity.CaseInfoReplyEntity;
import com.jy.pc.Service.CaseInfoReplyService;
//看图识病回复表 Controller
@Controller
@RequestMapping(value = "/caseInfoReply")
public class CaseInfoReplyController {
	@Autowired
	private CaseInfoReplyService caseInfoReplyService;
	
	//分页查询
	@RequestMapping(value = "/findReplyList")
	@ResponseBody
	public Map<String, Object> findCasePage(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "replyContent", defaultValue = "") String replyContent,
			@RequestParam(name = "replyUserName", defaultValue = "") String replyUserName,
			@RequestParam(name = "commentId", defaultValue = "") String commentId,
			@RequestParam(name = "page") Integer page,@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();

		Pageable pageable = new PageRequest(page - 1, size);
		try {
			Page<List<Map<String, Object>>> caseInfoReplyList = caseInfoReplyService.findPageByParam(name, replyUserName, replyContent, commentId, pageable);
			map.put("code", "200");// 成功
			map.put("message", "查询成功");
			map.put("data", caseInfoReplyList);
		} catch (Exception e) {
			map.put("code", "500");
			map.put("message", "查询失败");
		}
		return map;
	}

	// 新增
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req,CaseInfoReplyEntity caseInfoReplyEntity) {
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			caseInfoReplyService.save(caseInfoReplyEntity);
			map.put("code", "200");
			map.put("message", "添加成功");
		} catch (Exception e) {
			map.put("code", "500");
			map.put("message", "添加失败");
		}
		return map;
	}
	
	//删除
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			caseInfoReplyService.delete(id);
			map.put("code", "200");
			map.put("message", "删除成功");
		} catch (Exception e) {
			map.put("code", "500");
			map.put("message", "删除失败");
			e.printStackTrace();
		}
		return map;
	}
	
	// 修改状态
	@RequestMapping(value = "/enable")
	@ResponseBody
	public Map<String, String> setSelect(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") Integer status, @RequestParam(name = "id") String id) {
		Map<String, String> map = new HashMap<String, String>();
		CaseInfoReplyEntity caseInfoReplyEntity = caseInfoReplyService.findId(id);
		caseInfoReplyEntity.setStatus(status);
		boolean result = true;
		if (status.equals(1)) {
			caseInfoReplyEntity.setStatus(1);
			map.put("code", "200");
			map.put("message", "禁用成功");
		} else if (status.equals(0)) {
			caseInfoReplyEntity.setStatus(status);
			map.put("code", "200");
			map.put("message", "启用成功");
			result = false;
		}
		caseInfoReplyService.updateEnable(caseInfoReplyEntity, result);
		return map;
	}


}
