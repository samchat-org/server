package com.samchat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samchat.common.beans.manual.db.QryContactVO;
import com.samchat.dao.db.interfaces.IContactDbDao;
import com.samchat.service.interfaces.IContactSrv;

@Service
public class ContactSrv implements IContactSrv {

	@Autowired
	private IContactDbDao contactDbDao;

	public void addContactUser(long userId, long proUserId) {
		contactDbDao.addContactUser(userId, proUserId);
	}

	public void addContactProUser(long proUserId, long userId) {
		contactDbDao.addContactProUser(proUserId, userId);
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
