package com.samchat.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samchat.common.beans.auto.db.entitybeans.TOaFollow;
import com.samchat.common.beans.manual.db.QryFollowVO;
import com.samchat.common.beans.manual.db.QryPublicQueryVO;
import com.samchat.dao.db.interfaces.IOfficialAccountDbDao;
import com.samchat.service.interfaces.IOfficialAccountSrvs;

@Service
public class OfficialAccountSrvs extends BaseSrvs implements IOfficialAccountSrvs {

	@Autowired
	private IOfficialAccountDbDao officialAccountDbDao;

	public TOaFollow queryUserFollow(long userId, long userIdPros) {
		return officialAccountDbDao.queryUserFollow(userId, userIdPros);
	}

	public void insertFollow(long userId, long userIdPros, Timestamp sysdate) {
		officialAccountDbDao.insertFollow(userId, userIdPros, sysdate);
	}

	public void deleteFollow(long userId, long userIdPros) {
		officialAccountDbDao.deleteFollow(userId, userIdPros);
	}

	public void updateUnblock(long userId, long userIdPros) {
		officialAccountDbDao.deleteFollow(userId, userIdPros);
	}
	
	public void updateBlock(long userId, long userIdPros, byte block, Timestamp sysdate){
		officialAccountDbDao.updateBlock(userId, userIdPros, block, sysdate);
	}
	
	public void updateFavourite(long userId, long userIdPros, byte favourite, Timestamp sysdate){
		officialAccountDbDao.updateFavourite(userId, userIdPros, favourite, sysdate);
	}
	
	public List<QryFollowVO> queryFollowList(long userId){
		return officialAccountDbDao.queryFollowList(userId);
	}
	
	public List<QryPublicQueryVO> queryPublicList(String key){
		return officialAccountDbDao.queryPublicList(key);
	}
	
	public List<TOaFollow> queryFollowListByAdserId(long userId){
		return officialAccountDbDao.queryFollowListByAdserId(userId);
	}
}
