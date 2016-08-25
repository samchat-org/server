package com.samchat.dao.db.interfaces;

import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TOaFollow;
import com.samchat.common.beans.manual.db.QryFollowVO;

public interface IOfficialAccountDbDao extends IBaseDbDao {

	public TOaFollow queryUserFollow(long userId, long userIdPros);

	public void insertFollow(long userId, long userIdPros);

	public void deleteFollow(long userId, long userIdPros);

	public void updateBlock(long userId, long userIdPros, byte block);

	public void updateFavourite(long userId, long userIdPros, byte favourite);

	public List<QryFollowVO> queryFollowList(long userId);
}