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

import com.jy.pc.Entity.AgriculturalEntity;
import com.jy.pc.Entity.GrainTradingCommentEntity;
import com.jy.pc.Entity.GrainTradingReplyEntity;
import com.jy.pc.Entity.PictureInfoEntity;
import com.jy.pc.Service.AgriculturalService;
import com.jy.pc.Service.GrainTradingCollectionService;
import com.jy.pc.Service.GrainTradingCommentService;
import com.jy.pc.Service.GrainTradingPraiseService;
import com.jy.pc.Service.GrainTradingReplyService;
import com.jy.pc.Service.PictureInfoService;

@Controller
@ResponseBody
@RequestMapping(value = "grainTrading")
public class GrainTradingController {
	@Autowired
	private AgriculturalService agriculturalService;
	@Autowired
	private GrainTradingCollectionService collectionService;
	@Autowired
	private GrainTradingPraiseService praiseService;
	@Autowired
	private GrainTradingCommentService commentService;
	@Autowired
	private GrainTradingReplyService replyService;
	@Autowired
	private PictureInfoService pictureInfoService;

	/**
	 * H5-根据主键id返回详情信息
	 * 
	 * @param id 粮食买卖id
	 * @return consumer: note:
	 */
	@RequestMapping(value = "/mobileView")
	public Map<String, Object> mobileWebView(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		AgriculturalEntity agr = agriculturalService.findId(id);
		List<PictureInfoEntity> agrPic = pictureInfoService.findByAgrId(id);
		GrainTradingCommentEntity comment = commentService.findNewestById(id);
		map.put("agr", agr);
		map.put("agrPic", agrPic);
		map.put("comment", comment);
		map.put("code", 200);
		return map;
	}

