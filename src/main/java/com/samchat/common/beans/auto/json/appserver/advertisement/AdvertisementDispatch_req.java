package com.samchat.common.beans.auto.json.appserver.advertisement;

import com.samchat.common.exceptions.AppException;
import com.samchat.common.enums.app.ResCodeAppEnum;
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
		private Long id;
		private Long adv_id;
		private Long publish_timestamp;
		private Long type;
		private String content;
		private String content_thumb;
		private Long dest_id;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getAdv_id() {
			return adv_id;
		}

		public void setAdv_id(Long adv_id) {
			this.adv_id = adv_id;
		}

		public Long getPublish_timestamp() {
			return publish_timestamp;
		}

		public void setPublish_timestamp(Long publish_timestamp) {
			this.publish_timestamp = publish_timestamp;
		}

		public Long getType() {
			if (type != null&& (type != 0 &&type != 1 &&type != 2 )){
				 throw new AppException(ResCodeAppEnum.PARAM_NONSUPPORT.getCode(), "value:" + type);
			}
			return type;
		}

		public void setType(Long type) {
			if (type != null&& (type != 0 &&type != 1 &&type != 2 )){
				 throw new AppException(ResCodeAppEnum.PARAM_NONSUPPORT.getCode(), "value:" + type);
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

		public Long getDest_id() {
			return dest_id;
		}

		public void setDest_id(Long dest_id) {
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