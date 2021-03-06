package com.samchat.common.beans.auto.json.appserver.question;

public class DispatchQuestion_req{

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
		private Long datetime;
		private Long question_id;
		private String question;
		private Long opt;
		private Long dest_id;
		private String address;
		private User user;

		public Long getDatetime() {
			return datetime;
		}

		public void setDatetime(Long datetime) {
			this.datetime = datetime;
		}

		public Long getQuestion_id() {
			return question_id;
		}

		public void setQuestion_id(Long question_id) {
			this.question_id = question_id;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = (question == null? null : question.trim());
		}

		public Long getOpt() {
			return opt;
		}

		public void setOpt(Long opt) {
			this.opt = opt;
		}

		public Long getDest_id() {
			return dest_id;
		}

		public void setDest_id(Long dest_id) {
			this.dest_id = dest_id;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = (address == null? null : address.trim());
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
	}

	public static class User {
		private Long id;
		private String username;
		private Long lastupdate;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = (username == null? null : username.trim());
		}

		public Long getLastupdate() {
			return lastupdate;
		}

		public void setLastupdate(Long lastupdate) {
			this.lastupdate = lastupdate;
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