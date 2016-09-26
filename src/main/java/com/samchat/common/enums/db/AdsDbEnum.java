package com.samchat.common.enums.db;

public interface AdsDbEnum {
	
	public enum SendLogState {
		ERROR((byte) -1), 
		SEND_SUCCESS((byte) 1), 
		RECV_SUCCESS((byte) 2);
		
		private byte state;

		private SendLogState(byte state) {
			this.state = state;
		}
		
		public byte val(){
			return state;
		}
	}
}
