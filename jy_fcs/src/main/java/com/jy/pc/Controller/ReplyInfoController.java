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
import com.jy.pc.Entity.CommentReplyInfoEntity;
import com.jy.pc.Service.CommentReplyInfoService;

/**
 * 评论回复相关接口
 * 
 * @author admin
 *
 */
@Controller
@RequestMapping(value = "/replyInfo")
@ResponseBody
public class ReplyInfoController {
	@Autowired
	private CommentReplyInfoService commentReplyInfoService;

	// 查询 分页
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findListByContent(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "content") String content, @RequestParam(name = "user") String user,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<CommentReplyInfoEntity> replyList = commentReplyInfoService.findListByContent(content, user,pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", replyList);
		return map;
	}

	// 回复添加
	@RequestMapping(value = "/add")
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("commentReplyInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		CommentReplyInfoEntity commentReplyInfoEntity = jsonObject.toJavaObject(CommentReplyInfoEntity.class);
		Date date = new Date();
		commentReplyInfoEntity.setReplyDate(date);
		commentReplyInfoEntity.setStatus("1");
		commentReplyInfoService.save(commentReplyInfoEntity);
		map.put("status", "0");
		map.put("message", "添加成功");
		return map;
	}

	// 回复修改
	@RequestMapping(value = "/update")
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("commentReplyInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		CommentReplyInfoEntity commentReplyInfoEntity = jsonObject.toJavaObject(CommentReplyInfoEntity.class);
		commentReplyInfoService.update(commentReplyInfoEntity);
		map.put("status", "0");
		map.put("message", "修改成功");
		return map;
	}

	// 回复删除
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		commentReplyInfoService.delete(id);
		map.put("status", "0");
		map.put("message", "删除成功");
		return map;
	}

	// 回复启用 OR 禁用
	@RequestMapping(value = "/enable")
	public Map<String, String> enable(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") String status, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		CommentReplyInfoEntity commentReplyInfoEntity = commentReplyInfoService.findId(id);
		if ("1".contentEquals(status)) {
			map.put("state", "0");
			map.put("message", "禁用成功");
		}
		if ("0".contentEquals(status)) {
			map.put("state", "0");
			map.put("message", "启用成功");
		}
		commentReplyInfoEntity.setStatus(status);
		commentReplyInfoService.update(commentReplyInfoEntity);
		return map;
	}

	// 条件查询
	@RequestMapping(value = "/findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		CommentReplyInfoEntity commentReplyInfoEntity = commentReplyInfoService.findId(id);
		if (commentReplyInfoEntity != null) {
			map.put("status", "0");
			map.put("data", commentReplyInfoEntity);
		} else {
			map.put("status", "1");
		}
		return map;
	}
}
