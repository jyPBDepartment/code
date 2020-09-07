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
import com.jy.pc.Entity.ClassificationEntity;
import com.jy.pc.Entity.KeyWordEntity;
import com.jy.pc.POJO.DictPO;
import com.jy.pc.Service.ClassificationService;
import com.jy.pc.Service.KeyWordService;

@Controller
@RequestMapping(value = "/keyWord")
@ResponseBody
public class KeyWordController {
	@Autowired
	private KeyWordService keyWordService;
	@Autowired
	private ClassificationService classificationService;

	//农机顶部滑块接口
	@RequestMapping(value = "/findMachineDict")
	public Map<String, Object> findMachineDict(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<DictPO> dictList = new ArrayList<DictPO>();
		DictPO dict1 = new DictPO("0","收购");
		DictPO dict2 = new DictPO("1","出售");
		DictPO dict3 = new DictPO("2","出租");
		dictList.add(dict1);
		dictList.add(dict2);
		dictList.add(dict3);
		map.put("state", "0");// 成功
		map.put("data", dictList);
		return map;
	}
	
	//类型接口 
	@RequestMapping(value = "/findCropDict")
	public Map<String, Object> findCropDict(HttpServletRequest res, HttpServletResponse req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<DictPO> dictList = new ArrayList<DictPO>();
		DictPO dict1 = new DictPO("0","收购");
		DictPO dict2 = new DictPO("1","出售");
		DictPO dict3 = new DictPO("2","出租");
		DictPO dict4 = new DictPO("3","播种");
		DictPO dict5 = new DictPO("4","植保");
		DictPO dict6 = new DictPO("5","收割");
		dictList.add(dict1);
		dictList.add(dict2);
		dictList.add(dict3);
		dictList.add(dict4);
		dictList.add(dict5);
		dictList.add(dict6);
		map.put("state", "0");// 成功
		map.put("data", dictList);
		return map;
	}
	
	//类别接口 
		@RequestMapping(value = "/findTypeDict")
		public Map<String, Object> findTypeDict(HttpServletRequest res, HttpServletResponse req) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<DictPO> dictList = new ArrayList<DictPO>();
			DictPO dict1 = new DictPO("0","玉米");
			DictPO dict2 = new DictPO("1","农机");
			DictPO dict3 = new DictPO("2","水稻");
			DictPO dict4 = new DictPO("3","高粱");
			DictPO dict5 = new DictPO("4","黄豆");
			dictList.add(dict1);
			dictList.add(dict2);
			dictList.add(dict3);
			dictList.add(dict4);
			dictList.add(dict5);
			map.put("state", "0");// 成功
			map.put("data", dictList);
			return map;
		}

	// 查询 分页
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<KeyWordEntity> keyWordList = keyWordService.findListByName(name, pageable);
		map.put("state", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", keyWordList);
		return map;
	}

	// 关键词添加
	@RequestMapping(value = "/add")
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("keyWordEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		KeyWordEntity keyWordEntity = jsonObject.toJavaObject(KeyWordEntity.class);
		Date date = new Date();
		keyWordEntity.setCreateDate(date);
		keyWordEntity.setAuditStatus("1");
		ClassificationEntity classificationEntity = new ClassificationEntity();
		classificationEntity = classificationService.findBId(keyWordEntity.getParentCode());
		keyWordEntity.setParentName(classificationEntity.getName());
		keyWordService.save(keyWordEntity);
		map.put("status", "0");
		map.put("message", "添加成功");
		return map;
	}

	// 关键词修改
	@RequestMapping(value = "/update")
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("keyWordEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		KeyWordEntity keyWordEntity = jsonObject.toJavaObject(KeyWordEntity.class);
		Date date = new Date();
		keyWordEntity.setUpdateDate(date);
		ClassificationEntity classificationEntity = new ClassificationEntity();
		classificationEntity = classificationService.findBId(keyWordEntity.getParentCode());
		keyWordEntity.setParentName(classificationEntity.getName());
		keyWordService.update(keyWordEntity);
		map.put("status", "0");
		map.put("message", "修改成功");
		return map;
	}

	// 关键词删除
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		keyWordService.delete(id);
		map.put("status", "0");
		map.put("message", "删除成功");
		return map;
	}

	// 条件查询
	@RequestMapping(value = "/findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		KeyWordEntity moduleInfoEntity = keyWordService.findId(id);
		if (moduleInfoEntity != null) {
			map.put("status", "0");
			map.put("data", moduleInfoEntity);
		} else {
			map.put("status", "1");
		}
		return map;
	}

	// 启用/禁用
	@RequestMapping(value = "/enable")
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "auditStatus") String auditStatus, @RequestParam(name = "id") String id,
			@RequestParam(name = "updateUser") String updateUser) {

		Map<String, String> map = new HashMap<String, String>();
		KeyWordEntity keyWordEntity = keyWordService.findId(id);
		keyWordEntity.setAuditStatus(auditStatus);
		keyWordEntity.getAuditStatus();
		Date date = new Date();
		boolean result = true;
		if (auditStatus.equals("0")) {
			keyWordEntity.setAuditStatus("0");
			keyWordEntity.setUpdateDate(date);
			keyWordEntity.setUpdateUser(updateUser);
			map.put("state", "0");
			map.put("message", "启用成功");
		} else if (auditStatus.equals("1")) {
			keyWordEntity.setAuditStatus("1");
			keyWordEntity.setUpdateDate(date);
			keyWordEntity.setUpdateUser(updateUser);
			map.put("state", "1");
			map.put("message", "禁用成功");
			result = false;
		}
		keyWordService.enable(keyWordEntity, result);
		return map;
	}
}
