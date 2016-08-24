package com.samchat.common.beans.auto.db;

import java.io.Serializable;

/**
 * @author xucl
 * 
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -7957582811406436292L;

	private String table_name;

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
}