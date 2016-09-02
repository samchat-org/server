package com.samchat.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.Constant;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.contact.ContactListQuery_req;
import com.samchat.common.beans.auto.json.appserver.contact.ContactListQuery_res;
import com.samchat.common.beans.auto.json.appserver.contact.Contact_req;
import com.samchat.common.beans.auto.json.appserver.contact.Contact_res;
import com.samchat.common.beans.manual.db.QryContactVO;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.exceptions.AppException;
import com.samchat.dao.db.ContactDbDao;
import com.samchat.service.interfaces.IContactSrv;
import com.samchat.service.interfaces.IUsersSrv;

public class ContactAction extends BaseAction {
	
	private static Logger log = Logger.getLogger(ContactAction.class);

	@Autowired
	private IUsersSrv usersSrv;

	@Autowired
	private IContactSrv contactSrv;

	public Contact_res contact(Contact_req req, TokenRds token) {

		Contact_req.Body body = req.getBody();
		long userId = body.getId();
		long opt = body.getOpt();
		long type = body.getType();

		TUserUsers user = usersSrv.queryUser(userId);
		if (user == null) {
			throw new AppException(Constant.ERROR.USER_NOT_EXIST, "user not exists:" + userId);
		}
		if (opt == 0) {
			if (type == 0) {
				if (user.getUser_type() == Constant.USER_TYPE_CUSTOMER) {
					throw new AppException(Constant.ERROR.CUSTORMER_ADD_CUSTORMER);
				}
				log.info("user_id:" + token.getUserId() + "--user_id_pro:" +  userId);
				contactSrv.addContactUser(token.getUserId(), userId);
			} else if (type == 1) {
				if (token.getUserType() == Constant.USER_TYPE_CUSTOMER) {
					throw new AppException(Constant.ERROR.CUSTORMER_ADD_SERVIER_CONTACT_LIST);
				}
				contactSrv.addContactProUser(token.getUserId(), userId);
			}
		} else if (opt == 1) {
			if (type == 0) {
				contactSrv.deleteContactUser(token.getUserId(), userId);
			} else if (type == 1) {
				contactSrv.deleteContactProUser(token.getUserId(), userId);
			}
		}

		return new Contact_res();
	}

	public void contactValidate(Contact_req req, TokenRds token) {
	}

	public ContactListQuery_res contactListQuery(ContactListQuery_req req, TokenRds token) {
		
		log.info("start list");
		long userId = token.getUserId();
		long type = req.getBody().getType();
		List<QryContactVO> userlist = null;
		if (type == Constant.USER_TYPE_CUSTOMER) {
			userlist = contactSrv.queryContactUserList(userId);
		} else {
			userlist = contactSrv.queryContactProUserList(userId);
		}
		ContactListQuery_res res = new ContactListQuery_res();
		res.setCount(userlist.size());

		ArrayList<ContactListQuery_res.Users> list = new ArrayList<ContactListQuery_res.Users>();
		for (QryContactVO vo : userlist) {
			ContactListQuery_res.Users user = new ContactListQuery_res.Users();
			user.setId(vo.getUser_id());
			user.setUsername(vo.getUser_name());
			user.setLastupdate(vo.getLastupdate().getTime());
			user.setType(vo.getType());

			ContactListQuery_res.Avatar avatar = new ContactListQuery_res.Avatar();
			avatar.setThumb(vo.getThumb());
			user.setAvatar(avatar);

			ContactListQuery_res.Sam_pros_info pros = new ContactListQuery_res.Sam_pros_info();
			pros.setService_category(vo.getService_category());
			user.setSam_pros_info(pros);

			list.add(user);

		}
		res.setUsers(list);

		return res;

	}
	
	public void contactListQueryValidate(ContactListQuery_req req, TokenRds token) {}


}
