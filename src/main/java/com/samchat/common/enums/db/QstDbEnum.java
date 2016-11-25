package com.samchat.common.enums.db;

public interface QstDbEnum {
	
	public enum SendLogState {
 		ERROR((byte) -1), 
  		SEND_SUCCESS((byte) 2);
		
		private byte state;

		private SendLogState(byte state) {
			this.state = state;
		}
		
		public byte val(){
			return state;
		}
	}
	
	public enum ContentState {
		CANCEL((byte)-3),
		RECALL((byte) -2),
		ERROR((byte) -1), 
		WAIT((byte)0),
		SENDING((byte) 1),
		SEND_SUCCESS((byte) 2);
		
		private byte state;

		private ContentState(byte state) {
			this.state = state;
		}
		
		public byte val(){
			return state;
		}
	}
}
