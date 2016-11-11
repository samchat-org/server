package com.samchat.common.enums.db;

public class UserDbEnum {
	public enum QuestionNotify {

		NOTIFY((byte) 1), NO_NOTIFY((byte) 0);

		private byte notify;

		private QuestionNotify(byte notify) {
			this.notify = notify;
		}

		public byte val() {
			return notify;
		}
	}
}
