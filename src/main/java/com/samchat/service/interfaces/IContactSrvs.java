package com.samchat.service.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.samchat.common.beans.manual.db.QryContactVO;

public interface IContactSrvs extends IBaseSrvs{
	public void addContactUser_master(long userId, long proUserId, Timestamp sysdate);

	public void addContactProUser_master(long proUserId, long userId, Timestamp sysdate);

	public void deleteContactUser(long userId, long proUserId);

	public void deleteContactProUser(long proUserId, long userId);

	public List<QryContactVO> queryContactUserList(long userId);

	public List<QryContactVO> queryContactProUserList(long proUserId);
}
