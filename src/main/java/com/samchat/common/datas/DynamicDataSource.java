package com.samchat.common.datas;

import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	@Override
	public Object determineCurrentLookupKey() {
		return DbContextHolder.getDbType();
	}

	public Logger getParentLogger() {
		return null;
	}
}
