package com.jy.pc.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.pc.Entity.PostInfoEntity;
import com.jy.pc.Entity.PostPictureEntity;
import com.jy.pc.Service.PostInfoService;
import com.jy.pc.Service.PostPicutreService;

@Controller
@ResponseBody
@RequestMapping(value = "postPicture")
public class postPictureController {

	@Autowired
	private PostInfoService postInfoService;
	@Autowired
	private PostPicutreService postPicutreService;

	//查詢
	@RequestMapping(value = "findId")
	public Map<String, Object> findId(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id, @RequestParam(name = "photoId") String photoId) {
		Map<String, Object> map = new HashMap<String, Object>();
		PostInfoEntity postInfoEntity = postInfoService.findId(id);
		List<PostPictureEntity> postpic = postPicutreService.findByPhotoId(photoId);

		map.put("code", "200");// 查询数据成功
		map.put("data", postInfoEntity);
		map.put("dataPic", postpic);
		return map;
	}
}
