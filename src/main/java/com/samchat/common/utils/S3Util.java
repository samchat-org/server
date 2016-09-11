package com.samchat.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.log4j.Logger;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3Util {

	private static Logger log = Logger.getLogger(S3Util.class);

	public static String getThumbObject(String s3url) throws Exception {

		String accessKey = "AKIAJQOK3VMVG7CXXA3A";// CommonUtil.getSysConfigStr("aws_access_key");
		String secretKey = "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";// CommonUtil.getSysConfigStr("aws_secret_key");
		AmazonS3 s3c = new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey));
		
		java.net.URL  url = new  java.net.URL(s3url);
		String endpoint = url.getProtocol() + "://" + url.getHost();
		String curl = url.getPath().substring(1);
		int idx = curl.indexOf("/");
		String bucket = curl.substring(0, idx);
		String key = curl.substring(idx + 1);
		
		s3c.setEndpoint(endpoint);
		
		//String s3Endpoint = CommonUtil.getSysConfigStr("aws_s3_endpoint");
 		// "https://s3-us-west-2.amazonaws.com"
		ObjectListing ol = s3c.listObjects(bucket, key);
		List<S3ObjectSummary> solst = ol.getObjectSummaries();
		if (solst.size() == 1 && solst.get(0).getKey().equals(key)) {

			S3Object so = s3c.getObject(bucket, key);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
				int width = CommonUtil.getSysConfigInt("aws_s3_avatar_thumb_width");
				int height = CommonUtil.getSysConfigInt("aws_s3_avatar_thumb_height");
				Thumbnails.of(so.getObjectContent()).size(width, height).toOutputStream(out);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			} finally {
				out.close();
			}
			byte[] dataThumb = out.toByteArray();
			ByteArrayInputStream in = new ByteArrayInputStream(dataThumb);
			
			File keyfile = new File(key);
			String origin = keyfile.getName();
			String thumb = "thumb_" + origin.substring(origin.indexOf("_") + 1);
			String thumbPath = keyfile.getParentFile().getParent() + "/thumb/" + thumb;
			try {
				ObjectMetadata meta = new ObjectMetadata();
				meta.setContentLength(dataThumb.length);
				s3c.putObject(bucket, thumbPath, in, meta);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			} finally {
				in.close();
			}
			return endpoint + "/" + bucket + "/" + thumbPath;
		} else {
			throw new Exception("bucket:" + bucket + "--key:" + key + " not exists");
		}
	}

	public static void main(String args[]) throws Exception {
		String s3url = "https://s3-us-west-2.amazonaws.com/samchat2/advertisement/origin/orig_1000030_1473412182276.jpg";

	}

}
