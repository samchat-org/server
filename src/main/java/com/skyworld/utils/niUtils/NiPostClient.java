package com.skyworld.utils.niUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.skyworld.common.Constant;
import com.skyworld.utils.CommonUtil;

public class NiPostClient {

	private static Log log = LogFactory.getLog(NiPostClient.class);

	private static final String appKey = "a95830f52659eec125b54bc1b88f05b2";

	private static final String appSecret = "bd26b055314f";

	public static String post(String actionUrl, Map<String, String> param, Timestamp cur) throws Exception {

		if (param.size() == 0) {
			throw new Exception(" param is empty !");
		}
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(actionUrl);

		String nonce = CommonUtil.getRandom();
		String curTime = String.valueOf(cur.getTime() / 1000L);
		String checkSum = NiCheckSumBuilder.getCheckSum(appSecret, nonce, curTime);// 计算CheckSum的java代码

		// 设置请求的header
		httpPost.addHeader("AppKey", appKey);
		httpPost.addHeader("Nonce", nonce);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		// 设置请求的参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Iterator<Map.Entry<String, String>> itr = param.entrySet().iterator(); itr.hasNext();) {
			Map.Entry<String, String> entry = itr.next();
			nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Constant.CHARSET));

		// 执行请求
		String body = null;
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("云信服务访问异常:" + response.getStatusLine().getReasonPhrase());
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				body = EntityUtils.toString(entity, Constant.CHARSET);
			}
			EntityUtils.consume(entity);

		} finally {
			if (response != null) {
				response.close();
			}
		}
		return body;
	}
}
