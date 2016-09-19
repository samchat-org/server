package com.samchat.dao.db.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementContent;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementSendLog;

public interface IAdvertisementDbDao extends IBaseDbDao {
	public void saveAdvertisementContent(long adsId, long userIdPro, byte type, String content, String thumb,
			Timestamp recvdate, int shardingFlag);

	public void saveAdvertisementSendLog(long adsId, long userId, Timestamp senddate, byte state, String clientId,
			String remark, int shardingFlag);

	public void delAdvertisementContent(long adsId, long userId, Timestamp timestamp);

	public List<TAdvertisementSendLog> queryAdvertisementSendLog(long userId, int shardingFlag) throws Exception;

	public TAdvertisementContent queryAdvertisementCotentById(long adsId, int shardingFlag) throws Exception;
}
