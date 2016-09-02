package com.samchat.service.interfaces;

import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TOaFollow;
import com.samchat.common.beans.manual.db.QryFollowVO;
import com.samchat.common.beans.manual.db.QryPublicQueryVO;

public interface IOfficialAccountSrv {

	public TOaFollow queryUserFollow(long userId, long userIdPros);

	public void insertFollow(long userId, long userIdPros);

	public void deleteFollow(long userId, long userIdPros);
	
	public void updateBlock(long userId, long userIdPros, byte block);
	
	public void updateFavourite(long userId, long userIdPros, byte favourite);
	
	public List<QryFollowVO> queryFollowList(long userId);
	
	public List<QryPublicQueryVO> queryPublicList(String key);
	
	public List<TOaFollow> queryFollowListByAdserId(long userId);
}
