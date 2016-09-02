package com.samchat.dao.db.interfaces;

import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementAdvertisements;

public interface IAdvertisementDbDao extends IBaseDbDao {

	public TAdvertisementAdvertisements saveAdvertisement(long userId, byte type, String content);

	public void updateAdvertisementNotInuse(long adsId, long timestamp, long userId);
}
