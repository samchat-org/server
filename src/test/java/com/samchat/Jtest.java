package com.samchat;

import org.apache.commons.beanutils.PropertyUtils;

import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;


public class Jtest {

	// redis服务器主机

	static String host = "samchat.unebf5.0001.cnn1.cache.amazonaws.com.cn";

	// 端口号

	static int port = 6379;

	public void download(){}

	public static void main(String[] args) throws Exception {
		UserInfoRds p = new UserInfoRds();
		p.setAvatar_origin("1111111111111111111");
		TUserUsers t = new TUserUsers();
		t.setUser_id(122L);
		PropertyUtils.setProperty(p, "avatar_origin",);
		PropertyUtils.copyProperties(p , t);
 	}
}