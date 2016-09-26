package com.samchat.common.enums.app;

public interface FollowAppEnum {

	public enum Follow {

		FOLLOW((byte) 1), UNFOLLOW((byte) 0);

		private byte follow;

		private Follow(byte follow) {
			this.follow = follow;
		}
		
		public byte val(){
			return follow;
		}
	}
}
