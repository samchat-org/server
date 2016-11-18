package com.samchat.common.enums.app;

public interface CommonAppEnum {
	
	public enum RecallType {

		QUESTION(1), ADVERTISEMENT(2);

		private int type;

		private RecallType(int type) {
			this.type = type;
		}
		public int val(){
			return type;
		}
	}
}
