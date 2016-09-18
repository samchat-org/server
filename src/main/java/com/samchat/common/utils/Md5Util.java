package com.samchat.common.utils;

import java.io.FileInputStream;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.samchat.common.enums.Constant;

public class Md5Util {

	private static Log log = LogFactory.getLog(Md5Util.class);

	private static final String TYPE_MD5 = "MD5";

	private static final String CHARSET = "GBK";

	/**
	 * 对字符串进行MD5签名
	 * 
	 * @param original
	 * @param key
	 * @return
	 * @throws Exception
	 * @author cl
	 */
	public static String getSign4String(String original, String key) throws Exception {
		MessageDigest md = MessageDigest.getInstance(TYPE_MD5);
		String inmsg = original + key;
		byte[] digest = md.digest(inmsg.getBytes(CHARSET));
		String sign = new String(Hex.encodeHex(digest));
		log.info("md5 str:" + inmsg + " key str:" + key + " sign:" + sign);
		return sign;
	}

	public static String getSign4String(String original) throws Exception {
		return getSign4String(original, "");
	}

	/**
	 * 对文件进行MD5签名
	 * 
	 * @param filepath
	 * @param key
	 * @return
	 * @throws Exception
	 * @author cl
	 */
	public static String getSign4File(String filepath, String key) throws Exception {
		MessageDigest md = MessageDigest.getInstance(TYPE_MD5);
		byte[] buffer = new byte[1024];
		int length = -1;
		FileInputStream fis = new FileInputStream(filepath);
		while ((length = fis.read(buffer)) != -1) {
			md.update(buffer, 0, length);
		}
		md.update(merge("", key).getBytes(CHARSET));
		byte[] digest = md.digest();
		return new String(Hex.encodeHex(digest));
	}

	/**
	 * 动态设置密钥
	 * 
	 * @param original
	 * @param key
	 * @return
	 * @author cl
	 */
	private static String merge(String original, String key) {
		if (original == null) {
			original = "";
		}
		if ((key == null) || "".equals(key)) {
			return original;
		} else {
			return original + "{" + key + "}";
		}
	}

	public static void main(String args[]) throws Exception {
	}

}
