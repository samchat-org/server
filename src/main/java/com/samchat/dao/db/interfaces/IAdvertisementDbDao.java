package com.samchat.dao.db.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementContent;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementSendLog;

public interface IAdvertisementDbDao extends IBaseDbDao {
	public TAdvertisementContent saveAdvertisementContent(long userIdPro, byte type, String content, String thumb,
			Timestamp recvdate, int shardingFlag);

	public void saveAdvertisementSendLog(long adsId, long userId, Timestamp senddate, byte state, String clientId,
			String remark, int shardingFlag, int pagination);
	
	public void updateAdvertisementSendLog(long logId, Timestamp senddate, byte state, String clientId,
			String remark, int shardingFlag, int sendcount);

	public void delAdvertisementContent(long adsId, long userId, Timestamp timestamp);

	public TAdvertisementContent queryAdvertisementCotentById(long adsId, int shardingFlag) throws Exception;
	
	public boolean updateAdvertisementState(long adsId, byte destState, List<Byte> expectState, int shardingFlag);
	
	public void updateAdvertisementState(long adsId, byte state, int shardingFlag);

}
