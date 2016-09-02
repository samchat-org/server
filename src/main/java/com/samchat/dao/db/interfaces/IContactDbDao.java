package com.samchat.dao.db.interfaces;

import java.util.List;

import com.samchat.common.beans.manual.db.QryContactVO;

public interface IContactDbDao extends IBaseDbDao {

	public void addContactUser(long userId, long proUserId);

	public void addContactProUser(long proUserId, long userId);

	public void deleteContactUser(long userId, long proUserId);

	public void deleteContactProUser(long proUserId, long userId);

	public  List<QryContactVO> queryContactUserList(long userId);

	public List<QryContactVO> queryContactProUserList(long proUserId);
}