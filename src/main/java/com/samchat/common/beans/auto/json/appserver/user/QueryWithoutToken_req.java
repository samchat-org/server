package com.samchat.common.beans.auto.json.appserver.user;

import com.samchat.common.exceptions.AppException;
import com.samchat.common.Constant;
public class QueryWithoutToken_req{

	private Header header;
	private Body body;

	public static class Header {
		private String action;

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = (action == null? null : action.trim());
		}
	}

	public static class Body {
		private long opt;
		private Param param;

		public long getOpt() {
			return opt;
		}

		public void setOpt(long opt) {
			this.opt = opt;
		}

		public Param getParam() {
			return param;
		}

		public void setParam(Param param) {
			this.param = param;
		}
	}

	public static class Param {
		private long type;
		private String username;
		private String countrycode;
		private String cellphone ;

		public long getType() {
			return type;
		}

		public void setType(long type) {
			if (type != 0 &&type != 2 ){
				 throw new AppException(Constant.ERROR.PARAM_NONSUPPORT, "value:" + type);
			}
			this.type = type;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = (username == null? null : username.trim());
		}

		public String getCountrycode() {
			return countrycode;
		}

		public void setCountrycode(String countrycode) {
			this.countrycode = (countrycode == null? null : countrycode.trim());
		}

		public String getCellphone () {
			return cellphone ;
		}

		public void setCellphone (String cellphone ) {
			this.cellphone  = (cellphone  == null? null : cellphone .trim());
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