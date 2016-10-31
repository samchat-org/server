package com.samchat.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.log4j.Logger;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3Util {

	private static Logger log = Logger.getLogger(S3Util.class);
	
	private static String getBucketMapping(String name){
		String bucketMapping = CommonUtil.getSysConfigStr("aws_s3_bucket_mapping");
		String[] mappings = bucketMapping.split(",");
		
		HashMap<String, String> hs = new HashMap<String, String>();
		for(String mapping : mappings){
			String [] kv = mapping.split(":");
			hs.put(kv[0].trim(), kv[1].trim());
		}
		return hs.get(name);
	}

	public static String getThumbObject(String s3url) throws Exception {

		AmazonS3 s3c = new AmazonS3Client();
		java.net.URL url = new java.net.URL(s3url);
		String bucket = url.getHost();
		String key = url.getPath().substring(1);

		String endpoint = CommonUtil.getSysConfigStr("aws_s3_endpoint");
		s3c.setEndpoint(endpoint);

		ObjectListing ol = s3c.listObjects(bucket, key);
		List<S3ObjectSummary> solst = ol.getObjectSummaries();
		if (solst.size() == 1 && solst.get(0).getKey().equals(key)) {

			S3Object so = s3c.getObject(bucket, key);
			S3ObjectInputStream s3ois = so.getObjectContent();
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			int width = CommonUtil.getSysConfigInt("aws_s3_avatar_thumb_width");
			int height = CommonUtil.getSysConfigInt("aws_s3_avatar_thumb_height");
			int quality = CommonUtil.getSysConfigInt("aws_s3_avatar_thumb_quality");
			
			try {
				Thumbnails.of(s3ois).size(width, height).outputQuality(new BigDecimal(quality).divide(new BigDecimal(100)).doubleValue()).toOutputStream(out);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			} finally {
				s3ois.close();
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
			return url.getProtocol() + "://" + url.getHost() + "/" + thumbPath;
		} else {
			throw new Exception("bucket:" + bucket + "--key:" + key + " not exists");
		}
	}

	public static String getThumbPath(String s3url) throws Exception {
		java.net.URL url = new java.net.URL(s3url);
 		String key = url.getPath().substring(1);
		
		File keyfile = new File(key);
		String origin = keyfile.getName();
		String thumb = "thumb_" + origin.substring(origin.indexOf("_") + 1);
		String thumbPath = keyfile.getParentFile().getParent() + "/thumb/" + thumb;
		return url.getProtocol() + "://" + url.getHost() + "/" + thumbPath;
		
	}

	public static void main(String args[]) throws Exception {

		Thumbnails.of(new File("d:/nim_g_unread_badge.9.png")).size(12, 12).toFile(new File("d:/nim_g_unread_badge.9_t.png"));
		
	}

}
