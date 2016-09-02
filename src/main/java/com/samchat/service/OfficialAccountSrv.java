package com.samchat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samchat.common.beans.auto.db.entitybeans.TOaFollow;
import com.samchat.common.beans.manual.db.QryFollowVO;
import com.samchat.common.beans.manual.db.QryPublicQueryVO;
import com.samchat.dao.db.interfaces.IOfficialAccountDbDao;
import com.samchat.service.interfaces.IOfficialAccountSrv;

@Service
public class OfficialAccountSrv implements IOfficialAccountSrv {

	@Autowired
	private IOfficialAccountDbDao officialAccountDbDao;

	public TOaFollow queryUserFollow(long userId, long userIdPros) {
		return officialAccountDbDao.queryUserFollow(userId, userIdPros);
	}

	public void insertFollow(long userId, long userIdPros) {
		officialAccountDbDao.insertFollow(userId, userIdPros);
	}

	public void deleteFollow(long userId, long userIdPros) {
		officialAccountDbDao.deleteFollow(userId, userIdPros);
	}
	
	public void updateBlock(long userId, long userIdPros) {
		officialAccountDbDao.insertFollow(userId, userIdPros);
	}

	public void updateUnblock(long userId, long userIdPros) {
		officialAccountDbDao.deleteFollow(userId, userIdPros);
	}
	
	public void updateBlock(long userId, long userIdPros, byte block){
		officialAccountDbDao.updateBlock(userId, userIdPros, block);
	}
	
	public void updateFavourite(long userId, long userIdPros, byte favourite){
		officialAccountDbDao.updateFavourite(userId, userIdPros, favourite);
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
