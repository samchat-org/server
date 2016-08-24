package com.samchat.common.beans.manual.json.redis;

public class QuSendCtlRds {
	
	private byte block;
	
	private long first;

	private long last;

	private int count;

	public byte getBlock() {
		return block;
	}

	public void setBlock(byte block) {
		this.block = block;
	}

	public long getFirst() {
		return first;
	}

	public void setFirst(long first) {
		this.first = first;
	}

	public long getLast() {
		return last;
	}

	public void setLast(long last) {
		this.last = last;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
 


}
