package com.samchat;

import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;
import com.amazonaws.services.cognitoidentity.model.GetIdRequest;
import com.amazonaws.services.cognitoidentity.model.GetIdResult;

public class Jtest {

	// redis服务器主机

	static String host = "samchat.unebf5.0001.cnn1.cache.amazonaws.com.cn";

	// 端口号

	static int port = 6379;

	public void download(){}

	public static void main(String[] args) throws Exception {
		AmazonCognitoIdentity identityClient = new AmazonCognitoIdentityClient(new AnonymousAWSCredentials());
		 
		// send a get id request. This only needs to be executed the first time
		// and the result should be cached.
		GetIdRequest idRequest = new GetIdRequest();
		idRequest.setAccountId("");
		idRequest.setIdentityPoolId("us-west-2:083f7106-00ad-4b9e-8e1d-2214d30975ba");
		// If you are authenticating your users through an identity provider
		// then you can set the Map of tokens in the request
		// Map providerTokens = new HashMap();
		// providerTokens.put(“graph.facebook.com”, “facebook session key”);
		// idRequest.setLogins(providerTokens);
		 
		GetIdResult idResp = identityClient.getId(idRequest);
		 
		String identityId = idResp.getIdentityId();
	}
}