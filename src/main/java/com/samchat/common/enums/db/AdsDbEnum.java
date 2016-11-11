package com.samchat.common.enums.db;

public interface AdsDbEnum {
	
	public enum SendLogState {
		CANCEL((byte) -2),
		ERROR((byte) -1), 
		WAIT((byte)0),
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
