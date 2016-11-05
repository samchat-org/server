package com.samchat.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.samchat.common.beans.manual.common.InternetProxyBean;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
import com.samchat.common.exceptions.SysException;

public class InternetProxyUtil {
	
	private static Logger log = Logger.getLogger(InternetProxyUtil.class);

	private static List<InternetProxyBean> getProxyList() {
		String[] proxyArr = CommonUtil.getSysConfigStr(SysParamCodeDbEnum.INTERNET_PROXY.getParamCode()).split(",");
		List<InternetProxyBean> proxyList = new ArrayList<InternetProxyBean>();
		for (String proxy : proxyArr) {
			String[] proxyDtl = proxy.split(":");
			if (proxyDtl.length != 2) {
				throw new SysException(proxy + ":please, follow the format of proxy: 'url:port'");
			}
			InternetProxyBean ipx = new InternetProxyBean();
			ipx.setProxyUrl(proxyDtl[0].trim());
			ipx.setProxyPort(Integer.parseInt(proxyDtl[1].trim()));
			proxyList.add(ipx);
		}
		return proxyList;
	}

	private static Object balanceInternetProxy(List<InternetProxyBean> iplist, Class clazz, String method,
			Object[] param) {
		int size = iplist.size();
		if (size == 0) {
			throw new SysException("InternetProxy is empty");
		}
		long postion = System.currentTimeMillis() % size;
		InternetProxyBean ipb = iplist.get((int) postion);
		try {
			int len = param.length + 1;
			Class[] paramType = new Class[len];
			Object [] paramValue = new Object[len];
			for (int i = 0; i < len - 1; i++) {
				paramType[i] = param[i].getClass();
				paramValue[i] = param[i];
			}
			paramType[len - 1] = ipb.getClass();
			paramValue[len - 1] = ipb;
			return clazz.getMethod(method, paramType).invoke(null, paramValue);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			iplist.remove(ipb);
			return balanceInternetProxy(iplist, clazz, method, param);
		}
	}

	public static Object balanceInternetProxy(Class clazz, String method, Object[] param) {
		List<InternetProxyBean> proxyList = getProxyList();
		return balanceInternetProxy(proxyList, clazz, method, param);
	}
}
