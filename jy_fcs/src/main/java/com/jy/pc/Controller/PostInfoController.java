package com.jy.pc.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.jy.pc.Entity.ClassificationEntity;
import com.jy.pc.Entity.PostInfoEntity;
import com.jy.pc.Enum.ClassificationEnum;
import com.jy.pc.Service.ClassificationService;
import com.jy.pc.Service.PostInfoService;

//帖子管理
@Controller
@RequestMapping(value = "/postInfo")
@ResponseBody
public class PostInfoController {
	@Autowired
	private PostInfoService postInfoService;
	@Autowired
	private ClassificationService classificationService;


	@RequestMapping(value = "/getPostType")
	public Map<String, Object> getPostType(HttpServletRequest res, HttpServletResponse req) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<ClassificationEntity> classList = classificationService
				.findClassByCode(ClassificationEnum.POSTINFO_TYPE.getCode());
		map.put("status", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", classList);
		return map;
	}

	/**
	 * 加载所有帖子、评论、回复列表信息
	 * 
	 * @param postType 圈子类型
	 * @param page     页码
	 * @param size     每页加载数量
	 * @return consumer: note:
	 */
	@RequestMapping(value = "/findAllPostInfo")
	public Map<String, Object> findAllPostInfo(HttpServletRequest res, HttpServletResponse req, String postType,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<PostInfoEntity> invitationList = postInfoService.findListWithSub(postType, pageable);
		map.put("status", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", invitationList);
		return map;
	}

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
<<<<<<< HEAD
		Date date = new Date();
=======
>>>>>>> 6cf23403c7b79c0d4109b5b487a69cc247f91d7c
		boolean result = true;
		invitationEntity.setStatus(status);
		if (status.equals("0")) {
<<<<<<< HEAD
			invitationEntity.setUpdateDate(date);
			map.put("status", "0");
			map.put("message", "启用成功");
		} else if (status.equals("1")) {
			invitationEntity.setUpdateDate(date);
=======
			map.put("status", "0");
			map.put("message", "启用成功");
		}
		if (status.equals("1")) {
>>>>>>> 6cf23403c7b79c0d4109b5b487a69cc247f91d7c
			map.put("status", "1");
			map.put("message", "禁用成功");
			result = false;
		}
		postInfoService.enable(invitationEntity, result);
		return map;
	}

	// 审核通过
	@RequestMapping(value = "/passPostInfo")
	public Map<String, String> passPostInfo(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("postInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		Date date = new Date();
		PostInfoEntity postInfoEntity = jsonObject.toJavaObject(PostInfoEntity.class);
		postInfoEntity.setAuditStatus("1");
		postInfoEntity.setUpdateDate(date);
		postInfoService.audit(postInfoEntity, true);
		map.put("state", "0");
		map.put("message", "审核通过");
		return map;
	}

	// 审核拒绝
	@RequestMapping(value = "/refusePostInfo")
	public Map<String, String> refusePostInfo(HttpServletRequest res, HttpServletResponse req) {

		Date date = new Date();
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("postInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		PostInfoEntity postInfoEntity = jsonObject.toJavaObject(PostInfoEntity.class);
		postInfoEntity.setAuditStatus("2");
		postInfoEntity.setUpdateDate(date);
		postInfoService.audit(postInfoEntity, false);
		map.put("state", "0");
		map.put("message", "审核驳回");
		return map;
	}

	/**
	 * 接口
	 */
	// 添加帖子
	@RequestMapping(value = "/addPostInfo")
	public Map<String, String> addPostInfo(HttpServletRequest res, HttpSession session, HttpServletResponse req,
			PostInfoEntity postInfo) {
		Map<String, String> map = new HashMap<String, String>();
		Date date = new Date();
		postInfo.setCreateDate(date);
		postInfo.setStatus("1");//默认禁用
		postInfo.setAuditStatus("0");//默认审核状态 未审核
		postInfoService.save(postInfo);
		map.put("state", "0");
		map.put("message", "添加成功");
		return map;
	}

}
