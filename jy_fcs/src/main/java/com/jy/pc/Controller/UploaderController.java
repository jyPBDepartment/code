package com.jy.pc.Controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.ContentType;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jy.pc.Utils.FtpUtil;
import com.jy.pc.Utils.IDUtils;

import net.coobird.thumbnailator.Thumbnails;

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
			byte[] imgBytes = uploadFile.getBytes();
			byte[] resultBytes = compressPicForScale(imgBytes, 1024);
//			System.out.println("压缩前：" + uploadFile.getSize());
			InputStream inputStream = new ByteArrayInputStream(resultBytes);
			MultipartFile file = new MockMultipartFile(ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
//			System.out.println("压缩后：" + file.getSize());
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
			InputStream input = file.getInputStream();

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

	/**
	 * 自动调节精度(经验数值)
	 *
	 * @param size 源图片大小
	 * @return 图片压缩质量比
	 */
	private static double getAccuracy(long size) {
		double accuracy;
		if (size < 900) {
			accuracy = 0.85;
		} else if (size < 2047) {
			accuracy = 0.6;
		} else if (size < 3275) {
			accuracy = 0.44;
		} else {
			accuracy = 0.4;
		}
		return accuracy;
	}

	public static byte[] compressPicForScale(byte[] imageBytes, long desFileSize) {
		if (imageBytes == null || imageBytes.length <= 0 || imageBytes.length < desFileSize * 1024) {
			// 若传入图片已小于目标大小，则不作处理
			return imageBytes;
		}
		long srcSize = imageBytes.length;
		double accuracy = getAccuracy(srcSize / 1024);
		try {
			while (imageBytes.length > desFileSize * 1024) {
				ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);
				Thumbnails.of(inputStream).scale(accuracy).outputQuality(accuracy).toOutputStream(outputStream);
				imageBytes = outputStream.toByteArray();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageBytes;
	}

}
