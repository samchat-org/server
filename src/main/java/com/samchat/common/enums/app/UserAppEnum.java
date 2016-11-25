package com.samchat.common.enums.app;

public interface UserAppEnum {

	
	public enum LoginType {

		SAMCHAT_ID(0), CELLPHONE(1), SMS_VERIFYCODE(2);

		private int type;

		private LoginType(int type) {
			this.type = type;
		}
		public int val(){
			return type;
		}
	}
	
	public enum PwdFlag{
		
		PWD_NULL(0), PWD_NOT_NULL(1);

		private int flag;

		private PwdFlag(int flag) {
			this.flag = flag;
		}
		public int val(){
			return flag;
		}
	}
}
