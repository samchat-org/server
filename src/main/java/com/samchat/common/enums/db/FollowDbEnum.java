package com.samchat.common.enums.db;

public interface FollowDbEnum {
	public enum Block{
		
		BLOCK((byte)1),UNBLOCK((byte)0);
		
		private byte isBlock;
		
		private Block(byte isBlock){
			this.isBlock = isBlock;
		}
		
		public byte val(){
			return isBlock;
		}
	}
}
