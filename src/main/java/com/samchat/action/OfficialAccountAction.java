package com.samchat.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.auto.db.entitybeans.TOaFollow;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.officialAccount.Block_req;
import com.samchat.common.beans.auto.json.appserver.officialAccount.Block_res;
import com.samchat.common.beans.auto.json.appserver.officialAccount.Favourite_req;
import com.samchat.common.beans.auto.json.appserver.officialAccount.Favourite_res;
import com.samchat.common.beans.auto.json.appserver.officialAccount.FollowListQuery_req;
import com.samchat.common.beans.auto.json.appserver.officialAccount.FollowListQuery_res;
import com.samchat.common.beans.auto.json.appserver.officialAccount.FollowListQuery_res.Users;
import com.samchat.common.beans.auto.json.appserver.officialAccount.Follow_req;
import com.samchat.common.beans.auto.json.appserver.officialAccount.Follow_res;
import com.samchat.common.beans.auto.json.appserver.officialAccount.PublicQuery_req;
import com.samchat.common.beans.auto.json.appserver.officialAccount.PublicQuery_res;
import com.samchat.common.beans.manual.db.QryFollowVO;
import com.samchat.common.beans.manual.db.QryPublicQueryVO;
import com.samchat.common.beans.manual.json.redis.TokenMappingRds;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.app.FollowAppEnum;
import com.samchat.common.enums.app.ResCodeAppEnum;
import com.samchat.common.exceptions.AppException;
import com.samchat.service.interfaces.ICommonSrvs;
import com.samchat.service.interfaces.IOfficialAccountSrvs;
import com.samchat.service.interfaces.IUsersSrvs;

public class OfficialAccountAction extends BaseAction {

	private static Logger log = Logger.getLogger(OfficialAccountAction.class);

	@Autowired
	private IUsersSrvs usersSrv;

	@Autowired
	private IOfficialAccountSrvs officialAccountSrv;
	
	@Autowired
	private ICommonSrvs commonSrv;

	public Follow_res follow(Follow_req req, TokenMappingRds token, HashMap<String, Object> paramRet) {
		
 		TOaFollow follow = (TOaFollow) paramRet.get("follow");

		long userIdPro = req.getBody().getId();
		long userId = token.getUserId();
		Timestamp sysdate = commonSrv.querySysdate();
		String sysdateStr = String.valueOf(sysdate.getTime());
		String previous = commonSrv.hgetUserInfoFollowListDate(userId);
		
		if (req.getBody().getOpt() ==  FollowAppEnum.Follow.FOLLOW.val()) {
			if (follow == null) {
				officialAccountSrv.insertFollow(userId, userIdPro, sysdate);
 				commonSrv.hsetUserInfoFollowListDate(userId, sysdateStr);
			}
		} else {
			officialAccountSrv.deleteFollow(userId, userIdPro);
			commonSrv.hsetUserInfoFollowListDate(userId, sysdateStr);
 		}
		Follow_res res = new Follow_res();
		Follow_res.State_date stateDate = new Follow_res.State_date();
		res.setState_date(stateDate);
		stateDate.setPrevious(new Long(previous));
		stateDate.setLast(new Long(sysdateStr));

		return res;
	}

	public HashMap<String, Object> followValidate(Follow_req req, TokenMappingRds token) {

		long userIdPro = req.getBody().getId();

		TUserUsers tuser = usersSrv.queryUser(userIdPro);
		if (tuser == null) {
			throw new AppException(ResCodeAppEnum.USER_NOT_EXIST.getCode());
		}
		if (tuser.getUser_type() != Constant.USER_TYPE_SERVICES) {
			throw new AppException(ResCodeAppEnum.USER_PROS_NOT_EXIST.getCode());
		}
		TOaFollow follow = officialAccountSrv.queryUserFollow(token.getUserId(), userIdPro);

		if (req.getBody().getOpt() == FollowAppEnum.Follow.UNFOLLOW.val()) {
			if (follow == null) {
				throw new AppException(ResCodeAppEnum.OFFICIAL_ACCOUNT_UNFOLLOWED.getCode());
			}
		}

		HashMap<String, Object> paramRet = new HashMap<String, Object>();
		paramRet.put("userPro", tuser);
		paramRet.put("follow", follow);
		return paramRet;
	}

