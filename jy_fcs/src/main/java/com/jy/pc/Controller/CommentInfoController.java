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
import com.jy.pc.Entity.CommentReplyInfoEntity;
import com.jy.pc.Entity.PostCommentInfoEntity;
import com.jy.pc.Entity.PostInfoEntity;
import com.jy.pc.Service.CommentReplyInfoService;
import com.jy.pc.Service.PostCommentInfoService;
import com.jy.pc.Service.PostInfoService;

/**
 * 帖子评论相关接口
 * 
 * @author admin
 *
 */
@Controller
@RequestMapping(value = "/commentInfo")
@ResponseBody
public class CommentInfoController {
	@Autowired
	private PostCommentInfoService postCommentInfoService;
	@Autowired
	private CommentReplyInfoService commentReplyInfoService;
	@Autowired
	private PostInfoService postInfoService;

	/**
	 * 根据传入的信息，新增评论
	 * 
	 * @param postCommentInfoEntity commentContent：内容 commentUserName：评论人
	 *                              postInfoEntity.id：帖子ID
	 * @return 0成功1失败 consumer:H5 note:由客户添加评论，默认为启用状态
	 */
	@RequestMapping(value = "/addCommentInfo")
	public Map<String, Object> addComment(HttpServletRequest res, HttpServletResponse req,
			PostCommentInfoEntity postCommentInfoEntity) {
		Map<String, Object> map = new HashMap<String, Object>();
		postCommentInfoEntity.setCommentDate(new Date());
		postCommentInfoEntity.setStatus("0");
		postCommentInfoService.save(postCommentInfoEntity);
		map.put("code", "200");// 成功
		map.put("message", "评论成功");
		return map;
	}

	/**
	 * 根据传入的主键，删除对应评论
	 * 
	 * @param id 评论ID
	 * @return 0成功1失败 consumer:H5 note:此方法为级联删除，将级联删除评论下所有的回复
	 */
	@RequestMapping(value = "/delCommentInfo")
	public Map<String, Object> delInfo(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
			try {
				PostCommentInfoEntity postInfo = postCommentInfoService.findId(id);
				PostInfoEntity post = postInfoService.findId(postInfo.getPostId());
				if(postInfo.getStatus().equals("1")){
					post.setCommentNum(post.getCommentNum()-1);
					postInfoService.update(post);
				}
				List<CommentReplyInfoEntity> replyInfo = commentReplyInfoService.findByCommentId(id);
				for(int i=0;i<replyInfo.size();i++) {
					CommentReplyInfoEntity commentReplyInfoEntity =commentReplyInfoService.findId(replyInfo.get(i).getId());
					commentReplyInfoEntity.setStatus("-1");
					commentReplyInfoService.update(commentReplyInfoEntity);
				}
				postInfo.setStatus("-1");
				postCommentInfoService.update(postInfo);
				map.put("code", "200");
				map.put("message", "删除成功");
			} catch (Exception e) {
				map.put("code", "500");
				map.put("message", "删除失败");
				e.printStackTrace();
			}
			return map;
		}

	

	// 接口，评论列表
	@RequestMapping(value = "/findByPostId")
	public Map<String, Object> findByPostId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "postId") String postId, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
//		Page<PostCommentInfoPO> replyList = postCommentInfoService.findByPostId(postId, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
//		map.put("data", replyList);
		return map;
	}

	// 查询 分页
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findListByContent(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "content") String content, @RequestParam(name = "user") String user,@RequestParam(name = "name") String name,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<List<Map<String, Object>>> replyList = postCommentInfoService.findListByContent(content, user, name, pageable);
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
		PostCommentInfoEntity postCommentInfoEntity = jsonObject.toJavaObject(PostCommentInfoEntity.class);
		Date date = new Date();
		postCommentInfoEntity.setCommentDate(date);
		postCommentInfoEntity.setStatus("0");
		postCommentInfoService.save(postCommentInfoEntity);
		map.put("status", "0");
		map.put("message", "添加成功");
		return map;
	}

	// 评论修改
	@RequestMapping(value = "/update")
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("commentReplyInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		PostCommentInfoEntity postCommentInfoEntity = jsonObject.toJavaObject(PostCommentInfoEntity.class);
		postCommentInfoService.update(postCommentInfoEntity);
		map.put("status", "0");
		map.put("message", "修改成功");
		return map;
	}

	// 评论删除
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		postCommentInfoService.delete(id);
		map.put("code", "200");
		map.put("message", "删除成功");
		return map;
	}

	// 评论启用/禁用
	@RequestMapping(value = "/enable")
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") String status, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		PostCommentInfoEntity postCommentInfoEntity = postCommentInfoService.findId(id);
		PostInfoEntity postInfoEntity = new PostInfoEntity();
		boolean result = true;
		if (status.equals("1")) {
			postInfoEntity.setCommentNum(postInfoEntity.getCommentNum()+1);
			postInfoService.update(postInfoEntity);
			map.put("code", "200");
			map.put("message", "启用成功");
		}
		if (status.equals("0")) {
			postInfoEntity.setCommentNum(postInfoEntity.getCommentNum()-1);
			postInfoService.update(postInfoEntity);
			map.put("code", "200");
			map.put("message", "禁用成功");
			result = false;
		}
		postCommentInfoEntity.setStatus(status);
		postCommentInfoService.enable(postCommentInfoEntity, result);
		return map;
	}
	// 条件查询，根据主键查询评论
	@RequestMapping(value = "/findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		PostCommentInfoEntity postCommentInfoEntity = postCommentInfoService.findId(id);
		if (postCommentInfoEntity != null) {
			map.put("status", "0");
			map.put("data", postCommentInfoEntity);
		} else {
			map.put("status", "1");
		}
		return map;
	}

	// 条件查询回复人ID
	@RequestMapping(value = "/findByUserReplyId")
	public Map<String, Object> findByUserReplyId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "replyUserId") String replyUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CommentReplyInfoEntity> commentReplyInfoEntity = commentReplyInfoService.findByUserReplyId(replyUserId);
		if (commentReplyInfoEntity != null) {
			map.put("code", "200");
			map.put("data", commentReplyInfoEntity);
		} else {
			map.put("code", "500");
		}
		return map;
	}
	/**
	 * 帖子评论详情查询
	 * */
	@RequestMapping(value = "/findByCommentId")
	public Map<String, Object> findByCommentId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		PostCommentInfoEntity postCommentInfoEntity = postCommentInfoService.findId(id);

		PostInfoEntity postInfoEntity = postInfoService.findId(postCommentInfoEntity.getPostId());
		try {
			map.put("code", "200");
			map.put("data", postInfoEntity);
			map.put("dataComment", postCommentInfoEntity);
		} catch (Exception e) {
			map.put("code", "500");
		} 
		return map;
	}
}
