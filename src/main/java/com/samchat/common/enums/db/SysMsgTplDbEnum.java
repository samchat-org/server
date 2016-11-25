package com.samchat.common.enums.db;

public interface SysMsgTplDbEnum {
	public enum ActionCode{
		REGISTER_CODE_SMS("register_code_sms"),
		LOGIN_CODE_SMS("login_code_sms"),
		FINDPWD_CODE_SMS("findpwd_code_sms"),
		EDIT_CELL_PHONE_CODE_SMS("edit_cell_phone_code_sms"),
		SEND_INVITE_SMS("send_invite_sms");
		private String code;
		
		private ActionCode(String code){
			this.code = code;
		}
		public String val(){
			return this.code;
		}
	}

}