	public Block_res block(Block_req req, TokenMappingRds token, HashMap<String, Object> paramRet) {

		long userIdPro = req.getBody().getId();
		long userId = token.getUserId();
		
		TOaFollow follow = (TOaFollow) paramRet.get("follow");
		Timestamp sysdate = commonSrv.querySysdate();
		String sysdateStr = String.valueOf(sysdate.getTime());
		String previous = commonSrv.hgetUserInfoFollowListDate(userId);

		long opt = req.getBody().getOpt();
		if (follow.getBlock_tag() != opt) {
			officialAccountSrv.updateBlock(userId, userIdPro, (byte) opt, sysdate);
			commonSrv.hsetUserInfoFollowListDate(userId, sysdateStr);
 		}
		Block_res res = new Block_res();
		Block_res.State_date stateDate = new Block_res.State_date();
		res.setState_date(stateDate);
		stateDate.setPrevious(new Long(previous));
		stateDate.setLast(new Long(sysdateStr));
		
		return res;
	}

	public HashMap<String, Object> blockValidate(Block_req req, TokenMappingRds token) {

		long userIdPro = req.getBody().getId();

		TUserUsers tuser = usersSrv.queryUser(userIdPro);
		if (tuser == null) {
			throw new AppException(ResCodeAppEnum.USER_NOT_EXIST.getCode());
		}
		if (tuser.getUser_type() != Constant.USER_TYPE_SERVICES) {
			throw new AppException(ResCodeAppEnum.USER_PROS_NOT_EXIST.getCode());
		}
		TOaFollow follow = officialAccountSrv.queryUserFollow(token.getUserId(), userIdPro);
		if (follow == null) {
			throw new AppException(ResCodeAppEnum.OFFICIAL_ACCOUNT_UNFOLLOWED.getCode());
		}

		HashMap<String, Object> paramRet = new HashMap<String, Object>();
		paramRet.put("userPro", tuser);
		paramRet.put("follow", follow);
		return paramRet;
	}

	public Favourite_res favourite(Favourite_req req, TokenMappingRds token, HashMap<String, Object> paramRet) {

		long userIdPro = req.getBody().getId();
		long userId = token.getUserId();
		
 		TOaFollow follow = (TOaFollow) paramRet.get("follow");
		Timestamp sysdate = commonSrv.querySysdate();
		String sysdateStr = String.valueOf(sysdate.getTime());
		String previous = commonSrv.hgetUserInfoFollowListDate(userId);

		long opt = req.getBody().getOpt();
		if (follow.getFavourite_tag() != opt) {
			officialAccountSrv.updateFavourite(userId, userIdPro, (byte) opt, sysdate);
			commonSrv.hsetUserInfoFollowListDate(userId, sysdateStr);
 		}
		Favourite_res res = new Favourite_res();
		Favourite_res.State_date stateDate = new Favourite_res.State_date();
		res.setState_date(stateDate);
		stateDate.setPrevious(new Long(previous));
		stateDate.setLast(new Long(sysdateStr));
		return res;
	}

	public HashMap<String, Object> favouriteValidate(Favourite_req req, TokenMappingRds token) {

		long userIdPro = req.getBody().getId();

		TUserUsers tuser = usersSrv.queryUser(userIdPro);
		if (tuser == null) {
			throw new AppException(ResCodeAppEnum.USER_NOT_EXIST.getCode());
		}
		if (tuser.getUser_type() != Constant.USER_TYPE_SERVICES) {
			throw new AppException(ResCodeAppEnum.USER_PROS_NOT_EXIST.getCode());
		}
		TOaFollow follow = officialAccountSrv.queryUserFollow(token.getUserId(), userIdPro);
		if (follow == null) {
			throw new AppException(ResCodeAppEnum.OFFICIAL_ACCOUNT_UNFOLLOWED.getCode());
		}

		HashMap<String, Object> paramRet = new HashMap<String, Object>();
		paramRet.put("userPro", tuser);
		paramRet.put("follow", follow);

		return paramRet;
	}

