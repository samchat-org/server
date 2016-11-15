package com.samchat.common.beans.auto.json.appserver.profile;

public class QueryStateDate_res{

	private Long ret;
	private State_date_info state_date_info;

	public static class State_date_info {
		private Long servicer_list;
		private Long customer_list;
		private Long follow_list;

		public Long getServicer_list() {
			return servicer_list;
		}

		public void setServicer_list(Long servicer_list) {
			this.servicer_list = servicer_list;
		}

		public Long getCustomer_list() {
			return customer_list;
		}

		public void setCustomer_list(Long customer_list) {
			this.customer_list = customer_list;
		}

		public Long getFollow_list() {
			return follow_list;
		}

		public void setFollow_list(Long follow_list) {
			this.follow_list = follow_list;
		}
	}

	public Long getRet() {
		return ret;
	}

	public void setRet(Long ret) {
		this.ret = ret;
	}

	public State_date_info getState_date_info() {
		return state_date_info;
	}

	public void setState_date_info(State_date_info state_date_info) {
		this.state_date_info = state_date_info;
	}

}