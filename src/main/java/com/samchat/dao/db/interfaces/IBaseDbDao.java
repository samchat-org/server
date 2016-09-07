package com.samchat.dao.db.interfaces;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

public interface IBaseDbDao {
	public int executeUpdateSql(String sqlName, Object param);

	public List<Object> executeSqlList(String sqlName, Object param);

	public Object executeSqlOne(String sqlName, Object param);

	public <T1 extends Object> PageInfo<T1> getPageInfo(List<T1> resList);

	public PageInfo<Object> executePageSql(String sqlName, Map paramMap, int currentPage, int pageSize);

}
