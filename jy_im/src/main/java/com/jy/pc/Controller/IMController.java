package com.jy.pc.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jy.pc.Utils.IMUtils;

/**
 * 对外暴露IM相关接口
 * 
 * @author admin
 *
 */
@RequestMapping(value = "/im")
@RestController
public class IMController {
	@Autowired
	IMUtils util;
	
	@RequestMapping(value = "/test")
	public JSONObject test(@RequestParam(name = "userId") String userId) throws IOException {
		return util.createNeteaseCommunicationUser(userId);
	}
}
