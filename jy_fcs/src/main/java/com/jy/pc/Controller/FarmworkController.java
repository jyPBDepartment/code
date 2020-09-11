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
import org.springframework.web.bind.annotation.RestController;

import com.jy.pc.Entity.AgriculturalEntity;
import com.jy.pc.Entity.FarmworkEntity;
import com.jy.pc.Entity.FarmworkPictureEntity;
import com.jy.pc.Entity.PictureInfoEntity;
import com.jy.pc.Service.AgriculturalService;
import com.jy.pc.Service.ClassificationService;
import com.jy.pc.Service.FarmworkPictureService;
import com.jy.pc.Service.FarmworkService;
import com.jy.pc.Service.PictureInfoService;

@Controller
@ResponseBody
@RequestMapping(value = "farmwork")
@RestController
public class FarmworkController {
	@Autowired
	private FarmworkService farmworkService;
	@Autowired
	private AgriculturalService agriculturalService;
	@Autowired
	private PictureInfoService pictureInfoService;
	@Autowired
	private FarmworkPictureService farmworkPictureService;

	// 我预约的农服列表

	@RequestMapping(value = "/findMyFarm")
	public Map<String, Object> findMyFarm(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "userId") String userId, @RequestParam(name = "user",defaultValue="") String user,
			@RequestParam(name = "status") String status,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<FarmworkEntity> replyList = farmworkService.findMyFarm(userId,status, user, pageable);
		for(FarmworkEntity frame : replyList.getContent()) {
			AgriculturalEntity agriclu =agriculturalService.findBId(frame.getAgriculturalId());
			frame.setAgriName(agriclu.getName());
//			List<FarmworkPictureEntity> farmPic =farmworkPictureService.findPicId(frame.getId());
		}

		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", replyList);
//		map.put("data1", agricluPic);
		return map;
	}

	// 预约我的农服列表
	// 意向用户滑块列表
		@RequestMapping(value = "/findFarmForMe")
		public Map<String, Object> findFarmForMe(HttpServletRequest res, HttpServletResponse req,
				
				@RequestParam(name = "userId") String userId,
				@RequestParam(name = "status") String status,
				@RequestParam(name = "user",defaultValue="") String user,
				@RequestParam(name = "page") Integer page, 
				@RequestParam(name = "size") Integer size
				) {

			Map<String, Object> map = new HashMap<String, Object>();
			//找到农服集合对应的所有预约
			Pageable pageable = new PageRequest(page - 1, size);
			Page<FarmworkEntity> replyList = farmworkService.findFarmForMe(userId,status, user, pageable);
			for(FarmworkEntity frame : replyList.getContent()) {
				   AgriculturalEntity agriclu =agriculturalService.findBId(frame.getAgriculturalId());
				   frame.setAgriName(agriclu.getName());
			}
			map.put("state", "0");// 成功
			map.put("message", "查询成功");
			map.put("data", replyList);
			return map;
		}
		
	
	
	// 获取客户联系方式
	@RequestMapping(value = "/getCustTel")
	public Map<String, Object> getCustTel(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		FarmworkEntity agricultural = farmworkService.findById(id);
		// AgriculturalEntity entity =
		// agriculturalService.findBId(agricultural.getAgriculturalId());
		if (agricultural != null) {
			map.put("state", "0");// 查询成功
			map.put("message", "查询成功");
			map.put("data", agricultural.getContactPhone());
		} else {
			map.put("state", "1");// 查询失败
			map.put("message", "查询失败");
		}
		return map;
	}

	// 获取商家联系方式
	@RequestMapping(value = "/getBusTel")
	public Map<String, Object> getBusTel(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		FarmworkEntity agricultural = farmworkService.findById(id);
		if (agricultural != null) {
			AgriculturalEntity entity = agriculturalService.findBId(agricultural.getAgriculturalId());
			map.put("state", "0");// 查询成功
			map.put("message", "查询成功");
			map.put("data", entity.getContactsPhone());
		} else {
			map.put("state", "1");// 查询失败
			map.put("message", "查询失败");
		}
		return map;
	}

	// 根据预约信息ID取消预约
	@RequestMapping(value = "/cancelById")
	public Map<String, Object> cancelById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		FarmworkEntity agricultural = farmworkService.findById(id);
		if (agricultural != null) {
			map.put("state", "0");// 取消成功
			map.put("message", "取消成功");
		} else {
			map.put("state", "1");// 取消失败
		}
		agricultural.setStatus("3");
		farmworkService.save(agricultural);
		return map;
	}

	// 获取预约详情
	@RequestMapping(value = "/findDetail")
	public Map<String, Object> findDetail(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		FarmworkEntity agricultural = farmworkService.findById(id);
		if (agricultural != null) {
			map.put("state", "0");// 查询数据成功
			map.put("data", agricultural);
		} else {
			map.put("state", "1");// 查询数据失败
		}
		return map;
	}

	// 农活预约添加（接口）
	@RequestMapping(value = "/save")
	public Map<String, String> addPostInfo(HttpServletRequest res, HttpSession session, HttpServletResponse req,
			FarmworkEntity farmworkEntity,@RequestParam(name = "addItem") String[] addItem,
			@RequestParam(name = "id") String id) {
		Map<String, String> map = new HashMap<String, String>();
//		Date date = new Date();
//		farmworkEntity.setBeginDate(date);
		farmworkEntity.setStatus("0");
		farmworkEntity.setAgriculturalId(id);
		farmworkService.save(farmworkEntity);
		for(int i=0;i<addItem.length;i++) {
			PictureInfoEntity pictureInfoEntity = new PictureInfoEntity();
			FarmworkPictureEntity farm = new FarmworkPictureEntity();
			pictureInfoEntity.setPicName(farmworkEntity.getId());
			pictureInfoEntity.setPicUrl(addItem[i]);
			pictureInfoService.save(pictureInfoEntity);
			farm.setFarmworkId(farmworkEntity.getId());
			farm.setPicId(pictureInfoEntity.getId());
			farmworkPictureService.save(farm);			
		}
		map.put("state", "0");
		map.put("message", "添加成功");
		return map;
	}
}
