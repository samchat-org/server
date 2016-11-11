package com.samchat.action;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.profile.AppkeyGet_req;
import com.samchat.common.beans.auto.json.appserver.profile.AppkeyGet_res;
import com.samchat.common.beans.auto.json.appserver.profile.AvatarUpdate_req;
import com.samchat.common.beans.auto.json.appserver.profile.AvatarUpdate_res;
import com.samchat.common.beans.auto.json.appserver.profile.EditCellPhoneCodeRequest_req;
import com.samchat.common.beans.auto.json.appserver.profile.EditCellPhoneCodeRequest_res;
import com.samchat.common.beans.auto.json.appserver.profile.EditCellPhoneUpdate_req;
import com.samchat.common.beans.auto.json.appserver.profile.EditCellPhoneUpdate_res;
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
import com.samchat.common.beans.auto.json.appserver.profile.UpdateQuestionNotify_req;
import com.samchat.common.beans.auto.json.appserver.profile.UpdateQuestionNotify_res;
import com.samchat.common.beans.manual.common.SysdateObjBean;
import com.samchat.common.beans.manual.json.redis.TokenMappingRds;
import com.samchat.common.beans.manual.json.sqs.AdvertisementSqs;
import com.samchat.common.enums.Constant;
import com.samchat.common.enums.app.ResCodeAppEnum;
import com.samchat.common.enums.db.SysMsgTplDbEnum;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
import com.samchat.common.exceptions.AppException;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.GooglePlaceUtil;
import com.samchat.common.utils.SqsUtil;
import com.samchat.common.utils.TwilioUtil;
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

	public AppkeyGet_res appkeyGet(AppkeyGet_req req, TokenMappingRds token) {
		AppkeyGet_res res = new AppkeyGet_res();

		String appI = CommonUtil.getSysConfigStr(SysParamCodeDbEnum.GETUI_APP_ID.getParamCode());
		String appK = CommonUtil.getSysConfigStr(SysParamCodeDbEnum.GETUI_APP_KEY.getParamCode());
		String appS = CommonUtil.getSysConfigStr(SysParamCodeDbEnum.GETUI_APP_MASTER_SECRET.getParamCode());
		log.info("appI:" + appI + "---appK" + appI + "---appS:" + appS);

		res.setAppi(appI);
		res.setAppk(appK);
		res.setApps(appS);
		return res;
	}

	public void appkeyGetValidate(AppkeyGet_req req, TokenMappingRds token) {
	}

	public ProfileUpdate_res profileUpdate(ProfileUpdate_req req, TokenMappingRds token) throws Exception{

		SysdateObjBean sysdate = commonSrv.querySysdateObj();
		profileSrv.updateProfile_master(req, token.getUserId(), sysdate);

		ProfileUpdate_res res = new ProfileUpdate_res();
		ProfileUpdate_res.User userRes = new ProfileUpdate_res.User();
		res.setUser(userRes);
		userRes.setLastupdate(sysdate.getNow().getTime());

		return res;
	}

	public void profileUpdateValidate(ProfileUpdate_req req, TokenMappingRds token) throws Exception{}

	public AvatarUpdate_res avatarUpdate(AvatarUpdate_req req, TokenMappingRds token) throws Exception {

		AvatarUpdate_req.Avatar avatar = req.getBody().getAvatar();
		String origin = avatar.getOrigin();
		String thumb = avatar.getThumb();
		long userId = token.getUserId();
		SysdateObjBean sysdate = commonSrv.querySysdateObj();

		TUserUsers userdb = usersSrv.updateAvatar_master(origin, thumb, userId, sysdate);

		AvatarUpdate_res res = new AvatarUpdate_res();
		AvatarUpdate_res.User user = new AvatarUpdate_res.User();
		res.setUser(user);
		user.setThumb(userdb.getAvatar_thumb());
		user.setLastupdate(sysdate.getNow().getTime());

		return res;
	}

	public void avatarUpdateValidate(AvatarUpdate_req req, TokenMappingRds token) {
	}

	public SendClientId_res sendClientId(SendClientId_req req, TokenMappingRds token) throws Exception {

		long userId = token.getUserId();
		String clientId = req.getBody().getClient_id();
		usersSrv.hsetUserInfoClientId(userId, clientId);
		AdvertisementSqs ads = new AdvertisementSqs();
		ads.setUser_id(userId);
		ads.setSendType((byte) 1);
		SqsUtil.pushMessage(ads, SysParamCodeDbEnum.SQS_ADVERTISEMENT_URL.getParamCode());

		return new SendClientId_res();
	}

	public void sendClientIdValidate(SendClientId_req req, TokenMappingRds token) {

	}

	public GetPlacesInfoRequest_res getPlacesInfoRequest(GetPlacesInfoRequest_req req, TokenMappingRds token) throws Exception {

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

	public void getPlacesInfoRequestValidate(GetPlacesInfoRequest_req req, TokenMappingRds token) {

	}

	public QueryStateDate_res queryStateDate(QueryStateDate_req req, TokenMappingRds token) {
		long userId = token.getUserId();
		String cld = usersSrv.hgetUserInfoCustomerListDate(userId);
		String sld = usersSrv.hgetUserInfoServicerListDate(userId);
		String fld = usersSrv.hgetUserInfoFollowListDate(userId);

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

	public void queryStateDateValidate(QueryStateDate_req req, TokenMappingRds token) {

	}
	
	/**
	 * 修改手机号码验证码申请
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public EditCellPhoneCodeRequest_res editCellPhoneCodeRequest(EditCellPhoneCodeRequest_req req, TokenMappingRds token) throws Exception {

		String countryCode = req.getBody().getCountrycode();
		String cellPhone = req.getBody().getCellphone();
		
		String code = profileSrv.getEditCellPhoneVerificationCode(countryCode, cellPhone);
		String verificationCode = code;
		if(verificationCode == null){
			verificationCode = CommonUtil.getRadom(4);
		}
 		
		log.info("countryCode:" + countryCode + "--" + "cellphone:" + cellPhone + "--verificationCode:" + verificationCode);
		
		String smstpl = CommonUtil.getSysMsgTpl(SysMsgTplDbEnum.ActionCode.EDIT_CELL_PHONE_CODE_SMS.val());
		String smsContent = smstpl.replaceAll(Constant.TWILLO_VERIFICATION_CODE, verificationCode);
		log.info("smsContent:" + smsContent);
		TwilioUtil.sendSms(countryCode, cellPhone, smsContent);
		
		if(code == null){
			profileSrv.putEditCellPhoneVerificationCode(countryCode, cellPhone, verificationCode);
		}
		profileSrv.putEditCellPhoneVerificationCodeCtrl(countryCode, cellPhone);

		return new EditCellPhoneCodeRequest_res();
	}

	public void editCellPhoneCodeRequestValidate(EditCellPhoneCodeRequest_req req, TokenMappingRds token) {

		String countryCode = req.getBody().getCountrycode();
		String cellPhone = req.getBody().getCellphone();

		if (!CommonUtil.phoneNoFormatValidate(cellPhone)) {
			throw new AppException(ResCodeAppEnum.PHONE_FORMAT_ILLEGAL.getCode());
		}
		TUserUsers user = usersSrv.queryUserInfoByPhone(cellPhone, countryCode);
		if (user != null) {
			if(user.getUser_id() == token.getUserId()){
				throw new AppException(ResCodeAppEnum.SAME_PHONE_NO.getCode());
			}else{
				throw new AppException(ResCodeAppEnum.PHONEorUSERNAME_EXIST.getCode());
			}
		}
		if (profileSrv.getEditCellPhoneVerificationCodeCtrl(countryCode, cellPhone) != null) {
			throw new AppException(ResCodeAppEnum.VERIFICATION_CODE_FREQUENT.getCode());
		}
	}

	/**
	 * 手机号码更新
	 * 
	 * @param req
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public EditCellPhoneUpdate_res editCellPhoneUpdate(EditCellPhoneUpdate_req req, TokenMappingRds token) throws Exception {
		EditCellPhoneUpdate_req.Body body = req.getBody();
		String countryCode = body.getCountrycode();
		String cellPhone = body.getCellphone();
		SysdateObjBean sysdate = commonSrv.querySysdateObj();
		
		profileSrv.updatePhoneNo_master(token.getUserId(), countryCode, cellPhone, sysdate);
		EditCellPhoneUpdate_res res = new EditCellPhoneUpdate_res();
		EditCellPhoneUpdate_res.User user = new EditCellPhoneUpdate_res.User();
		user.setLastupdate(sysdate.getNow().getTime());
		res.setUser(user);
		
		return res;

	}

	public void editCellPhoneUpdateValidate(EditCellPhoneUpdate_req req, TokenMappingRds token) {
		
		EditCellPhoneUpdate_req.Body body = req.getBody();
		String countryCode = body.getCountrycode();
		String cellPhone = body.getCellphone();
		String verifycode = body.getVerifycode();

		if (!CommonUtil.phoneNoFormatValidate(cellPhone)) {
			throw new AppException(ResCodeAppEnum.PHONE_FORMAT_ILLEGAL.getCode());
		}
		String code = profileSrv.getEditCellPhoneVerificationCode(countryCode, cellPhone);
		if(code == null){
			throw new AppException(ResCodeAppEnum.VERIFICATION_CODE_EXPIRED.getCode());
		} else if(!code.equals(verifycode)){
			throw new AppException(ResCodeAppEnum.VERIFICATION_CODE.getCode());
		}
		TUserUsers user = usersSrv.queryUserInfoByPhone(cellPhone, countryCode);
		if (user != null) {
			if(user.getUser_id() == token.getUserId()){
				throw new AppException(ResCodeAppEnum.SAME_PHONE_NO.getCode());
			}else{
				throw new AppException(ResCodeAppEnum.PHONEorUSERNAME_EXIST.getCode());
			}
		}
	}
	
	public UpdateQuestionNotify_res updateQuestionNotify(UpdateQuestionNotify_req req, TokenMappingRds token) throws Exception{
		SysdateObjBean sysdate = commonSrv.querySysdateObj();
		profileSrv.updateQuestionNotify_master(req, token.getUserId(), sysdate);
		
		UpdateQuestionNotify_res res = new UpdateQuestionNotify_res();
		UpdateQuestionNotify_res.User user = new UpdateQuestionNotify_res.User();
		user.setLastupdate(sysdate.getNow().getTime());
		res.setUser(user);
		return res;	
	}
	
	public void updateQuestionNotifyValidate(UpdateQuestionNotify_req req, TokenMappingRds token){	
	}
}
