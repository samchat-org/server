package com.samchat.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samchat.common.beans.auto.json.appserver.profile.GoogleplaceAutocomplete_res;
import com.samchat.common.beans.manual.common.InternetProxyBean;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.db.SysParamCodeDbEnum;

public class GooglePlaceUtil {

	private static Log log = LogFactory.getLog(GooglePlaceUtil.class);

	private static String AUTO_COMPLETE_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json?";

	private static String getBaseUrl() {
		String key = CommonUtil.getSysConfigStr(SysParamCodeDbEnum.GOOGLE_PLACES_KEY.getParamCode());
		return AUTO_COMPLETE_URL + "key=" + key;
	}

	public static GoogleplaceAutocomplete_res autocomplete(String keyCotent){
		
		return (GoogleplaceAutocomplete_res)InternetProxyUtil.balanceInternetProxy(GooglePlaceUtil.class, "autocomplete", new Object[]{keyCotent});
	}

	public static GoogleplaceAutocomplete_res autocomplete(String keyCotent, InternetProxyBean pb) throws Exception {
		
		String url = HttpclientUrlUtil.encodeQuery(getBaseUrl() + "&input=" + keyCotent);
		CloseableHttpClient httpClient = HttpClients.createDefault();

		RequestConfig config = RequestConfig.custom().setProxy(new HttpHost(pb.getProxyUrl(), pb.getProxyPort()))
				.build();

		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(config);
		httpPost.addHeader("Content-Type", "application/json;charset=utf-8;");

		String body = null;
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("google places 服务访问异常:" + response.getStatusLine().getReasonPhrase());
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				body = EntityUtils.toString(entity, Constant.CHARSET);
				log.info("google res body:" + body);
			}
			EntityUtils.consume(entity);

		} finally {
			if (response != null) {
				response.close();
			}
		}
		ObjectMapper om = ThreadLocalUtil.getAppObjectMapper();
		GoogleplaceAutocomplete_res gac = om.readValue(body, GoogleplaceAutocomplete_res.class);
		if (!"OK".equals(gac.getStatus())) {
			throw new Exception("google status:" + gac.getStatus());
		}
		return gac;
	}

	public static void main(String args[]) throws Exception {
		 autocomplete("abcd");
	}
}
