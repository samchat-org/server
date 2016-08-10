package com.skyworld.utils;

import java.util.Locale;

public class StrUtils {
	
	/**
	 * 将string按需要格式化,前面加缩进符,后面加换行符
	 * @param tabNum 缩进量
	 * @param srcString
	 * @return
	 */
	public static String formatSingleLine(int tabNum, String srcString) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<tabNum; i++) {
			sb.append("\t");
		}
		sb.append(srcString);
		sb.append("\n");
		return sb.toString();
	}
	
	public static String gapToCamel(String src) {
		StringBuilder sb = new StringBuilder();
		for(String s : src.trim().split(" ")) {
			sb.append(firstToUpperCase(s));
		}
		return sb.toString();
	}
	
	public static String firstToUpperCase(String key) {
		return key.substring(0, 1).toUpperCase() + key.substring(1);
	}
	
	public static String firstToUpperCase(String key, String chr){
		String[] keyp = key.split(chr);
		StringBuilder sb = new StringBuilder(keyp[0]);
		for(int i = 1; i < keyp.length; i++){
			sb.append(keyp[i]);
		}
		return sb.toString();
	}
}
