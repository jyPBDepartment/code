package com.jy.pc.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Component
public class IMUtils {
	private Logger logger = LogManager.getLogger(this.getClass());

	@Value("${netease.appKey}")
	private String appKey;

	@Value("${netease.appSecret}")
	private String appSecret;

	/**
	 * 创建网易云信用户
	 * 
	 * @param : accid 用户环信id/token信息
	 * @return :
	 */
	public JSONObject createNeteaseCommunicationUser(String neteaseCommunicationAccountId) throws IOException {

		logger.info("**************************   创建网易云信用户 **************************");
		HttpClient httpClient = HttpClientBuilder.create().build();
		String url = "https://api.netease.im/nimserver/user/create.action";
		HttpPost httpPost = new HttpPost(url);
		String nonce = UUID.randomUUID().toString();
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		// 参考 计算CheckSum的java代码
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
		logger.info(appSecret);
		logger.info(appKey);
		// 设置请求的header
		httpPost.addHeader("AppKey", appKey);
		httpPost.addHeader("Nonce", nonce);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		// 设置请求的参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", neteaseCommunicationAccountId));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
		// 执行请求
		HttpResponse response = httpClient.execute(httpPost);
		JSONObject existJsonObject = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
		return existJsonObject;
	}

	/**
	 * 更新网易云信用户token
	 * 
	 * @param : accid 用户环信id/token信息
	 * @return :
	 */
	public JSONObject updateNeteaseCommunicationUserToken(String neteaseCommunicationAccountId) throws IOException {

		logger.info("**************************   更新网易云信用户token **************************");
		HttpClient httpClient = HttpClientBuilder.create().build();
		String url = "https://api.netease.im/nimserver/user/refreshToken.action";
		HttpPost httpPost = new HttpPost(url);
		String nonce = UUID.randomUUID().toString();
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		// 参考 计算CheckSum的java代码
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);

		// 设置请求的header
		httpPost.addHeader("AppKey", appKey);
		httpPost.addHeader("Nonce", nonce);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		// 设置请求的参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", neteaseCommunicationAccountId));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

		// 执行请求
		HttpResponse response = httpClient.execute(httpPost);
		JSONObject existJsonObject = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
		return existJsonObject;
	}
}
