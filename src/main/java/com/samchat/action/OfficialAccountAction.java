package com.samchat.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.Constant;
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
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.exceptions.AppException;
import com.samchat.service.interfaces.IOfficialAccountSrv;
import com.samchat.service.interfaces.IUsersSrv;

public class OfficialAccountAction extends BaseAction {

	private static Logger log = Logger.getLogger(OfficialAccountAction.class);

	@Autowired
	private IUsersSrv usersSrv;

	@Autowired
	private IOfficialAccountSrv officialAccountSrv;

	public Follow_res follow(Follow_req req, TokenRds token, HashMap<String, Object> paramRet) {

		TUserUsers userPro = (TUserUsers) paramRet.get("userPro");
		TOaFollow follow = (TOaFollow) paramRet.get("follow");

		long userIdPro = req.getBody().getId();
		long userId = token.getUserId();

		if (req.getBody().getOpt() == Constant.OA_FOLLOW) {
			if (follow == null) {
				officialAccountSrv.insertFollow(userId, userIdPro);
			}
		} else {
			officialAccountSrv.deleteFollow(userId, userIdPro);
		}
		Follow_res res = new Follow_res();
		Follow_res.User user = new Follow_res.User();
		res.setUser(user);
		user.setLastupdate(userPro.getState_date().getTime());
		return res;
	}

	public HashMap<String, Object> followValidate(Follow_req req, TokenRds token) {

		long userIdPro = req.getBody().getId();

		TUserUsers tuser = usersSrv.queryUser(userIdPro);
		if (tuser == null) {
			throw new AppException(Constant.ERROR.USER_NOT_EXIST);
		}
		if (tuser.getUser_type() != Constant.USER_TYPE_SERVICES) {
			throw new AppException(Constant.ERROR.USER_PROS_NOT_EXIST);
		}
		TOaFollow follow = officialAccountSrv.queryUserFollow(token.getUserId(), userIdPro);

		if (req.getBody().getOpt() == Constant.OA_UNFOLLOW) {
			if (follow == null) {
				throw new AppException(Constant.ERROR.OFFICIAL_ACCOUNT_UNFOLLOWED);
			}
		}

		HashMap<String, Object> paramRet = new HashMap<String, Object>();
		paramRet.put("userPro", tuser);
		paramRet.put("follow", follow);
		return paramRet;
	}

	public Block_res block(Block_req req, TokenRds token, HashMap<String, Object> paramRet) {

		TUserUsers userPro = (TUserUsers) paramRet.get("userPro");
		TOaFollow follow = (TOaFollow) paramRet.get("follow");

		long userIdPro = req.getBody().getId();
		long userId = token.getUserId();

		long opt = req.getBody().getOpt();
		if (follow.getBlock_tag() != opt) {
			officialAccountSrv.updateBlock(userId, userIdPro, (byte) opt);
		}
		Block_res res = new Block_res();
		Block_res.User user = new Block_res.User();
		res.setUser(user);
		user.setLastupdate(userPro.getState_date().getTime());
		return res;
	}

	public HashMap<String, Object> blockValidate(Block_req req, TokenRds token) {

		long userIdPro = req.getBody().getId();

		TUserUsers tuser = usersSrv.queryUser(userIdPro);
		if (tuser == null) {
			throw new AppException(Constant.ERROR.USER_NOT_EXIST);
		}
		if (tuser.getUser_type() != Constant.USER_TYPE_SERVICES) {
			throw new AppException(Constant.ERROR.USER_PROS_NOT_EXIST);
		}
		TOaFollow follow = officialAccountSrv.queryUserFollow(token.getUserId(), userIdPro);
		if (follow == null) {
			throw new AppException(Constant.ERROR.OFFICIAL_ACCOUNT_UNFOLLOWED);
		}

		HashMap<String, Object> paramRet = new HashMap<String, Object>();
		paramRet.put("userPro", tuser);
		paramRet.put("follow", follow);
		return paramRet;
	}

	public Favourite_res favourite(Favourite_req req, TokenRds token, HashMap<String, Object> paramRet) {

		TUserUsers userPro = (TUserUsers) paramRet.get("userPro");
		TOaFollow follow = (TOaFollow) paramRet.get("follow");

		long userIdPro = req.getBody().getId();
		long userId = token.getUserId();

		long opt = req.getBody().getOpt();
		if (follow.getFavourite_tag() != opt) {
			officialAccountSrv.updateFavourite(userId, userIdPro, (byte) opt);
		}
		Favourite_res res = new Favourite_res();
		Favourite_res.User user = new Favourite_res.User();
		res.setUser(user);
		user.setLastupdate(userPro.getState_date().getTime());
		return res;
	}

	public HashMap<String, Object> favouriteValidate(Favourite_req req, TokenRds token) {

		long userIdPro = req.getBody().getId();

		TUserUsers tuser = usersSrv.queryUser(userIdPro);
		if (tuser == null) {
			throw new AppException(Constant.ERROR.USER_NOT_EXIST);
		}
		if (tuser.getUser_type() != Constant.USER_TYPE_SERVICES) {
			throw new AppException(Constant.ERROR.USER_PROS_NOT_EXIST);
		}
		TOaFollow follow = officialAccountSrv.queryUserFollow(token.getUserId(), userIdPro);
		if (follow == null) {
			throw new AppException(Constant.ERROR.OFFICIAL_ACCOUNT_UNFOLLOWED);
		}

		HashMap<String, Object> paramRet = new HashMap<String, Object>();
		paramRet.put("userPro", tuser);
		paramRet.put("follow", follow);

		return paramRet;
	}

	public FollowListQuery_res followListQuery(FollowListQuery_req req, TokenRds token) {

		long userId = token.getUserId();

		FollowListQuery_res res = new FollowListQuery_res();
		List<QryFollowVO> list = officialAccountSrv.queryFollowList(userId);
		res.setCount(list.size());

		ArrayList<Users> users = new ArrayList<Users>();
		res.setUsers(users);

		for (QryFollowVO fo : list) {

			FollowListQuery_res.Avatar avatar = new FollowListQuery_res.Avatar();
			FollowListQuery_res.Sam_pros_info prosInfo = new FollowListQuery_res.Sam_pros_info();

			FollowListQuery_res.Users userPro = new FollowListQuery_res.Users();
			userPro.setId(fo.getUser_id());
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
		return res;
	}

	public void followListQueryValidate(FollowListQuery_req req, TokenRds token) {
	}
	
	public PublicQuery_res publicQuery(PublicQuery_req req, TokenRds token){
		PublicQuery_req.Body body = req.getBody();
		String key = body.getKey();
		String address = "";
		String placeId = "";
		String longitude = "";
		String latitude = "";
		
		PublicQuery_req.Location location = body.getLocation();
		
		if(location != null){
			address = location.getAddress();
			placeId = location.getPlace_id();
			
			PublicQuery_req.Location_info info = location.getLocation_info();
			if(info != null){
				
			}
		}

		List<QryPublicQueryVO>  oalist = officialAccountSrv.queryPublicList(key);
		PublicQuery_res res = new PublicQuery_res();
		res.setCount(oalist.size());
		
		ArrayList<PublicQuery_res.Users> users = new ArrayList<PublicQuery_res.Users>(); 
		res.setUsers(users);
		
 		for(QryPublicQueryVO pq : oalist){
			
			PublicQuery_res.Users user = new PublicQuery_res.Users();
			user.setId(pq.getUser_id());
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
	
	public void publicQueryValidate(PublicQuery_req req, TokenRds token){
		
	}
}
