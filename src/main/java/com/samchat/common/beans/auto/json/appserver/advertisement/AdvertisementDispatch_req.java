package com.samchat.common.beans.auto.json.appserver.advertisement;

import com.samchat.common.exceptions.AppException;
import com.samchat.common.enums.Constant;
public class AdvertisementDispatch_req{

	private Header header;
	private Body body;

	public static class Header {
		private String category;

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = (category == null? null : category.trim());
		}
	}

	public static class Body {
		private long id;
		private long adv_id;
		private long publish_timestamp;
		private long type;
		private String content;
		private String content_thumb;
		private long dest_id;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getAdv_id() {
			return adv_id;
		}

		public void setAdv_id(long adv_id) {
			this.adv_id = adv_id;
		}

		public long getPublish_timestamp() {
			return publish_timestamp;
		}

		public void setPublish_timestamp(long publish_timestamp) {
			this.publish_timestamp = publish_timestamp;
		}

		public long getType() {
			return type;
		}

		public void setType(long type) {
			if (type != 0 &&type != 1 &&type != 2 ){
				 throw new AppException(Constant.ERROR.PARAM_NONSUPPORT, "value:" + type);
			}
			this.type = type;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = (content == null? null : content.trim());
		}

		public String getContent_thumb() {
			return content_thumb;
		}

		public void setContent_thumb(String content_thumb) {
			this.content_thumb = (content_thumb == null? null : content_thumb.trim());
		}

		public long getDest_id() {
			return dest_id;
		}

		public void setDest_id(long dest_id) {
			this.dest_id = dest_id;
		}
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}