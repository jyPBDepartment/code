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
import com.jy.pc.Entity.AgriculturalEntity;
import com.jy.pc.Entity.ClassificationEntity;
import com.jy.pc.Service.AgriculturalService;
import com.jy.pc.Service.ClassificationService;

@Controller
@ResponseBody
@RequestMapping(value = "agricultural")
public class AgriculturalController {

	@Autowired
	private AgriculturalService agriculturalService;
	@Autowired
	private ClassificationService classificationService;
	// 农服添加

	@RequestMapping(value = "/save")

	public Map<String, Object> save(HttpServletRequest res, HttpServletResponse req) {

		String s = res.getParameter("agricultural");
		JSONObject jsonObject = JSONObject.parseObject(s);

		Date date = new Date();

		AgriculturalEntity agriculturalEntity = jsonObject.toJavaObject(AgriculturalEntity.class);

		agriculturalEntity.setCreateDate(date);

		ClassificationEntity classificationEntity = new ClassificationEntity();
		classificationEntity = classificationService.findBId(agriculturalEntity.getClassiCode());
		agriculturalEntity.setMachineType(classificationEntity.getCode());

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			agriculturalService.save(agriculturalEntity);
			map.put("state", "0");
			map.put("data", agriculturalEntity);
			map.put("message", "添加成功");
		} catch (Exception e) {
			map.put("state", "1");
			map.put("message", "添加失败");
		}
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
		agriculturalService.update(agriculturalEntity);
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
		agriculturalService.update(agriculturalEntity);
		map.put("state", "0");
		map.put("message", "审核拒绝");
		return map;
	}

	/**
	 * 接口
	 * 
	 * 
	 */
	// 新增农机出售
	@RequestMapping(value = "/addSell")
	public Map<String, String> addSell(HttpServletRequest res, HttpSession session, HttpServletResponse req,
			AgriculturalEntity agriculturalEntity) {
		Map<String, String> map = new HashMap<String, String>();
		Date date = new Date();
		agriculturalEntity.setCreateDate(date);
		agriculturalEntity.setStatus("0");
		agriculturalEntity.setTransactionTypeCode("1");
		agriculturalEntity.setTransactionCategoryCode("1");
		agriculturalService.save(agriculturalEntity);
		map.put("status", "0");
		map.put("message", "添加成功");
		return map;
	}

	// 新增玉米播种
	@RequestMapping(value = "/addSow")
	public Map<String, String> addSow(HttpServletRequest res, HttpSession session, HttpServletResponse req,
			AgriculturalEntity agriculturalEntity) {
		Map<String, String> map = new HashMap<String, String>();
		Date date = new Date();
		agriculturalEntity.setCreateDate(date);
		agriculturalEntity.setStatus("0");
		agriculturalEntity.setTransactionTypeCode("3");
		agriculturalEntity.setTransactionCategoryCode("0");
		agriculturalService.save(agriculturalEntity);
		map.put("status", "0");
		map.put("message", "添加成功");
		return map;
	}

	// 新增粮食收购
	@RequestMapping(value = "/addAcquisition")
	public Map<String, String> addAcquisition(HttpServletRequest res, HttpSession session, HttpServletResponse req,
			AgriculturalEntity agriculturalEntity) {
		Map<String, String> map = new HashMap<String, String>();
		Date date = new Date();
		agriculturalEntity.setCreateDate(date);
		agriculturalEntity.setStatus("0");
		agriculturalEntity.setTransactionTypeCode("0");
		agriculturalService.save(agriculturalEntity);
		map.put("status", "0");
		map.put("message", "添加成功");
		return map;
	}

	// 新增粮食出售
	@RequestMapping(value = "/addGrainSales")
	public Map<String, String> addGrainSalesn(HttpServletRequest res, HttpSession session, HttpServletResponse req,
			AgriculturalEntity agriculturalEntity) {
		Map<String, String> map = new HashMap<String, String>();
		Date date = new Date();
		agriculturalEntity.setCreateDate(date);
		agriculturalEntity.setStatus("0");
		agriculturalEntity.setTransactionTypeCode("1");
		agriculturalService.save(agriculturalEntity);
		map.put("status", "0");
		map.put("message", "添加成功");
		return map;
	}

	// 不同状态加载不同的发布
	@RequestMapping(value = "/findStatusPass")
	public Map<String, Object> findStatusPass(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") String status) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<AgriculturalEntity> agri = agriculturalService.findStatusPass(status);
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
