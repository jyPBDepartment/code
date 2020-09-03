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
import com.jy.pc.Entity.AgriculturalEntity;
import com.jy.pc.Entity.AgriculturalPictureEntity;
import com.jy.pc.Entity.PictureInfoEntity;
import com.jy.pc.Service.AgriculturalPictureService;
import com.jy.pc.Service.AgriculturalService;
import com.jy.pc.Service.PictureInfoService;

@Controller
@ResponseBody
@RequestMapping(value = "agricultural")
public class AgriculturalController {

	@Autowired
	private AgriculturalService agriculturalService;
	@Autowired
	private AgriculturalPictureService agriculturalPictureService;
	@Autowired
	private PictureInfoService pictureInfoService;

	@RequestMapping(value = "/findDetail")
	public Map<String, Object> findDetail(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		AgriculturalEntity agricultural = agriculturalService.findBId(id);
		if (agricultural != null) {
			map.put("status", "0");// 查询数据成功
			map.put("data", agricultural);
		} else {
			map.put("status", "1");// 查询数据失败
		}
		return map;
	}
	// 农服添加(接口)
		@RequestMapping(value = "/save")
		public Map<String, Object> save(HttpServletRequest res, HttpServletResponse req,
				AgriculturalEntity agriculturalEntity,@RequestParam(name = "addItem") String[] addItem){
			Date date = new Date();
			agriculturalEntity.setCreateDate(date);// 设置创建时间
			agriculturalEntity.setStatus("0");// 初始化值为待审核0
			Map<String, Object> map = new HashMap<String, Object>();
			agriculturalService.save(agriculturalEntity);
			for(int i=0;i<addItem.length;i++) {
				PictureInfoEntity pictureInfoEntity = new PictureInfoEntity();
				AgriculturalPictureEntity agriculturalPictureEntity = new AgriculturalPictureEntity();
				pictureInfoEntity.setPicName(agriculturalEntity.getName());
				pictureInfoEntity.setPicUrl(addItem[i]);
				pictureInfoService.save(pictureInfoEntity);
				agriculturalPictureEntity.setAgrId(agriculturalEntity.getId());
				agriculturalPictureEntity.setPicId(pictureInfoEntity.getId());
				agriculturalPictureService.save(agriculturalPictureEntity);
			}
			map.put("state", "0");
			map.put("data", agriculturalEntity);
			map.put("message", "添加成功");
			return map;
		}

	/**
	 * 根据类型查询发布信息（接口）
	 * 
	 * @param type 包括：农服类0，粮食买卖1，农机类2
	 */
	@RequestMapping(value = "/findPubPage")
	public Map<String, Object> findPubPage(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "type") String type, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<AgriculturalEntity> agriculturalList = agriculturalService.findListByType(type, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", agriculturalList);
		return map;
	}

