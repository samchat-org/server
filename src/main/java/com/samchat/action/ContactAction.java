package com.samchat.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.contact.ContactListQuery_req;
import com.samchat.common.beans.auto.json.appserver.contact.ContactListQuery_res;
import com.samchat.common.beans.auto.json.appserver.contact.Contact_req;
import com.samchat.common.beans.auto.json.appserver.contact.Contact_res;
import com.samchat.common.beans.manual.db.QryContactVO;
import com.samchat.common.beans.manual.json.redis.TokenMappingRds;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.app.ResCodeAppEnum;
import com.samchat.common.exceptions.AppException;
import com.samchat.service.interfaces.ICommonSrvm;
import com.samchat.service.interfaces.ICommonSrvs;
import com.samchat.service.interfaces.IContactSrvs;
import com.samchat.service.interfaces.IUsersSrvs;

public class ContactAction extends BaseAction {

	private static Logger log = Logger.getLogger(ContactAction.class);

	@Autowired
	private IUsersSrvs usersSrv;

	@Autowired
	private IContactSrvs contactSrv;

	@Autowired
	private ICommonSrvm commonSrvm;

	@Autowired
	private ICommonSrvs commonSrvs;

	public Contact_res contact(Contact_req req, TokenMappingRds token) throws Exception {
		long senderId = token.getUserId();
		TUserUsers senderInfo = usersSrv.queryUser(senderId);

		Contact_req.Body body = req.getBody();
		long userId = body.getId();
		long opt = body.getOpt();
		long type = body.getType();
		Timestamp sysdate = commonSrvs.querySysdate();
		String sysdateStr = sysdate.getTime() + "";
		TUserUsers user = usersSrv.queryUser(userId);
 		if (user == null) {
			throw new AppException(ResCodeAppEnum.USER_NOT_EXIST.getCode(), "user not exists:" + userId);
		}
		if (opt == 0) {
			if (type == 0) {
				if (user.getUser_type() == Constant.USER_TYPE_CUSTOMER) {
					throw new AppException(ResCodeAppEnum.CUSTORMER_ADD_CUSTORMER.getCode());
				}
				log.info("user_id:" + senderId + "--user_id_pro:" + userId);
				contactSrv.addContactUser_master(senderId, userId, sysdate);
 				contactSrv.hsetUserInfoServicerListDate(userId, sysdateStr);
			} else if (type == 1) {
				if (senderInfo.getUser_type() == Constant.USER_TYPE_CUSTOMER) {
					throw new AppException(ResCodeAppEnum.CUSTORMER_ADD_SERVIER_CONTACT_LIST.getCode());
				}
				contactSrv.addContactProUser_master(senderId, userId, sysdate);
 				contactSrv.hsetUserInfoCustomerListDate(userId, sysdateStr);
			}
			
		} else if (opt == 1) {
			if (type == 0) {
				contactSrv.deleteContactUser(senderId, userId);
				contactSrv.hsetUserInfoServicerListDate(userId, sysdateStr);
 			} else if (type == 1) {
				contactSrv.deleteContactProUser(senderId, userId);
				contactSrv.hsetUserInfoCustomerListDate(userId, sysdateStr);
			}
			
		}
 		return new Contact_res();
	}

	public void contactValidate(Contact_req req, TokenMappingRds token) {
	}

	public ContactListQuery_res contactListQuery(ContactListQuery_req req, TokenMappingRds token) {

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

	public void contactListQueryValidate(ContactListQuery_req req, TokenMappingRds token) {
	}

}
