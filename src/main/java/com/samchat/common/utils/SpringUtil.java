package com.samchat.common.utils;

import java.io.File;
import java.util.HashSet;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {

	public static ApplicationContext initContext(String path) throws Exception {
		Object[] sfs = new HashSet<File>(FileUtils.listFiles(new File(Thread.currentThread().getContextClassLoader()
				.getResource(path).getFile()), new String[] { "xml" }, true)).toArray();
		String arrsfs[] = new String[sfs.length];
		for (int i = 0; i < sfs.length; i++) {
			arrsfs[i] = "file:" + ((File) sfs[i]).getPath();
		}
		return new ClassPathXmlApplicationContext(arrsfs);
	}
}
