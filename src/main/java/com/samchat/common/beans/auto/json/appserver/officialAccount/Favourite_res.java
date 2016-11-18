package com.samchat.common.beans.auto.json.appserver.officialAccount;

public class Favourite_res{

	private Long ret;
	private State_date state_date;

	public static class State_date {
		private Long previous;
		private Long last;

		public Long getPrevious() {
			return previous;
		}

		public void setPrevious(Long previous) {
			this.previous = previous;
		}

		public Long getLast() {
			return last;
		}

		public void setLast(Long last) {
			this.last = last;
		}
	}

	public Long getRet() {
		return ret;
	}

	public void setRet(Long ret) {
		this.ret = ret;
	}

	public State_date getState_date() {
		return state_date;
	}

	public void setState_date(State_date state_date) {
		this.state_date = state_date;
	}

}