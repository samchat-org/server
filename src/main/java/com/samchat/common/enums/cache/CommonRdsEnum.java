package com.samchat.common.enums.cache;

public interface CommonRdsEnum {
	
	public enum RecallState {

		RECALL(1), INIT(0);

		private int type;

		private RecallState(int type) {
			this.type = type;
		}
		public int val(){
			return type;
		}
	}
}
