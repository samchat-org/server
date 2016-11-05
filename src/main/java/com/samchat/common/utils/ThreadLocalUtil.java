package com.samchat.common.utils;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;

public class ThreadLocalUtil {

	private static ThreadLocal<ObjectMapper> TL_OM_APP = new ThreadLocal<ObjectMapper>();

	private static ThreadLocal<ObjectMapper> TL_OM_DEF = new ThreadLocal<ObjectMapper>();

	public static ObjectMapper getAppObjectMapper() {
		ObjectMapper om = TL_OM_APP.get();
		if (om == null) {
			om = new ObjectMapper();
			om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			//mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			TL_OM_APP.set(om);
		}
		return om;
	}

	public static ObjectMapper getDefObjectMappera() {
		ObjectMapper om = TL_OM_DEF.get();
		if (om == null) {
			om = new ObjectMapper();
			om.enableDefaultTyping(DefaultTyping.NON_FINAL, As.PROPERTY);
			TL_OM_DEF.set(om);
		}
		return om;
	}
}
