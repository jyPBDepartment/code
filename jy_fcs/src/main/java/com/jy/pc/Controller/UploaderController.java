package com.jy.pc.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jy.pc.Utils.FtpUtil;
import com.jy.pc.Utils.IDUtils;

@Controller
@RequestMapping(value = "/upload")
public class UploaderController {

	@Value("${FTP.ADDRESS}")
	private String host;
	// 端口
	@Value("${FTP.PORT}")
	private int port;
	// ftp用户名
	@Value("${FTP.USERNAME}")
	private String userName;
	// ftp用户密码
	@Value("${FTP.PASSWORD}")
	private String passWord;
	// 文件在服务器端保存的主目录
	@Value("${FTP.BASEPATH}")
	private String basePath;
	// 访问图片时的基础url
	@Value("${IMAGE.BASE.URL}")
	private String baseUrl;

	@RequestMapping(value = "/uploadImg")
	@ResponseBody
	public Map<String, Object> uploadImg(@RequestParam(value = "file", required = false) MultipartFile uploadFile,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 1、给上传的图片生成新的文件名
			// 1.1获取原始文件名
			String oldName = uploadFile.getOriginalFilename();
			// 1.2使用IDUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
			String newName = IDUtils.genImageName();
			newName = newName + oldName.substring(oldName.lastIndexOf("."));
			// 1.3生成文件在服务器端存储的子目录
			String filePath = new DateTime().toString("/yyyy/MM/dd");

			// 2、把前端输入信息，包括图片的url保存到数据库
//            User user = new User();
//            user.setUsername(username);
//            user.setPassword(password);
//            user.setPicture(baseUrl + filePath + "/" + newName);
//            userService.insertUser(user);

			// 3、把图片上传到图片服务器
			// 3.1获取上传的io流
			InputStream input = uploadFile.getInputStream();

			// 3.2调用FtpUtil工具类进行上传
			boolean result = FtpUtil.uploadFile(host, port, userName, passWord, basePath, filePath, newName, input);
			if (result) {
				map.put("url", baseUrl + filePath + "/" + newName);
			} else {

			}
			System.out.println("上传结果打印" + result);
		} catch (IOException e) {

		}

		return map;
	}

}
