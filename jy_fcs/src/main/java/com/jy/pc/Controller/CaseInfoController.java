package com.jy.pc.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.pc.Entity.CaseInfoCollectionEntity;
import com.jy.pc.Entity.CaseInfoEntity;
import com.jy.pc.Entity.CaseInfoIrrelevantEntity;
import com.jy.pc.Entity.CasePraiseEntity;
import com.jy.pc.Entity.KeyWordEntity;
import com.jy.pc.Enum.ClassificationEnum;
import com.jy.pc.Service.CaseInfoCollectionService;
import com.jy.pc.Service.CaseInfoCommentService;
import com.jy.pc.Service.CaseInfoIrrelevantService;
import com.jy.pc.Service.CaseInfoReplyService;
import com.jy.pc.Service.CaseInfoService;
import com.jy.pc.Service.CasePraiseService;
import com.jy.pc.Service.KeyWordService;

@Controller
@ResponseBody
@RequestMapping(value = "/caseInfo")
public class CaseInfoController {

	@Autowired
	private CaseInfoService caseInfoService;
	
	@Autowired
	private KeyWordService keyWordService;
	
	@Autowired
	private CasePraiseService casePraiseService;
	
	@Autowired
	private CaseInfoCollectionService caseInfoCollectionService;
	
	@Autowired
	private CaseInfoReplyService caseInfoReplyService;
	
	@Autowired
	private CaseInfoIrrelevantService caseInfoIrrelevantService;
	
	@Autowired
	private CaseInfoCommentService caseInfoCommentService;