	/**
	 * 搜索发布信息中农服信息（接口,标题名称）
	 * 
	 * @param type 包括：农服类0，粮食买卖1，农机类2
	 */
	@RequestMapping(value = "/findAgriInfo")
	public Map<String, Object> findAgriInfo(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name",defaultValue="") String name, @RequestParam(name = "type", defaultValue = "0") String type,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<AgriculturalEntity> agriculturalList = agriculturalService.findAgriInfo(name, type, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", agriculturalList);
		return map;
	}
	
	/**
	 * 搜索我的发布信息（接口,标题名称）
	 * 
	 * @param type 包括：农服类0，粮食买卖1，农机类2
	 */
	@RequestMapping(value = "/findMyPublication")
	public Map<String, Object> findMyPublication(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status")String status,
			@RequestParam(name = "type", defaultValue = "0") String type,			
			@RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<AgriculturalEntity> agriculturalList = agriculturalService.findMyPublication(status, type, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", agriculturalList);
		return map;
	}

	/**
	 * 搜索发布信息中农服信息（接口，类型、类别）
	 * 
	 * @param type 包括：农服类0，粮食买卖1，农机类2
	 */
	@RequestMapping(value = "/findAgriType")
	public Map<String, Object> findAgriType(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name",defaultValue="") String name,
			@RequestParam(name = "transactionTypeCode",defaultValue="") String transactionTypeCode,
			@RequestParam(name = "transactionCategoryCode",defaultValue="") String transactionCategoryCode,
			@RequestParam(name = "type", defaultValue = "0") String type, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<AgriculturalEntity> agriculturalList = agriculturalService.findAgriType(transactionTypeCode,
				transactionCategoryCode, type, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", agriculturalList);
		return map;
	}

	// 农服模糊查询与分页
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "status") String status,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<AgriculturalEntity> AgriculturalList = agriculturalService.findListByName(name, status, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", AgriculturalList);
		return map;
	}

	// 农服模糊查询标题名称
	@RequestMapping(value = "/findListByAgrName")
	public Map<String, Object> findListByAgrName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<AgriculturalEntity> Agricultural = agriculturalService.findListByAgrName(name);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", Agricultural);
		return map;
	}

	// 农服最近三条查询
	@RequestMapping(value = "/findListByTime")
	public Map<String, Object> findListByTime(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<AgriculturalEntity> Agricultur = agriculturalService.findListByTime();
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", Agricultur);
		return map;
	}

	// 农服查看详情
		@RequestMapping(value = "findById")
		public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
				@RequestParam(name = "id") String id) {
			Map<String, Object> map = new HashMap<String, Object>();
			AgriculturalEntity agricultural = agriculturalService.findBId(id);
			String[] agrPic = agricultural.getUrl().split(","); 
			map.put("state", "0");// 查询数据成功
			map.put("data", agricultural);
			map.put("data1", agrPic);
			return map;
		}

	// 根据id查询农服信息详情
	@RequestMapping(value = "findId")
	public Map<String, Object> findId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		AgriculturalEntity agricultural = agriculturalService.findId(id);
		if (agricultural != null) {
			map.put("state", "0");// 查询数据成功
			map.put("data", agricultural);
		} else {
			map.put("state", "1");// 查询数据失败
		}
		return map;
	}

	// 审核通过
	@RequestMapping(value = "/passPostInfo")
	public Map<String, String> passPostInfo(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("agriculturalEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		AgriculturalEntity agriculturalEntity = jsonObject.toJavaObject(AgriculturalEntity.class);
		agriculturalEntity.setStatus("1");
		agriculturalService.audit(agriculturalEntity, true);
		map.put("state", "0");
		map.put("message", "审核通过");
		return map;
	}

	// 审核拒绝
	@RequestMapping(value = "/refusePostInfo")
	public Map<String, String> refusePostInfo(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("agriculturalEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		AgriculturalEntity agriculturalEntity = jsonObject.toJavaObject(AgriculturalEntity.class);
		agriculturalEntity.setStatus("2");
		agriculturalService.audit(agriculturalEntity, false);
		map.put("state", "0");
		map.put("message", "审核拒绝");
		return map;
	}

	/**
	 * 根据类型查询发布信息
	 * 
	 * @param type 包括：农服类0，粮食买卖1，农机类2
	 */
	@RequestMapping(value = "/findStatusPass")
	public Map<String, Object> findStatusPass(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "type") String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<AgriculturalEntity> agri = agriculturalService.findListByType(type);
		System.out.println("aaa");
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", agri);
		return map;
	}

	/**
	 * 根据类型查询发布信息(前三条)
	 * 
	 * @param type 包括：农服类0，粮食买卖1，农机类2
	 */
	@RequestMapping(value = "/findNewInfo")
	public Map<String, Object> findNewInfo(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "type",defaultValue = "0") String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<AgriculturalEntity> agri = agriculturalService.findListByTime(type);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", agri);
		return map;
	}

	// 获取农服预约信息
	@RequestMapping(value = "/findAppointment")
	public Map<String, Object> findAppointment(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<AgriculturalEntity> agricul = agriculturalService.findAppointment();
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", agricul);
		return map;
	}
}
