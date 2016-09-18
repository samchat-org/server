package com.samchat.common.enums;

public enum AdsState {

	ERROR((byte) -1), SEND_SUCCESS((byte) 1), RECV_SUCCESS((byte) 2);

	private byte state;

	private AdsState(byte state) {
		this.state = state;
	}

	public byte getState() {
		return state;
	}
}