	/**
	 * PC-分页查询粮食买卖信息
	 * 
	 * @param title   标题
	 * @param name    回复人
	 * @param content 回复内容
	 * @param page    页码
	 * @param size    每页记录条数
	 * @return
	 */
	@RequestMapping(value = "/findReplyPageByParam")
	public Map<String, Object> findReplyPageByParam(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "cid", defaultValue = "") String cid,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "content", defaultValue = "") String content,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<List<Map<String, Object>>> data = replyService.findPageByParam(title, name, content,cid, pageable);
		map.put("code", "200");
		map.put("data", data);
		return map;
	}

	/**
	 * H5-获取评论下所有回复
	 * 
	 * @param aid  粮食买卖主键
	 * @param page 页码
	 * @param size 页尺寸
	 * @return consumer: note:
	 */
	@RequestMapping(value = "/findReplyPage")
	public Map<String, Object> findReplyPage(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "cid") String cid, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<GrainTradingReplyEntity> data = replyService.findCommentPage(cid, pageable);
		map.put("code", "200");
		map.put("data", data);
		return map;
	}
	
	/**
	 * H5-获取粮食买卖下所有评论
	 * 
	 * @param aid  粮食买卖主键
	 * @param page 页码
	 * @param size 页尺寸
	 * @return consumer: note:
	 */
	@RequestMapping(value = "/findCommentPage")
	public Map<String, Object> findCommentPage(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "aid") String aid, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<List<Map<String, Object>>> data = commentService.findCommentPage(aid, pageable);
		map.put("code", "200");
		map.put("data", data);
		return map;
	}

	/**
	 * PC-分页查询粮食买卖信息
	 * 
	 * @param title   标题
	 * @param name    评论人
	 * @param content 评论内容
	 * @param page    页码
	 * @param size    每页记录条数
	 * @return
	 */
	@RequestMapping(value = "/findCommentPageByParam")
	public Map<String, Object> findPageByParam(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "content", defaultValue = "") String content,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<List<Map<String, Object>>> data = commentService.findPageByParam(title, name, content, pageable);
		map.put("code", "200");
		map.put("data", data);
		return map;
	}

	/**
	 * PC-修改回复启/禁用状态
	 * 
	 * @param status 状态
	 * @param id     评论id
	 * @return consumer: note:
	 */
	@RequestMapping(value = "/enableReply")
	public Map<String, String> enableReply(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") int status, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		GrainTradingReplyEntity reply = replyService.findId(id);
		if (status == 0) {
			map.put("code", "200");
			map.put("message", "启用成功");
		}
		if (status == 1) {
			map.put("code", "200");
			map.put("message", "禁用成功");
		}
		if (status != 0 && status != 1) {
			map.put("code", "500");
			map.put("message", "操作参数错误");
			return map;
		}
		reply.setStatus(status);
		replyService.save(reply);
		return map;
	}

	/**
	 * PC-修改评论启/禁用状态
	 * 
	 * @param status 状态
	 * @param id     评论id
	 * @return consumer: note:
	 */
	@RequestMapping(value = "/enableComment")
	public Map<String, String> enableComment(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") int status, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		GrainTradingCommentEntity comment = commentService.findId(id);
		if (status == 0) {
			map.put("code", "200");
			map.put("message", "启用成功");
		}
		if (status == 1) {
			map.put("code", "200");
			map.put("message", "禁用成功");
		}
		if (status != 0 && status != 1) {
			map.put("code", "500");
			map.put("message", "操作参数错误");
			return map;
		}
		comment.setStatus(status);
		commentService.save(comment);
		return map;
	}

	/**
	 * PC-删除评论,级联删除下方回复
	 * 
	 * @param replyId 回复id
	 * @return
	 */
	@RequestMapping(value = "/delCommentPC")
	public Map<String, String> delCommentPC(HttpServletRequest res, HttpServletResponse req, String commentId) {
		Map<String, String> map = new HashMap<String, String>();
		commentService.delete(commentId);
		map.put("code", "200");
		map.put("message", "删除成功");
		return map;
	}

	/**
	 * h5-删除评论,级联删除下方回复
	 * 
	 * @param replyId 回复id
	 * @return
	 */
	@RequestMapping(value = "/deleteComment")
	public Map<String, String> deleteComment(HttpServletRequest res, HttpServletResponse req, String commentId) {
		Map<String, String> map = new HashMap<String, String>();
		commentService.delete(commentId);
		map.put("code", "200");
		map.put("message", "删除成功");
		return map;
	}

	/**
	 * PC-删除回复
	 * 
	 * @param replyId 回复id
	 * @return
	 */
	@RequestMapping(value = "/delReplyPC")
	public Map<String, String> deleteReply(HttpServletRequest res, HttpServletResponse req, String replyId) {
		Map<String, String> map = new HashMap<String, String>();
		replyService.delete(replyId);
		map.put("code", "200");
		map.put("message", "删除成功");
		return map;
	}

	/**
	 * h5-删除回复
	 * 
	 * @param replyId 回复id
	 * @return
	 */
	@RequestMapping(value = "/deleteReply")
	public Map<String, String> replyService(HttpServletRequest res, HttpServletResponse req, String replyId) {
		Map<String, String> map = new HashMap<String, String>();
		replyService.delete(replyId);
		map.put("code", "200");
		map.put("message", "删除成功");
		return map;
	}

	/**
	 * h5-新增回复，默认审核中
	 * 
	 * @param comment 评论内容
	 * @return
	 */
	@RequestMapping(value = "/addReply")
	public Map<String, String> addReply(HttpServletRequest res, HttpServletResponse req,
			GrainTradingReplyEntity reply) {
		Map<String, String> map = new HashMap<String, String>();
		reply.setReplyDate(new Date());
		reply.setStatus(1);
		replyService.save(reply);
		map.put("code", "200");
		map.put("message", "回复成功");
		return map;
	}

	/**
	 * h5-新增评论，默认审核中
	 * 
	 * @param comment 评论内容
	 * @return
	 */
	@RequestMapping(value = "/addComment")
	public Map<String, String> addComment(HttpServletRequest res, HttpServletResponse req,
			GrainTradingCommentEntity comment) {
		Map<String, String> map = new HashMap<String, String>();
		comment.setCommentDate(new Date());
		comment.setStatus(1);
		commentService.save(comment);
		map.put("code", "200");
		map.put("message", "评论成功");
		return map;
	}

	/**
	 * PC-设置精选状态
	 * 
	 * @param isSelected 精选状态 0否1是
	 * @param id         粮食买卖ID
	 * @return
	 */
	@RequestMapping(value = "/setSelected")
	public Map<String, String> setSelected(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "isSelected") int isSelected, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		AgriculturalEntity grain = agriculturalService.findId(id);
		if (isSelected == 0) {
			map.put("code", "200");
			map.put("message", "取消精选成功");
		}
		if (isSelected == 1) {
			map.put("code", "200");
			map.put("message", "设置精选成功");
		}
		if (isSelected != 0 && isSelected != 1) {
			map.put("code", "500");
			map.put("message", "操作参数错误");
			return map;
		}
		grain.setIsSelected(isSelected);
		agriculturalService.save(grain);
		return map;
	}

	/**
	 * h5-设置收藏状态
	 * 
	 * @param action 0取消收藏，1收藏
	 * @param agrId  粮食买卖信息id
	 * @param userId 用户id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/setCollection")
	public Map<String, String> setCollection(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "action") int action, @RequestParam(name = "agrId") String agrId,
			@RequestParam(name = "userId") String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if (action == 0) {
			// 取消收藏
			collectionService.cancel(agrId, userId);
			map.put("code", "200");
			map.put("message", "取消收藏成功");
		}
		if (action == 1) {
			// 收藏
			collectionService.collection(agrId, userId);
			map.put("code", "200");
			map.put("message", "收藏成功");
		}
		return map;
	}

	/**
	 * h5-设置点赞状态
	 * 
	 * @param action 0取消点赞，1点赞
	 * @param agrId  粮食买卖信息id
	 * @param userId 用户id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/setPraise")
	public Map<String, String> setPraise(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "action") int action, @RequestParam(name = "agrId") String agrId,
			@RequestParam(name = "userId") String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if (action == 0) {
			// 取消点赞
			praiseService.cancel(agrId, userId);
			map.put("code", "200");
			map.put("message", "取消点赞成功");
		}
		if (action == 1) {
			// 点赞
			praiseService.praise(agrId, userId);
			map.put("code", "200");
			map.put("message", "点赞成功");
		}
		return map;
	}

	/**
	 * h5-增加阅读量
	 * 
	 * @param id 粮食买卖信息id
	 * @return
	 */
	@RequestMapping(value = "/addPV")
	public Map<String, Object> addPV(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		AgriculturalEntity grain = agriculturalService.findId(id);
		grain.setViewNum(grain.getViewNum() + 1);
		agriculturalService.save(grain);
		map.put("code", "200");
		map.put("data", grain.getViewNum());
		return map;
	}

}
