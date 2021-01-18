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

import com.jy.pc.Entity.CaseInfoCommentEntity;
import com.jy.pc.Entity.CircleThumbsEntity;
import com.jy.pc.Entity.ClassificationEntity;
import com.jy.pc.Entity.PostCollectionEntity;
import com.jy.pc.Entity.PostCommentInfoEntity;
import com.jy.pc.Entity.PostInfoEntity;
import com.jy.pc.Enum.ClassificationEnum;
import com.jy.pc.Service.CircleThumbsService;
import com.jy.pc.Service.ClassificationService;
import com.jy.pc.Service.CommentReplyInfoService;
import com.jy.pc.Service.PostCollectionService;
import com.jy.pc.Service.PostCommentInfoService;
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
	@Autowired
	private PostCommentInfoService postCommentInfoService;
	@Autowired
	private CircleThumbsService circleThumbsService;
	@Autowired
	private PostCollectionService postCollectionService;
	@Autowired
	private CommentReplyInfoService commentReplyInfoService;

	@RequestMapping(value = "/getPostType")
	public Map<String, Object> getPostType(HttpServletRequest res, HttpServletResponse req) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<ClassificationEntity> classList = classificationService
				.findClassByCode(ClassificationEnum.POSTINFO_TYPE.getCode());
		map.put("state", "0");// 成功
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
		map.put("state", "0");// 成功
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

	// 条件查询pc
	@RequestMapping(value = "/findByPcId")
	public Map<String, Object> findByPcId(HttpServletRequest res, HttpServletResponse req,
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

	// 条件查询h5（浏览量）
	@RequestMapping(value = "/findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PostInfoEntity invitationEntity = postInfoService.findId(id);
			invitationEntity.setPageview(invitationEntity.getPageview() + 1);
			postInfoService.update(invitationEntity);
			map.put("code", "200");
			map.put("data", invitationEntity);
		} catch (Exception e) {
			map.put("code", "500");
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
		Date date = new Date();
		invitationEntity.setUpdateDate(date);
		boolean result = true;
		invitationEntity.setStatus(status);
		if (status.equals("0")) {
			map.put("status", "0");
			map.put("message", "启用成功");
		}
		if (status.equals("1")) {
			map.put("status", "1");
			map.put("message", "禁用成功");
			result = false;
		}
		postInfoService.enable(invitationEntity, result);
		return map;
	}

	// 审核通过
	@RequestMapping(value = "/passPostInfo")
	public Map<String, String> passPostInfo(HttpServletRequest res, HttpServletResponse req, String id,
			String auditUser) {

		Map<String, String> map = new HashMap<String, String>();
		PostInfoEntity postInfoEntity = postInfoService.findId(id);
		Date date = new Date();
		postInfoEntity.setAuditStatus("1");
		postInfoEntity.setUpdateDate(date);
		postInfoEntity.setAuditUser(auditUser);
		postInfoService.audit(postInfoEntity, true);
		map.put("state", "0");
		map.put("message", "审核通过");
		return map;
	}

	// 审核拒绝
	@RequestMapping(value = "/refusePostInfo")
	public Map<String, String> refusePostInfo(HttpServletRequest res, HttpServletResponse req, String id,
			String auditUser, String reason) {

		Map<String, String> map = new HashMap<String, String>();
		PostInfoEntity postInfoEntity = postInfoService.findId(id);
		Date date = new Date();
		postInfoEntity.setAuditStatus("2");
		postInfoEntity.setUpdateDate(date);
		postInfoEntity.setAuditUser(auditUser);
		postInfoEntity.setReason(reason);
		postInfoService.audit(postInfoEntity, false);
		map.put("state", "0");
		map.put("message", "审核驳回");
		return map;
	}

	// 删除
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PostInfoEntity postInfo = postInfoService.findId(id);
			List<PostCommentInfoEntity> postCommentList = postCommentInfoService.findPostId(postInfo.getId());
			for (int i = 0; i < postCommentList.size(); i++) {
				postCommentInfoService.delete(postCommentList.get(i).getId());
			}
			postInfoService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("state", "0");// 成功
		map.put("message", "删除成功");
		return map;
	}

	/**
	 * 接口
	 */
	// 添加帖子
	@RequestMapping(value = "/addPostInfo")
	public Map<String, String> addPostInfo(HttpServletRequest res, HttpSession session, HttpServletResponse req,
			PostInfoEntity postInfoEntity, @RequestParam(name = "addItem") String[] addItem) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			postInfoService.saveAgr(addItem, postInfoEntity);
			map.put("code", "200");
			map.put("message", "添加成功");
		} catch (Exception e) {
			map.put("code", "500");// 失败
			map.put("message", "添加失败");
		}
		return map;
	}

	// 是否精品
	@RequestMapping(value = "/Boutique")
	public Map<String, String> opensulfBoutique(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "isSelected") Integer isSelected, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		PostInfoEntity invitationEntity = postInfoService.findId(id);
		invitationEntity.setIsSelected(isSelected);
		Date date = new Date();
		invitationEntity.setUpdateDate(date);
		boolean result = true;
		invitationEntity.setIsSelected(isSelected);
		if (isSelected.equals(0)) {
			map.put("code", "200");
			map.put("message", "启用精品成功");
		} else {
			map.put("code", "200");
			map.put("message", "禁用精品成功");
			result = false;
		}
		postInfoService.Boutique(invitationEntity, result);
		return map;
	}

	/**
	 * 帖子点赞
	 * 
	 */
	@RequestMapping(value = "/postThumbs")
	@ResponseBody
	public Map<String, String> saveUserManualInfo(HttpServletRequest res, HttpServletResponse req,
			CircleThumbsEntity circleThumbsEntity, @RequestParam(name = "isCancelThumbs") Integer isCancelThumbs,
			@RequestParam(name = "thumbsUserId") String thumbsUserId,
			@RequestParam(name = "circleId") String circleId) {

		Map<String, String> map = new HashMap<String, String>();
		PostInfoEntity PostInfoEntity = postInfoService.findId(circleId);
		if (isCancelThumbs == 0) {
			try {
				circleThumbsEntity.setPostInfoEntity(PostInfoEntity);
				circleThumbsService.save(circleThumbsEntity);
				PostInfoEntity.setPraiseNum(PostInfoEntity.getPraiseNum() + 1);
				map.put("code", "200");
				map.put("msg", "点赞成功");
			} catch (Exception e) {
				map.put("code", "500");
				map.put("msg", "点赞失败");
			}
		} else {
			CircleThumbsEntity circleThumbs = circleThumbsService.findUserId(thumbsUserId, circleId);
			circleThumbsService.delete(circleThumbs.getId());
			PostInfoEntity.setPraiseNum(PostInfoEntity.getPraiseNum() - 1);
			map.put("code", "200");
			map.put("msg", "取消点赞成功");
		}
		postInfoService.update(PostInfoEntity);
		return map;
	}

	/**
	 * 查询最新最火热议好评精品
	 * 
	 */
	@RequestMapping(value = "/findByFive")
	public Map<String, Object> findByFive(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "type", defaultValue = "0") String type, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		try {
			Page<PostInfoEntity> invitationList = postInfoService.findByType(type, pageable);
			map.put("code", "200");
			map.put("data", invitationList);
		} catch (Exception e) {
			map.put("code", "500");
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 帖子收藏
	 * 
	 */
	@RequestMapping(value = "/postCollection")
	@ResponseBody
	public Map<String, String> postCollection(HttpServletRequest res, HttpServletResponse req,
			PostCollectionEntity postCollectionEntity,
			@RequestParam(name = "isCancelCollection") Integer isCancelCollection,
			@RequestParam(name = "userId") String userId, @RequestParam(name = "circleId") String circleId) {

		Map<String, String> map = new HashMap<String, String>();
		PostInfoEntity postInfoEntity = postInfoService.findId(circleId);
		if (isCancelCollection == 0) {
			try {
				postCollectionEntity.setPostInfoEntity(postInfoEntity);
				postCollectionService.save(postCollectionEntity);
				postInfoEntity.setCollectionNum(postInfoEntity.getCollectionNum() + 1);
				map.put("code", "200");
				map.put("msg", "收藏成功");
			} catch (Exception e) {
				map.put("code", "500");
				map.put("msg", "收藏失败");
			}
		} else {
			PostCollectionEntity postCollection = postCollectionService.findUserId(userId, circleId);
			postCollectionService.delete(postCollection.getId());
			postInfoEntity.setCollectionNum(postInfoEntity.getCollectionNum() - 1);
			map.put("code", "200");
			map.put("msg", "取消收藏成功");
		}
		postInfoService.update(postInfoEntity);
		return map;
	}

	/**
	 * 我的-我的收藏
	 * 
	 * @Param userId 用户Id
	 */
	@RequestMapping("/postByUserId")
	@ResponseBody
	public Map<String, Object> postByUserId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "userId") String userId, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		try {

			Page<List<Map<String, Object>>> postCollection = postCollectionService.findPageByParam(userId, pageable);
			map.put("code", "200");// 查询成功
			map.put("data", postCollection);
		} catch (Exception e) {
			map.put("code", "500");// 查询失败
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return map;
	}

	// 移动端-根据帖子Id、用户id查询帖子列表详情（接口）
	@RequestMapping(value = "/findPostId")
	@ResponseBody
	public Map<String, Object> findPostId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id, @RequestParam(name = "userId") String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> postInfoEntity = postInfoService.findInfoByPostUserId(id, userId);
		try {
			map.put("code", "200");// 查询成功
			map.put("message", "查询成功");
			map.put("data", postInfoEntity);
		} catch (Exception e) {
			map.put("code", "500");// 查询失败
			map.put("message", "查询失败");
		}
		return map;

	}
	/**
	 * 根据帖子id、用户id查询回复信息
	 * 
	 * @param commentId
	 * @param userId
	 */
	@RequestMapping(value = "/findReplyByUserId")
	public Map<String, Object> findReplyByUserId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "commentId") String commentId, @RequestParam(name = "userId") String userId, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<List<Map<String,Object>>> postReplyMap = commentReplyInfoService.findReplyByUserId(commentId, userId, pageable);
		map.put("code", "200");
		map.put("data", postReplyMap);
		return map;
	}
	/**
	 * 根据文章id、用户id查询评论信息
	 * 
	 * @param postId
	 * @param userId
	 */
	@RequestMapping(value = "/findCommentByUserId")
	public Map<String, Object> findCommentByUserId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "postId") String postId, @RequestParam(name = "userId") String userId, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<List<Map<String,Object>>> postCommentMap = postCommentInfoService.findCommentByUserId(postId, userId, pageable);
		map.put("code", "200");
		map.put("data", postCommentMap);
		return map;
	}
	
	/**
	 * H5-根据用户id查询帖子是否自身评论
	 * 
	 * @param postId  帖子主键
	 * @param page 页码
	 * @param size 页尺寸
	 * @return consumer: note:
	 */
	@RequestMapping(value = "/findCommentPage")
	public Map<String, Object> findCommentPage(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "postId") String postId, @RequestParam(name = "userId") String userId, 
			@RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<List<Map<String, Object>>> data = postCommentInfoService.findCommentPage(postId, userId, pageable);
		map.put("code", "200");
		map.put("data", data);
		return map;
	}
	/**
	 * H5-根据用户id查询帖子是否自身回复
	 * 
	 * @param cid  帖子主键
	 * @param page 页码
	 * @param size 页尺寸
	 * @return consumer: note:
	 */
	@RequestMapping(value = "/findReplyPage")
	public Map<String, Object> findReplyPage(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "cid") String cid, @RequestParam(name = "userId") String userId, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<List<Map<String, Object>>> data = commentReplyInfoService.findByIsMyReplyPage(cid, userId, pageable);
		map.put("code", "200");
		map.put("data", data);
		return map;
	}
	
	/**
	 * H5-获取帖子单条下所有评论
	 */
	@RequestMapping(value = "/findByCommentPage")
	public Map<String, Object> findByCommentPage(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "postId") String postId, @RequestParam(name = "userId") String userId, 
			@RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		try {
			Page<List<Map<String, Object>>> commentList =postCommentInfoService.findByCommentPage(postId, userId, pageable);
			map.put("code", "200");
			map.put("message", "查询成功");
			map.put("data", commentList);
		} catch (Exception e) {
			map.put("code", "500");// 失败
			map.put("message", "查询失败");
		}
		return map;
	}
	
	/**
	 *   搜索帖子信息（接口,标题名称）
	 * 
	 */
	@RequestMapping(value = "/findPostInfoList")
	public Map<String, Object> findPostInfoList(HttpServletRequest res, HttpServletResponse req,
			
			
			@RequestParam(name = "parentCode", defaultValue = "") String parentCode,
			@RequestParam(name = "userId", defaultValue = "") String userId,
			@RequestParam(name = "sort", defaultValue = "1") String sort,
			 @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);

		try {
			Page<List<Map<String, Object>>> postInfoList = postInfoService.findPostInfo(parentCode, sort, userId, pageable);
			map.put("code", "200");// 成功
			map.put("message", "查询成功");
			map.put("data", postInfoList);
		} catch (Exception e) {
			map.put("code", "500");// 失败
			map.put("message", "查询失败");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * H5-获取评论下所有回复
	 */
	@RequestMapping(value = "/findByReplyPage")
	public Map<String, Object> findByReplyPage(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "commentId") String commentId, @RequestParam(name = "userId") String userId,
			@RequestParam(name = "page") Integer page,@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		try {
			Page<List<Map<String, Object>>> replyList = commentReplyInfoService.findReplyPage(commentId, userId, pageable);
			map.put("code", "200");
			map.put("message", "查询成功");
			map.put("data", replyList);
		} catch (Exception e) {
			map.put("code", "500");// 失败
			map.put("message", "查询失败");
		}
		return map;
	}
	
	/**
	 * 帖子查看详情H5（收藏）
	 * */
	@RequestMapping(value = "/findByPostId")
	public Map<String, Object> findByPostId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "userId") String userId,@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> postInfo = postInfoService.findByPostId(userId, id);
			map.put("code", "200");// 查询数据成功
			map.put("message", "查询成功");
			map.put("data", postInfo);
		} catch (Exception e) {
			map.put("code", "500");// 失败
			map.put("message", "查询失败");
		}
		return map;
	}
}
