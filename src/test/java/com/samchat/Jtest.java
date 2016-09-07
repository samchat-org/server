package com.samchat;

import java.io.File;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

public class Jtest {

	// redis服务器主机

	static String host = "samchat.unebf5.0001.cnn1.cache.amazonaws.com.cn";

	// 端口号

	static int port = 6379;

	public static void main(String[] args) throws Exception {

		AWSCredentials credentials = null;
		credentials = new BasicAWSCredentials("AKIAOGLPJB2XJV3WPYPA", "8A+gIJL/azSMIYxDqI3JXvXpH3S77pO936QP11Fy");
		AmazonS3Client s3 = new AmazonS3Client(credentials);
		// Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		// s3.setRegion(usWest2);
		TransferManager tx = new TransferManager(s3);

		File fileToUpload = new File("e:/download/credentials.csv");
		
		s3.putObject("samchat", "test", fileToUpload);
 	}
}