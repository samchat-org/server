package com.samchat.dao.db.interfaces;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.samchat.common.beans.manual.db.QryContactVO;

public interface IContactDbDao extends IBaseDbDao {

	public void addContactUser( long userId, long proUserId, Timestamp sysdate);

	public void addContactProUser( long proUserId, long userId, Timestamp sysdate);

	public void deleteContactUser(long userId, long proUserId);

	public void deleteContactProUser(long proUserId, long userId);

	public  List<QryContactVO> queryContactUserList(long userId);

	public List<QryContactVO> queryContactProUserList(long proUserId);
	
	public Timestamp queryContactListStateDate(long userId);
	
	public Timestamp queryContactProsListStateDate(long userId);
}