	// 接口 -- 根据id获取信息
	@RequestMapping(value = "findLatestCaseInfoById")
	public Map<String, Object> findLatestCaseInfoById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		CaseInfoEntity caseInfo = caseInfoService.findBId(id);
		if (caseInfo != null) {
			map.put("state", "0");// 查询数据成功
			map.put("message", "查询成功");
			map.put("data", caseInfo);
		} else {
			map.put("state", "1");// 查询数据失败
			map.put("message", "查询失败");
		}
		return map;
	}

	// 接口 -- 查询病虫害列表（分页）
	@RequestMapping(value = "/findCasePage")
	public Map<String, Object> findCasePage(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "cropsTypeCode", defaultValue = "") String cropsTypeCode,
			@RequestParam(name = "dipTypeCode", defaultValue = "") String dipTypeCode,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<CaseInfoEntity> caseIn = caseInfoService.findPage(name, cropsTypeCode, dipTypeCode, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", caseIn);
		return map;
	}

	// 接口 -- 获取病虫害关键词
	@RequestMapping(value = "/findCaseKey")
	public Map<String, Object> findCaseKey(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<KeyWordEntity> keyList = keyWordService.findListByClass(ClassificationEnum.KEYWORD_CASEINFO.getCode());
		if (keyList != null) {
			map.put("state", "0");
			map.put("data", keyList);
		} else {
			map.put("state", "1");
		}
		return map;
	}

	// 添加

	@RequestMapping(value = "/save")

	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req,CaseInfoEntity caseInfoEntity,
			@RequestParam("caseInfoEntity") String caseInfo) {
		Map<String, String> map = new HashMap<String, String>();

		try {
			caseInfoService.saveCase(caseInfoEntity, caseInfo);
			map.put("state", "0");
			map.put("message", "添加成功");
		} catch (Exception e) {
			map.put("state", "1");
			map.put("message", "添加失败");
		}
		return map;
	}

	// 修改前查询
	@RequestMapping(value = "findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		CaseInfoEntity caseInfo = caseInfoService.findBId(id);
		if (caseInfo != null) {
			map.put("state", "0");// 查询数据成功
			map.put("data", caseInfo);
		} else {
			map.put("state", "1");// 查询数据失败
		}
		return map;
	}

	// 修改
	@Transactional
	@RequestMapping(value = "update")
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req,CaseInfoEntity caseInfo) {
		Map<String, String> map = new HashMap<String, String>();
		caseInfoService.updateCase(caseInfo,res,req);	
		map.put("message", "修改成功");
		return map;
	}

	// 删除
	@RequestMapping(value = "delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();// 接收数据容器
		caseInfoService.delete(id);
		map.put("state", "0");
		map.put("message", "删除成功");
		return map;
	}

	// 模糊查询与分页
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "auditStatus") String auditStatus,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<CaseInfoEntity> caseIn = caseInfoService.findListByName(name, auditStatus, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", caseIn);
		return map;
	}

	// 启用禁用
	@RequestMapping(value = "/enable")
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "auditStatus") String auditStatus, @RequestParam(name = "id") String id) {
		Map<String, String> map = new HashMap<String, String>();
		CaseInfoEntity caseInfoEntity = caseInfoService.findBId(id);
		caseInfoEntity.setAuditStatus(auditStatus);
		caseInfoEntity.getAuditStatus();
		if (auditStatus.equals("1")) {
			caseInfoEntity.setAuditStatus("1");
			map.put("state", "0");
			map.put("message", "启用成功");
		} else if (auditStatus.equals("0")) {
			caseInfoEntity.setAuditStatus("0");
			map.put("state", "0");
			map.put("message", "禁用成功");
		}
		caseInfoService.update(caseInfoEntity);
		return map;
	}
	
	// 是否设为精选
	@RequestMapping(value = "/setSelect")
	public Map<String, String> setSelect(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "isSelected") Integer isSelected, @RequestParam(name = "id") String id) {
		Map<String, String> map = new HashMap<String, String>();
		CaseInfoEntity caseInfoEntity = caseInfoService.findBId(id);
		caseInfoEntity.setIsSelected(isSelected);
		Date date = new Date();
		caseInfoEntity.setUpdateDate(date);
		boolean result = true;
		if (isSelected.equals(1)) {
			caseInfoEntity.setIsSelected(1);
			map.put("code", "200");
			map.put("message", "取消精选成功");
		} else if (isSelected.equals(0)) {
			caseInfoEntity.setIsSelected(0);
			map.put("code", "500");
			map.put("message", "设置为精选成功");
			result = false;
		}
		caseInfoService.setSelect(caseInfoEntity, result);
		return map;
	}

	/**
	 * 接口
	 */
	// 查询所有病虫害信息的最新3条记录
	@RequestMapping(value = "findLatestCaseInfo")
	public Map<String, Object> findCaseInfo(HttpServletRequest res, HttpServletResponse req) {

		Map<String, Object> map = new HashMap<String, Object>();// 接收数据容器
		List<CaseInfoEntity> caseInfoEntity = caseInfoService.findCaseInfo();
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", caseInfoEntity);
		return map;
	}

	// 修改前查询
	@RequestMapping(value = "findCaseId")
	public Map<String, Object> findCaseId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		CaseInfoEntity caseInfo = caseInfoService.findBId(id);
		if (caseInfo != null) {
			map.put("state", "0");// 查询数据成功
			map.put("message", "查询成功");
			map.put("data", caseInfo);
		} else {
			map.put("state", "1");// 查询数据失败
			map.put("message", "查询失败");
		}
		return map;
	}

	// 关键字搜索病虫害信息
	@RequestMapping(value = "/findCaseInfoByKey")
	public Map<String, Object> findCaseInfoByKey(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<CaseInfoEntity> caseInfo = caseInfoService.findCaseInfoByKey(name);
		for (int i = 0; i < caseInfo.size(); i++) {
			map.put("data", caseInfo);
		}
		map.put("state", "0");
		map.put("message", "查询成功");
		return map;
	}
	
	/**
	 * 看图识病点赞
	 * 
	 * */
	@RequestMapping(value = "/saveCasePraise")
	public Map<String, String> saveCasePraise(HttpServletRequest res, HttpServletResponse req,
			CasePraiseEntity casePraiseEntity,@RequestParam(name = "isFabulous") Integer isFabulous,
			@RequestParam(name = "praiseUserId") String praiseUserId,@RequestParam(name = "caseId") String caseId) {
		Map<String, String> map = new HashMap<String, String>();
		CaseInfoEntity caseInfo = caseInfoService.findBId(caseId);
		if(isFabulous == 0) {
			try {
				Date date = new Date();
				casePraiseEntity.setFabulousDate(date);//设置点赞时间
				casePraiseEntity.setCaseInfoEntity(caseInfo);
				casePraiseService.save(casePraiseEntity);
				caseInfo.setPraiseNum(caseInfo.getPraiseNum() + 1);
				map.put("code", "200");
				map.put("message", "点赞成功");
			}catch(Exception e) {
				map.put("code", "500");
				map.put("message", "点赞失败");
			}
		}else {
			CasePraiseEntity casePraise = casePraiseService.findUserId(caseId, praiseUserId);
			casePraiseService.delete(casePraise.getId());
			caseInfo.setPraiseNum(caseInfo.getPraiseNum() - 1);
		}
		caseInfoService.update(caseInfo);
		return map;
	}
	
	/**
	 * 看图识病浏览量
	 * */
	@RequestMapping(value = "findNumById")
	public Map<String, Object> findNumById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CaseInfoEntity caseInfo = caseInfoService.findBId(id);
			caseInfo.setBrowseNum(caseInfo.getBrowseNum() + 1);
			caseInfoService.update(caseInfo);
			map.put("code", "200");// 查询数据成功
			map.put("data", caseInfo);
		} catch (Exception e) {
			map.put("code", "500");// 查询数据失败
		}
		return map;
	}
	
	/**
	 * 设置与我无关
	 * */
	@RequestMapping(value = "/isIrrelevant")
	public Map<String, String> saveIsIrrelevant(HttpServletRequest res, HttpServletResponse req,
			CaseInfoIrrelevantEntity caseInfoIrrelevantEntity,@RequestParam(name = "isIrrelevant") Integer isIrrelevant,
			@RequestParam(name = "irrelevantnUserId") String irrelevantnUserId,@RequestParam(name = "caseId") String caseId) {
		Map<String, String> map = new HashMap<String, String>();
		CaseInfoEntity caseInfo = caseInfoService.findBId(caseId);
		try {
			if(isIrrelevant == 0) {
				Date date = new Date();
				caseInfoIrrelevantEntity.setIrrelevantDate(date);
				caseInfoIrrelevantEntity.setCaseInfoEntity(caseInfo);
		
				caseInfoIrrelevantService.save(caseInfoIrrelevantEntity);
				caseInfo.setIrrelevantNum(caseInfo.getIrrelevantNum() + 1);
				map.put("code", "200");
				map.put("message", "设置与我无关成功成功");
			}else {
				CaseInfoIrrelevantEntity caseInfoIrrelevant = caseInfoIrrelevantService.findCaseUserId(caseId, irrelevantnUserId);
				caseInfoIrrelevantService.delete(caseInfoIrrelevant.getId());
				caseInfo.setIrrelevantNum(caseInfo.getIrrelevantNum() - 1);
				map.put("code", "200");
				map.put("message", "取消与我无关成功");
			}
			caseInfoService.update(caseInfo);
		} catch (Exception e) {
			map.put("code", "500");
			map.put("message", "操作失败");
		}
		return map;
	}
	
	/**
	 * 看图识病根据类型查询列表
	 *  0：最火 1：最新 2：精选 3：热议 4：好评
	 * */
	@RequestMapping(value = "/findByNum")
	public Map<String, Object> findByNum(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "type", defaultValue = "") Integer type, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();

		Pageable pageable = new PageRequest(page - 1, size);
		try {
			Page<CaseInfoEntity> caseInfoList = caseInfoService.findByNum(type, pageable);
			map.put("code", "200");// 成功
			map.put("message", "查询成功");
			map.put("data", caseInfoList);
		} catch (Exception e) {
			map.put("code", "500");
			map.put("message", "查询失败");
		}
		return map;
	}
	
	/**
	 * 看图识病收藏
	 * */
	@RequestMapping(value = "/saveCollection")
	public Map<String, String> saveCollection(HttpServletRequest res, HttpServletResponse req,
			CaseInfoCollectionEntity caseInfoCollectionEntity,@RequestParam(name = "isCollection") Integer isCollection,
			@RequestParam(name = "collectionUserId") String collectionUserId,@RequestParam(name = "caseId") String caseId) {
		Map<String, String> map = new HashMap<String, String>();
		CaseInfoEntity caseInfo = caseInfoService.findBId(caseId);
		if(isCollection == 0) {
			try {
				Date date = new Date();
				caseInfoCollectionEntity.setCollectionDate(date);
				caseInfoCollectionEntity.setCaseInfoEntity(caseInfo);
				caseInfoCollectionService.save(caseInfoCollectionEntity);
				caseInfo.setCollectionNum(caseInfo.getCollectionNum() + 1);
				map.put("code", "200");
				map.put("message", "收藏成功");
			}catch(Exception e) {
				map.put("code", "500");
				map.put("message", "收藏失败");
			}
		}else {
			CaseInfoCollectionEntity caseInfoCollection = caseInfoCollectionService.findCaseUserId(caseId, collectionUserId);
			caseInfoCollectionService.delete(caseInfoCollection.getId());
			caseInfo.setCollectionNum(caseInfo.getCollectionNum() - 1);
			map.put("code", "200");
			map.put("message", "取消收藏成功");
		}
		caseInfoService.update(caseInfo);
		return map;
	}
	
	/**
	 * 我的-我的收藏
	 * 
	 * @Param userId 用户Id
	 */
	@RequestMapping("/findByMyCollection")
	@ResponseBody
	public Map<String, Object> findCollectionUserId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "collectionUserId") String collectionUserId, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		try {
			Page<List<Map<String, Object>>> caseCollection = caseInfoCollectionService.findPageByParam(collectionUserId, pageable);
			map.put("code", "200");// 查询成功
			map.put("data", caseCollection);
		} catch (Exception e) {
			map.put("code", "500");// 查询失败
			map.put("msg", "查询失败");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据看图识病id userId查询看图识病信息
	 * */
	@RequestMapping(value="/findUserCaseId")
	@ResponseBody
	public Map<String,Object> findUserCaseId(HttpServletRequest res,HttpServletResponse req,
			@RequestParam(name = "id") String id,
			@RequestParam(name = "userId") String userId){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			Map<String,Object> caseInfoEntity = caseInfoService.findInfoByUserId(id, userId);
			map.put("code", "200");//查询成功
			map.put("message", "查询成功");
			map.put("data", caseInfoEntity);
		} catch (Exception e) {
			map.put("code", "500");//查询失败
			map.put("message", "查询失败");
			e.printStackTrace();
		}
		return map;
		
	}
	
	/**
	 * 根据看图识病id、用户id查询回复信息
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
		try {
			Page<List<Map<String,Object>>> replyList = caseInfoReplyService.findReplyByUserId(commentId, userId, pageable);
			map.put("code", "200"); //查询成功
			map.put("message", "查询成功");
			map.put("data", replyList);
		} catch (Exception e) {
			map.put("code", "500");//查询失败
			map.put("message", "查询失败");
		}
		
		
		return map;
	}
	
	/**
	 *   搜索看图识病信息（接口,标题名称）
	 * 
	 */
	@RequestMapping(value = "/findCaseInfoList")
	public Map<String, Object> findCaseInfoList(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "cropsTypeCode", defaultValue = "") String cropsTypeCode,
			@RequestParam(name = "dipTypeCode", defaultValue = "") String dipTypeCode,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "userId", defaultValue = "") String userId,
			@RequestParam(name = "sort", defaultValue = "1") String sort,
			 @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);

		try {
			Page<List<Map<String, Object>>> caseInfoList = caseInfoService.findCaseInfo(name, cropsTypeCode, dipTypeCode, sort, userId, pageable);
			map.put("code", "200");// 成功
			map.put("message", "查询成功");
			map.put("data", caseInfoList);
		} catch (Exception e) {
			map.put("code", "500");// 失败
			map.put("message", "查询失败");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据看图识病id、用户id查询评论信息
	 * 
	 * @param artId
	 * @param userId
	 */
	@RequestMapping(value = "/findCommentByUserId")
	public Map<String, Object> findCommentByUserId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "caseId") String caseId, 
			@RequestParam(name = "userId") String userId,
			@RequestParam(name = "page") Integer page, 
			@RequestParam(name = "size") Integer size) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		try {
			Page<List<Map<String,Object>>> commmentList = caseInfoCommentService.findCommentByUserId(caseId, userId, pageable);
			map.put("code", "200");
			map.put("message", "查询成功");
			map.put("data", commmentList);
			return map;
		} catch (Exception e) {
			map.put("code", "500");// 失败
			map.put("message", "查询失败");
		}
		return map;
	}
}
