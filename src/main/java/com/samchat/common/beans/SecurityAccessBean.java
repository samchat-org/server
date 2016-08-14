package com.samchat.common.beans;

public class SecurityAccessBean<T> {

	private volatile byte state = 0;

	private T value;

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
