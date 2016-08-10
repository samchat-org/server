package com.skyworld.action;

import java.lang.reflect.Field;

import com.skyworld.common.Constant;

public class Test {
	public static void main(String[] args) throws Exception{
		Class clazz = Constant.CACHE_NAME.class;
		Field[] Fields = clazz.getDeclaredFields();
		System.out.print(Fields[0].get(clazz));
	}
}
