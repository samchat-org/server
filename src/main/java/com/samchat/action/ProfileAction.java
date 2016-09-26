package com.samchat.action;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.profile.AppkeyGet_req;
import com.samchat.common.beans.auto.json.appserver.profile.AppkeyGet_res;
import com.samchat.common.beans.auto.json.appserver.profile.AvatarUpdate_req;
import com.samchat.common.beans.auto.json.appserver.profile.AvatarUpdate_res;
import com.samchat.common.beans.auto.json.appserver.profile.GetPlacesInfoRequest_req;
import com.samchat.common.beans.auto.json.appserver.profile.GetPlacesInfoRequest_res;
import com.samchat.common.beans.auto.json.appserver.profile.GoogleplaceAutocomplete_res;
import com.samchat.common.beans.auto.json.appserver.profile.GoogleplaceAutocomplete_res.Predictions;
import com.samchat.common.beans.auto.json.appserver.profile.ProfileUpdate_req;
import com.samchat.common.beans.auto.json.appserver.profile.ProfileUpdate_res;
import com.samchat.common.beans.auto.json.appserver.profile.QueryStateDate_req;
import com.samchat.common.beans.auto.json.appserver.profile.QueryStateDate_res;
import com.samchat.common.beans.auto.json.appserver.profile.SendClientId_req;
import com.samchat.common.beans.auto.json.appserver.profile.SendClientId_res;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;
import com.samchat.common.beans.manual.json.sqs.AdvertisementSqs;
import com.samchat.common.enums.app.ResCodeAppEnum;
import com.samchat.common.enums.cache.UserInfoFieldRdsEnum;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
import com.samchat.common.exceptions.AppException;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.GooglePlaceUtil;
import com.samchat.common.utils.SqsUtil;
import com.samchat.service.interfaces.ICommonSrvs;
import com.samchat.service.interfaces.IProfileSrvs;
import com.samchat.service.interfaces.IUsersSrvs;

public class ProfileAction extends BaseAction {

	private static Logger log = Logger.getLogger(ProfileAction.class);

	@Autowired
	private IUsersSrvs usersSrv;
	@Autowired
	private IProfileSrvs profileSrv;

	@Autowired
	private ICommonSrvs commonSrv;

	public AppkeyGet_res appkeyGet(AppkeyGet_req req, TokenRds token) {
		AppkeyGet_res res = new AppkeyGet_res();

		String appI = CommonUtil.getSysConfigStr("getui_appI");
		String appK = CommonUtil.getSysConfigStr("getui_appK");
		String appS = CommonUtil.getSysConfigStr("getui_appS");
		log.info("appI:" + appI + "---appK" + appI + "---appS:" + appS);

		res.setAppi(appI);
		res.setAppk(appK);
		res.setApps(appS);
		return res;
	}

	public void appkeyGetValidate(AppkeyGet_req req, TokenRds token) {
	}

	public ProfileUpdate_res profileUpdate(ProfileUpdate_req req, TokenRds token, TUserUsers updatedUser) {

		Timestamp sysdate = commonSrv.querySysdate();
		long lastupdate = profileSrv.updateProfile(req, token.getUserId(), sysdate);

		ProfileUpdate_res res = new ProfileUpdate_res();
		ProfileUpdate_res.User userRes = new ProfileUpdate_res.User();
		res.setUser(userRes);
		userRes.setLastupdate(lastupdate);

		return res;
	}

	public TUserUsers profileUpdateValidate(ProfileUpdate_req req, TokenRds token) {

		UserInfoRds userInfo = usersSrv.hgetUserInfoJsonObjRedis(token.getUserId(),
				UserInfoFieldRdsEnum.BASE_INFO.val());

		String countrycode = userInfo.getCountry_code();
		String cellphone = userInfo.getPhone_no();

		ProfileUpdate_req.User user = req.getBody().getUser();
		String countrycodeOpt = user.getCountrycode();
		String cellphoneOpt = user.getCellphone();

		if (countrycodeOpt == null) {
			countrycodeOpt = countrycode;
		}
		if (cellphoneOpt == null) {
			cellphoneOpt = cellphone;
		}
		if (countrycodeOpt != countrycode || cellphoneOpt != cellphone) {
			TUserUsers u = usersSrv.queryUserInfoByPhone(cellphoneOpt, countrycodeOpt);
			if (u != null) {
				throw new AppException(ResCodeAppEnum.PHONEorUSERNAME_EXIST.getCode(), "countrycode:" + countrycodeOpt
						+ "--cellphone:" + cellphoneOpt);
			}
			u = new TUserUsers();
			u.setCountry_code(countrycodeOpt);
			u.setPhone_no(cellphoneOpt);
			return u;
		}
		return new TUserUsers();
	}

