package com.samchat.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samchat.common.beans.manual.db.QryContactVO;
import com.samchat.dao.db.interfaces.IContactDbDao;
import com.samchat.service.interfaces.IContactSrvs;

@Service
public class ContactSrvs implements IContactSrvs {

	@Autowired
	private IContactDbDao contactDbDao;

	public void addContactUser(long userId, long proUserId, Timestamp sysdate) {
		contactDbDao.addContactUser(userId, proUserId, sysdate);
	}

	public void addContactProUser(long proUserId, long userId, Timestamp sysdate) {
		contactDbDao.addContactProUser(proUserId, userId, sysdate);
	}

	public void deleteContactUser(long userId, long proUserId) {
		contactDbDao.deleteContactUser(userId, proUserId);
	}

	public void deleteContactProUser(long proUserId, long userId) {
		contactDbDao.deleteContactProUser(proUserId, userId);
	}

	public List<QryContactVO> queryContactUserList(long userId) {
		return contactDbDao.queryContactUserList(userId);
	}

	public List<QryContactVO> queryContactProUserList(long proUserId) {
		return contactDbDao.queryContactProUserList(proUserId);
	}
}
