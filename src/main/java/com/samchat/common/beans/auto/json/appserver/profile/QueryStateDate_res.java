package com.samchat.common.beans.auto.json.appserver.profile;

public class QueryStateDate_res{

	private long ret;
	private State_date_info state_date_info;

	public static class State_date_info {
		private long servicer_list;
		private long customer_list;
		private long follow_list;

		public long getServicer_list() {
			return servicer_list;
		}

		public void setServicer_list(long servicer_list) {
			this.servicer_list = servicer_list;
		}

		public long getCustomer_list() {
			return customer_list;
		}

		public void setCustomer_list(long customer_list) {
			this.customer_list = customer_list;
		}

		public long getFollow_list() {
			return follow_list;
		}

		public void setFollow_list(long follow_list) {
			this.follow_list = follow_list;
		}
	}

	public long getRet() {
		return ret;
	}

	public void setRet(long ret) {
		this.ret = ret;
	}

	public State_date_info getState_date_info() {
		return state_date_info;
	}

	public void setState_date_info(State_date_info state_date_info) {
		this.state_date_info = state_date_info;
	}

}