	public FollowListQuery_res followListQuery(FollowListQuery_req req, TokenMappingRds token) {

		long userId = token.getUserId();

		FollowListQuery_res res = new FollowListQuery_res();
		List<QryFollowVO> list = officialAccountSrv.queryFollowList(userId);
		res.setCount(new Long(list.size()));

		ArrayList<Users> users = new ArrayList<Users>();
		res.setUsers(users);

		for (QryFollowVO fo : list) {

			FollowListQuery_res.Avatar avatar = new FollowListQuery_res.Avatar();
			FollowListQuery_res.Sam_pros_info prosInfo = new FollowListQuery_res.Sam_pros_info();

			FollowListQuery_res.Users userPro = new FollowListQuery_res.Users();
			userPro.setId(fo.getUser_id_pro());
			userPro.setUsername(fo.getUser_name());
			userPro.setBlock_tag(fo.getBlock_tag());
			userPro.setFavourite_tag(fo.getFavourite_tag());
			userPro.setLastupdate(fo.getLastupdate().getTime());
			userPro.setAvatar(avatar);
			userPro.setSam_pros_info(prosInfo);

			avatar.setThumb(fo.getThumb());
			prosInfo.setService_category(fo.getService_category());

			users.add(userPro);
		}
		String last = commonSrv.hgetUserInfoFollowListDate(userId);
		FollowListQuery_res.State_date stateDate = new FollowListQuery_res.State_date();
		stateDate.setLast(new Long(last));
		res.setState_date(stateDate);
		
		return res;
	}

	public void followListQueryValidate(FollowListQuery_req req, TokenMappingRds token) {
	}

	public PublicQuery_res publicQuery(PublicQuery_req req, TokenMappingRds token) {
		PublicQuery_req.Body body = req.getBody();
		long count = body.getCount();
		String key = body.getKey();
		String address = "";
		String placeId = "";
		String longitude = "";
		String latitude = "";

		PublicQuery_req.Location location = body.getLocation();

		if (location != null) {
			address = location.getAddress();
			placeId = location.getPlace_id();

			PublicQuery_req.Location_info info = location.getLocation_info();
			if (info != null) {

			}
		}
		List<QryPublicQueryVO> oalist = officialAccountSrv.queryPublicList(token.getUserId(), key, count);
		PublicQuery_res res = new PublicQuery_res();
		res.setCount(new Long(oalist.size()));

		ArrayList<PublicQuery_res.Users> users = new ArrayList<PublicQuery_res.Users>();
		res.setUsers(users);

		for (QryPublicQueryVO pq : oalist) {
			long userId = pq.getUser_id();
			if(userId == token.getUserId()){
				continue;
			}
			PublicQuery_res.Users user = new PublicQuery_res.Users();
			user.setId(userId);
			user.setSamchat_id(pq.getUser_code());
			user.setUsername(pq.getUser_name());
			user.setCountrycode(pq.getCountrycode());
			user.setCellphone(pq.getCellphone());
			user.setEmail(pq.getEmail());
			user.setAddress(pq.getAddress());
			user.setType(pq.getType());

			PublicQuery_res.Avatar avatar = new PublicQuery_res.Avatar();
			user.setAvatar(avatar);
			avatar.setOrigin(pq.getOrigin());
			avatar.setThumb(pq.getThumb());

			user.setLastupdate(pq.getLastupdate().getTime());

			PublicQuery_res.Sam_pros_info pros = new PublicQuery_res.Sam_pros_info();
			user.setSam_pros_info(pros);
			pros.setCompany_name(pq.getCompany_name());
			pros.setService_category(pq.getService_category());
			pros.setService_description(pq.getService_description());
			pros.setCountrycode(pq.getCountrycode_pro());
			pros.setPhone(pq.getPhone_pro());
			pros.setEmail(pq.getEmail_pro());
			pros.setAddress(pq.getAddress_pro());
			users.add(user);
		}

		return res;
	}

	public void publicQueryValidate(PublicQuery_req req, TokenMappingRds token) {

	}
}
