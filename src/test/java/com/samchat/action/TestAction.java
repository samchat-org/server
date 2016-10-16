package com.samchat.action;

import java.util.ArrayList;

import com.samchat.common.beans.auto.json.appserver.profile.GetPlacesInfoRequest_req;
import com.samchat.common.beans.auto.json.appserver.profile.GetPlacesInfoRequest_res;
import com.samchat.common.beans.auto.json.appserver.profile.GoogleplaceAutocomplete_res;
import com.samchat.common.beans.auto.json.appserver.profile.GoogleplaceAutocomplete_res.Predictions;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.utils.GooglePlaceUtil;

public class TestAction  extends BaseAction{
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
}
