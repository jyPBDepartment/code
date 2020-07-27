package com.jy.pc.Controller;

import java.util.Date;
import java.util.HashMap;
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
import com.jy.pc.Entity.ClassificationEntity;
import com.jy.pc.Service.AgriculturalService;
import com.jy.pc.Service.ClassificationService;

@Controller
@ResponseBody
@RequestMapping(value="agricultural")
public class AgriculturalController {

	@Autowired
	private AgriculturalService agriculturalService;
	@Autowired
	private ClassificationService classificationService;
	//农服添加

		@RequestMapping(value="/save")
		
		public Map<String, Object> save(HttpServletRequest res,HttpServletResponse req) {
			
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
				map.put("message","添加成功");
			} catch (Exception e) {
				map.put("state", "1");
				map.put("message","添加失败");
			}
			return map;
		}
	//	 农服模糊查询与分页
		@RequestMapping(value = "/findByName")
		public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
				@RequestParam(name = "name") String name,
				@RequestParam(name = "page") Integer page,
				@RequestParam(name = "size") Integer size) {
			Map<String, Object> map = new HashMap<String, Object>();
			Pageable pageable = new PageRequest(page - 1, size);
			Page<AgriculturalEntity> AgriculturalList = agriculturalService.findListByName(name, pageable);
			map.put("state", "0");// 成功
			map.put("message", "查询成功");
			map.put("data", AgriculturalList);
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
//		// 启用禁用
//		@RequestMapping(value = "/enable")
//		public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
//				@RequestParam(name = "status") String status, @RequestParam(name = "id") String id) {
//			Map<String, String> map = new HashMap<String, String>();
//			AgriculturalEntity agriculturalEntity = agriculturalService.findBId(id);
//			agriculturalEntity.setStatus(status);
//			agriculturalEntity.getStatus();
//			if (status.equals("0")) {
//				agriculturalEntity.setStatus("0");
//				map.put("state", "0");
//				map.put("message", "待审核");
//			} else if (status.equals("1")) {
//				agriculturalEntity.setStatus("1");
//				map.put("state", "1");
//				map.put("message", "审核通过");
//			}
//			else if (status.equals("2")) {
//				agriculturalEntity.setStatus("2");
//				map.put("state", "2");
//				map.put("message", "审核拒绝");
//			}
//			else if (status.equals("3")) {
//				agriculturalEntity.setStatus("3");
//				map.put("state", "3");
//				map.put("message", "预约中");
//			}else if (status.equals("4")) {
//				agriculturalEntity.setStatus("4");
//				map.put("state", "4");
//				map.put("message", "已完成");
//			}
//			agriculturalService.update(agriculturalEntity);
//			return map;
//		}
//		
}
