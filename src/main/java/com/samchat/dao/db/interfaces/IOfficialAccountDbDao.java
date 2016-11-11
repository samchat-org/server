package com.samchat.dao.db.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TOaFollow;
import com.samchat.common.beans.manual.db.QryFollowVO;
import com.samchat.common.beans.manual.db.QryPublicQueryVO;

public interface IOfficialAccountDbDao extends IBaseDbDao {

	public TOaFollow queryUserFollow(long userId, long userIdPros);

	public void insertFollow(long userId, long userIdPros, Timestamp sysdate);

	public void deleteFollow(long userId, long userIdPros);

	public void updateBlock(long userId, long userIdPros, byte block, Timestamp sysdate);

	public void updateFavourite(long userId, long userIdPros, byte favourite, Timestamp sysdate);

	public List<QryFollowVO> queryFollowList(long userId);

	public List<QryPublicQueryVO> queryPublicList(long curUserId, String key, long count);

	public List<QryFollowVO> queryFollowListByAdserId(long userIdPro, int page);
	
	public Timestamp queryFollowListStateDate(long userId);
}
