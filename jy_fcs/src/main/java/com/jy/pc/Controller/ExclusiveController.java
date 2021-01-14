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

import com.jy.pc.Entity.ArticleManageEntity;
import com.jy.pc.Entity.ExclusiveCommentEntity;
import com.jy.pc.Entity.ExclusiveReplyEntity;
import com.jy.pc.Service.ArticleManageService;
import com.jy.pc.Service.ExclusiveCollectionService;
import com.jy.pc.Service.ExclusiveCommentService;
import com.jy.pc.Service.ExclusivePraiseService;
import com.jy.pc.Service.ExclusiveReplyService;

/**
 * 独家点评控制层
 * 
 * */
@Controller
@ResponseBody
@RequestMapping(value = "exclusive")
public class ExclusiveController {
	@Autowired
	private ArticleManageService eduArticleManageService;
	@Autowired
	private ExclusiveCollectionService collectionService;
	@Autowired
	private ExclusivePraiseService praiseService;
	@Autowired
	private ExclusiveCommentService commentService;
	@Autowired
	private ExclusiveReplyService replyService;

	/**
	 * PC-分页查询独家点评评论信息
	 * @param title 标题
	 * @param name 评论人
	 * @param content 评论内容
	 * @param page 页码
	 * @param size 每页记录条数
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
	 * PC-分页查询独家点评回复信息
	 * @param title 标题
	 * @param name 评论人
	 * @param content 评论内容
	 * @param page 页码
	 * @param size 每页记录条数
	 * @return
	 */
	@RequestMapping(value = "/findReplyPageByParam")
	public Map<String, Object> findReplyPageByParam(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "commentId", defaultValue = "") String commentId,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "content", defaultValue = "") String content,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<List<Map<String, Object>>> data = replyService.findReplyPageByParam(commentId,name, content, pageable);
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
		ExclusiveReplyEntity reply = replyService.findId(id);
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
		ExclusiveCommentEntity comment = commentService.findId(id);
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
			ExclusiveReplyEntity reply) {
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
			ExclusiveCommentEntity comment) {
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
		ArticleManageEntity articleManageEntity = eduArticleManageService.findBId(id);
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
		articleManageEntity.setIsSelected(isSelected);
		eduArticleManageService.save(articleManageEntity);
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
		ArticleManageEntity articleManageEntity = eduArticleManageService.findBId(id);
		articleManageEntity.setViewNum(articleManageEntity.getViewNum() + 1);
		eduArticleManageService.save(articleManageEntity);
		map.put("code", "200");
		map.put("data", articleManageEntity.getViewNum());
		return map;
	}
	
	
	/**
	 * 根据文章id、用户id查询评论信息
	 * 
	 * @param artId
	 * @param userId
	 */
	@RequestMapping(value = "/findCommentByUserId")
	public Map<String, Object> findCommentByUserId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "artId") String artId, 
			@RequestParam(name = "userId") String userId,
			@RequestParam(name = "page") Integer page, 
			@RequestParam(name = "size") Integer size) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<List<Map<String,Object>>> commmentMap = commentService.findCommentByUserId(artId, userId,pageable);
		map.put("code", "200");
		map.put("data", commmentMap);
		return map;
	}
	
	/**
	 * 根据文章id、用户id查询评论信息
	 * 
	 * @param commentId
	 * @param userId
	 */
	@RequestMapping(value = "/findReplyByUserId")
	public Map<String, Object> findReplyByUserId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "commentId") String commentId, 
			@RequestParam(name = "userId") String userId,
			@RequestParam(name = "page") Integer page, 
			@RequestParam(name = "size") Integer size) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<List<Map<String,Object>>> replyMap = replyService.findReplyByUserId(commentId, userId,pageable);
		map.put("code", "200");
		map.put("data", replyMap);
		return map;
	}
}
