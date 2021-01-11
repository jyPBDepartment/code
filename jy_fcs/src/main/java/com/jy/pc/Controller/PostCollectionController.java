package com.jy.pc.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.pc.Entity.PostInfoEntity;
import com.jy.pc.Service.PostInfoService;

//帖子收藏管理
@Controller
@RequestMapping(value = "/postCollection")
@ResponseBody
public class PostCollectionController {

	@Autowired
	public PostInfoService postInfoService;

	/**
	 * 我的-我的收藏
	 * 
	 * @Param userId 用户Id
	 */
	@RequestMapping("/postByUserId")
	@ResponseBody
	public Map<String, Object> postByUserId() {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List<PostInfoEntity> postInfoEntity = postInfoService.findByUserId();
			map.put("code", "200");// 查询成功
			map.put("data", postInfoEntity);
		} catch (Exception e) {
			map.put("code", "201");// 查询失败
			map.put("msg", e.getMessage());
		}
		return map;
	}
}
