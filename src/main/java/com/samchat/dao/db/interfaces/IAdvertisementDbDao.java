package com.samchat.dao.db.interfaces;

import java.sql.Timestamp;

public interface IAdvertisementDbDao extends IBaseDbDao {
	public void saveAdvertisementContent(long adsId, long userIdPro, byte type, String content, String thumb,
			Timestamp recvdate);

	public void saveAdvertisementSendLog(long adsId, long userId, Timestamp senddate, byte state, String clientId,
			String remark);

	public void delAdvertisementContent(long adsId, long userId, Timestamp timestamp);
}
