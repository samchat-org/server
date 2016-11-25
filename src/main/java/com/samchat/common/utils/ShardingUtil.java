package com.samchat.common.utils;

import java.sql.Timestamp;

import com.samchat.common.beans.manual.common.MonthShardingBean;
import com.samchat.common.enums.Constant;

public class ShardingUtil {
	public static Long getMonthShardingId(int month, long id) {
		return new Long(month + "" + id);
	}

	public static MonthShardingBean getMonthSharding(long id) {
		String idStr = String.valueOf(id);
		MonthShardingBean b = new MonthShardingBean();
		b.setShardingFlag(Integer.parseInt(idStr.substring(0, 4)));
		b.setId(Long.parseLong(idStr.substring(4)));
		return b;
	}
	
	public static int getMonthSharding(Timestamp time){
		return Integer.parseInt(Constant.SDF_YYYYMM.format(time));
	}
}