	public AvatarUpdate_res avatarUpdate(AvatarUpdate_req req, TokenRds token) throws Exception {

		AvatarUpdate_req.Avatar avatar = req.getBody().getAvatar();
		String origin = avatar.getOrigin();
		String thumb = avatar.getThumb();
		long userId = token.getUserId();
		Timestamp sysdate = commonSrv.querySysdate();

		TUserUsers userdb = usersSrv.updateAvatar(origin, thumb, userId, sysdate);

		AvatarUpdate_res res = new AvatarUpdate_res();
		AvatarUpdate_res.User user = new AvatarUpdate_res.User();
		res.setUser(user);
		user.setThumb(userdb.getAvatar_thumb());
		user.setLastupdate(sysdate.getTime());

		return res;
	}

	public void avatarUpdateValidate(AvatarUpdate_req req, TokenRds token) {
	}

	public SendClientId_res sendClientId(SendClientId_req req, TokenRds token) throws Exception {

		long userId = token.getUserId();
		String clientId = req.getBody().getClient_id();
		commonSrv.hsetUserInfoStrRedis(userId, UserInfoFieldRdsEnum.CLIENT_ID.val(), clientId);

		AdvertisementSqs ads = new AdvertisementSqs();
		ads.setUser_id(userId);
		ads.setSendType((byte) 1);
		SqsUtil.pushMessage(ads, SysParamCodeDbEnum.SQS_ADVERTISEMENT.getParamCode());

		return new SendClientId_res();
	}

	public void sendClientIdValidate(SendClientId_req req, TokenRds token) {

	}

	public GetPlacesInfoRequest_res getPlacesInfoRequest(GetPlacesInfoRequest_req req, TokenRds token) throws Exception {

		String key = req.getBody().getKey();
		GoogleplaceAutocomplete_res gac = GooglePlaceUtil.autocomplete(key);
		ArrayList<Predictions> list = gac.getPredictions();

		GetPlacesInfoRequest_res res = new GetPlacesInfoRequest_res();
		ArrayList<GetPlacesInfoRequest_res.Places_info> placesInfoList = new ArrayList<GetPlacesInfoRequest_res.Places_info>();
		res.setPlaces_info(placesInfoList);
		res.setCount(list.size());

		for (Predictions p : list) {
			GetPlacesInfoRequest_res.Places_info pi = new GetPlacesInfoRequest_res.Places_info();
			pi.setPlace_id(p.getPlace_id());
			pi.setDescription(p.getDescription());
			placesInfoList.add(pi);
		}
		return res;
	}

	public void getPlacesInfoRequestValidate(GetPlacesInfoRequest_req req, TokenRds token) {

	}

	public QueryStateDate_res queryStateDate(QueryStateDate_req req, TokenRds token) {
		long userId = token.getUserId();
		String cld = usersSrv.hgetUserInfoStrRedis(userId, UserInfoFieldRdsEnum.CUSTOMER_LIST_DATE.val());
		String sld = usersSrv.hgetUserInfoStrRedis(userId, UserInfoFieldRdsEnum.SERVICER_LIST_DATE.val());
		String fld = usersSrv.hgetUserInfoStrRedis(userId, UserInfoFieldRdsEnum.FOLLOW_LIST_DATE.val());

		QueryStateDate_res res = new QueryStateDate_res();
		QueryStateDate_res.State_date_info sdi = new QueryStateDate_res.State_date_info();
		res.setState_date_info(sdi);
		if (cld != null) {
			sdi.setCustomer_list(Long.parseLong(cld));
		}
		if (sld != null) {
			sdi.setServicer_list(Long.parseLong(sld));
		}
		if (fld != null) {
			sdi.setFollow_list(Long.parseLong(fld));
		}
		return res;
	}

	public void queryStateDateValidate(QueryStateDate_req req, TokenRds token) {

	}
}
