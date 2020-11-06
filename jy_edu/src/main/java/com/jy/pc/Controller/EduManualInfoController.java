package com.jy.pc.Controller;

import java.util.ArrayList;
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
import com.jy.pc.Entity.EduManualInfoEntity;
import com.jy.pc.Entity.EduManualLabelInfoEntity;
import com.jy.pc.Entity.EduUserManualEntity;
import com.jy.pc.Entity.EduVocationInfoEntity;
import com.jy.pc.Service.EduManualInfoService;
import com.jy.pc.Service.EduManualLabelService;
import com.jy.pc.Service.EduUserManualService;
import com.jy.pc.Service.EduVocationInfoService;
import com.jy.pc.VO.ManualListVO;
/**
 * 手册Conteoller
 * */
@Controller
@RequestMapping(value = "/manualInfo")
public class EduManualInfoController {
	@Autowired
	private EduManualInfoService eduManualInfoService;
	@Autowired
	private EduUserManualService eduUserManualService;//用户手册关联业务层
	
	//移动端-首页-热门课程加载
	@RequestMapping(value = "/getListByReading")
	@ResponseBody
	public Map<String, Object> getListByReading(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<ManualListVO> result = new ArrayList<ManualListVO>();
			List<EduManualInfoEntity> manuaList = eduManualInfoService.getListByReading();
			for(EduManualInfoEntity entity : manuaList) {
				ManualListVO vo = new ManualListVO(entity);
				result.add(vo);
			}
			map.put("code", "200");// 成功
			map.put("message", "查询成功");
			map.put("data", result);
		} catch (Exception e) {
			map.put("code", "201");// 成功
			map.put("message", e.getMessage());
		}
		return map;
	}
	
	// 查询 分页
	@RequestMapping(value = "/findByName")
	@ResponseBody
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "title") String title,@RequestParam(name = "createBy") String createBy,
			@RequestParam(name = "vocationId") String vocationId,@RequestParam(name = "labelId") String labelId,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<EduManualInfoEntity> manuaList = eduManualInfoService.findListByName(title, createBy, vocationId, labelId, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", manuaList);
		return map;
	}

	// 添加
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("eduManualInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		EduManualInfoEntity eduManualInfoEntity = jsonObject.toJavaObject(EduManualInfoEntity.class);
		EduManualLabelInfoEntity label = new EduManualLabelInfoEntity();
		label.setId(eduManualInfoEntity.getLabelId());
		EduVocationInfoEntity vocation = new EduVocationInfoEntity();
		vocation.setId(eduManualInfoEntity.getVocationId());
		eduManualInfoEntity.setVocation(vocation);
		eduManualInfoEntity.setLabel(label);
		Date date = new Date();
		eduManualInfoEntity.setCreateDate(date);
		eduManualInfoEntity.setStatus(1);
		eduManualInfoEntity.setManualType(0);
		eduManualInfoService.save(eduManualInfoEntity);
		map.put("state", "0");
		map.put("message", "添加成功");
		return map;
	}
				
	// 修改
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("eduManualInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		EduManualInfoEntity eduManualInfoEntity = jsonObject.toJavaObject(EduManualInfoEntity.class);
		Date date = new Date();
		EduVocationInfoEntity vocation = new EduVocationInfoEntity();
		vocation.setId(eduManualInfoEntity.getVocationId());
		EduManualLabelInfoEntity label = new EduManualLabelInfoEntity();
		label.setId(eduManualInfoEntity.getLabelId());
		eduManualInfoEntity.setVocation(vocation);
		eduManualInfoEntity.setLabel(label);
		eduManualInfoEntity.setUpdateDate(date);
		eduManualInfoService.update(eduManualInfoEntity);
		map.put("state", "0");
		map.put("message", "修改成功");
		return map;
	}

	// 删除
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		eduManualInfoService.delete(id);
		map.put("state", "0");
		map.put("message", "删除成功");			
		return map;
	}
				
	//通过id查询
	@RequestMapping(value = "/findById")
	@ResponseBody
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		EduManualInfoEntity eduManualInfoEntity = eduManualInfoService.findId(id);
		map.put("state", "0");
		map.put("data", eduManualInfoEntity);
		return map;
	}
				
	// 启用/禁用
	@RequestMapping(value = "/enable")
	@ResponseBody
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") Integer status, @RequestParam(name = "id") String id,
			@RequestParam(name = "updateUser") String updateUser) {
		Date date = new Date();
		Map<String, String> map = new HashMap<String, String>();
		EduManualInfoEntity eduManualInfoEntity = eduManualInfoService.findId(id);
		eduManualInfoEntity.setStatus(status);
		eduManualInfoEntity.setUpdateDate(date);
		eduManualInfoEntity.setUpdateBy(updateUser);
		boolean result = true;
		//启用
		if (status.equals(0)) {
			map.put("state", "0");
			map.put("message", "启用成功");	
		}
		//禁用
		if (status.equals(1)) {
			map.put("state", "1");
			map.put("message", "禁用成功");
			result = false;
		}
		eduManualInfoService.enable(eduManualInfoEntity,result);
		return map;
	}
	
	/**
	 *	用户新增手册收藏
	 *  @param userId 用户Id
	 * 
	 * */
	@RequestMapping(value = "/saveUserManualInfo")
	@ResponseBody
	public Map<String, String> saveUserManualInfo(HttpServletRequest res, HttpServletResponse req,
			EduUserManualEntity eduUserManualEntity,@RequestParam(name = "isCancel") Integer isCancel) {
		Map<String, String> map = new HashMap<String, String>();
		if(isCancel == 0) {
			Date date = new Date();
			eduUserManualEntity.setCollectionDate(date);//设置收藏时间
			eduUserManualService.save(eduUserManualEntity); 
			try {
				map.put("code", "200");
				map.put("msg", "收藏成功");
			}catch(Exception e) {
				map.put("code", "201");
				map.put("msg", "收藏失败");
			}
		}else {
			EduUserManualEntity eduUserManual = eduUserManualService.findManualInfoId(eduUserManualEntity.getManualInfoId());
			eduUserManualService.delete(eduUserManual.getId());
		}
		return map;
	}
	
	/**
	 * 手册详情接口
	 * */
	//通过id查询
	@RequestMapping(value = "/findManualInfoId")
	@ResponseBody
	public Map<String, Object> findManualInfoId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		EduManualInfoEntity eduManualInfoEntity = eduManualInfoService.findId(id);
		EduUserManualEntity eduUserManualEntity = eduUserManualService.findManualInfoId(id);
		eduManualInfoEntity.setStudyNum(eduManualInfoEntity.getStudyNum() + 1);
		eduManualInfoService.update(eduManualInfoEntity);
		map.put("code", "200");
		map.put("data", eduManualInfoEntity);
		map.put("dataUserManual", eduUserManualEntity);
		return map;
	}
}
