package com.jy.pc.Controller;

import java.util.Date;
import java.util.HashMap;
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
import com.jy.pc.Entity.AccountInfoEntity;
import com.jy.pc.Entity.AccountPowerInfoEntity;
import com.jy.pc.Service.AccountInfoService;
import com.jy.pc.Service.AccountPowerInfoService;
import com.jy.pc.Service.PowerInfoService;

@Controller
@RequestMapping(value = "/accountInfo")
@ResponseBody
public class AccountInfoController {

	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired 
	private PowerInfoService powerInfoService;
	@Autowired
	private AccountPowerInfoService accountPowerInfoService;

	// 登录
	@RequestMapping(value = "/login")
	public Map<String, String> login(HttpServletRequest req, HttpServletResponse res,
			HttpSession session,
			@RequestParam(name = "name") String name, @RequestParam(name = "password") String password) {

		Map<String, String> map = new HashMap<String, String>();
		Boolean flag = accountInfoService.checkUser(name, password);
		if (flag) {
			map.put("status", "1");
			map.put("message", "登陆成功");
		} else {
			map.put("status", "0");
			map.put("message", "用户不存在或密码不正确！");
		}
		return map;
	}

	// 查询 分页
	@RequestMapping(value = "/findByName")
	public Map<String, Object> findByName(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "name") String name, @RequestParam(name = "phone") String phone,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "auditStatus") String auditStatus,
			@RequestParam(name = "size") Integer size) {

		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		Page<AccountInfoEntity> accountInfoList = accountInfoService.findListByName(name, phone, auditStatus, pageable);
		map.put("status", "0");// 成功
		map.put("message", "查询成功");
		map.put("data", accountInfoList);
		return map;
	}

	// 账户添加
	@RequestMapping(value = "/add")
	public Map<String, String> save(HttpServletRequest res,HttpSession session,HttpServletResponse req) {
		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("accountInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		AccountInfoEntity accountInfoEntity = jsonObject.toJavaObject(AccountInfoEntity.class);
		Date date = new Date();
		accountInfoEntity.setCreateDate(date);
		accountInfoEntity.setAuditStatus("1");
		
		//角色权限连接表实体
		AccountPowerInfoEntity accountPowerInfoEntity = new AccountPowerInfoEntity();
		AccountInfoEntity accountInfo = accountInfoService.save(accountInfoEntity);
		//添加角色权限关联表
		accountPowerInfoEntity.setAccountId(accountInfo.getId());
		accountPowerInfoEntity.setJurCodel(accountInfoEntity.getJurId());
		powerInfoService.findBId(accountInfoEntity.getJurId());
		accountPowerInfoService.save(accountPowerInfoEntity);
		map.put("status", "0");
		map.put("message", "添加成功");
		return map;
	}

	// 账户修改
	@RequestMapping(value = "/update")
	public Map<String, String> update(HttpServletRequest res, HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("accountInfoEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		AccountInfoEntity accountInfoEntity = jsonObject.toJavaObject(AccountInfoEntity.class);
		Date date = new Date();
		accountInfoEntity.setUpdateDate(date);
		//权限id/名称同步修改
		powerInfoService.findBId(accountInfoEntity.getJurId());
		accountInfoService.update(accountInfoEntity);		
		//修改关联表
		AccountPowerInfoEntity accountPowerInfoEntity = new AccountPowerInfoEntity();
		accountPowerInfoEntity = accountPowerInfoService.findAccountId(accountInfoEntity.getId());
		accountPowerInfoEntity.setJurCodel(accountInfoEntity.getJurId());
		accountPowerInfoService.update(accountPowerInfoEntity);
		map.put("status", "0");
		map.put("message", "修改成功");
		return map;
	}

	// 账户删除
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		//删除关联表
		AccountInfoEntity accountInfoEntity = new AccountInfoEntity();
		accountInfoEntity = accountInfoService.findId(id);
		AccountPowerInfoEntity accountPowerInfoEntity = new AccountPowerInfoEntity();
		accountPowerInfoEntity = accountPowerInfoService.findAccountId(accountInfoEntity.getId());
		accountPowerInfoService.delete(accountPowerInfoEntity.getId());
		
		accountInfoService.delete(id);
		map.put("status", "0");
		map.put("message", "删除成功");
		return map;
	}

	// 条件查询
	@RequestMapping(value = "/findById")
	public Map<String, Object> findById(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		AccountInfoEntity accountInfoEntity = accountInfoService.findId(id);
		if (accountInfoEntity != null) {
			map.put("status", "0");
			map.put("data", accountInfoEntity);
		} else {
			map.put("status", "1");
		}
		return map;
	}

	// 启用/禁用
	@RequestMapping(value = "/enable")
	public Map<String, String> opensulf(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "auditStatus") String auditStatus, @RequestParam(name = "id") String id) {

		Map<String, String> map = new HashMap<String, String>();
		AccountInfoEntity accountInfoEntity = accountInfoService.findId(id);
		accountInfoEntity.setAuditStatus(auditStatus);
		accountInfoEntity.getAuditStatus();
		Date date = new Date();
		if (auditStatus.equals("0")) {
			accountInfoEntity.setAuditStatus("0");
			accountInfoEntity.setUpdateDate(date);
			map.put("status", "0");
			map.put("message", "启用成功");
		} else if (auditStatus.equals("1")) {
			accountInfoEntity.setAuditStatus("1");
			accountInfoEntity.setUpdateDate(date);
			map.put("status", "1");
			map.put("message", "禁用成功");
		}
		accountInfoService.update(accountInfoEntity);
		return map;
	}
	
	// 密码修改
		@RequestMapping(value = "/updatePassword")
		public Map<String, Object> updatePassword(HttpServletRequest res, HttpServletResponse req) {

			Map<String, Object> map = new HashMap<String, Object>();
			String s = res.getParameter("accountInfoEntity");
			JSONObject jsonObject = JSONObject.parseObject(s);
			AccountInfoEntity accountInfoEntity = jsonObject.toJavaObject(AccountInfoEntity.class);
//			AccountInfoEntity accountInfo = accountInfoService.findId(accountInfoEntity.getId());
			Date date = new Date();
			accountInfoEntity.setUpdateDate(date);
//				LimitEntity limitEntity = new LimitEntity();
//				limitEntity = limitService.findId(roleEntity.getLimitId());
//				roleEntity.setLimitName(limitEntity.getName());
			accountInfoService.update(accountInfoEntity);
			map.put("status", "0");
			map.put("message", "修改成功");
//			map.put("data", accountInfo);
			return map;
		}

}
