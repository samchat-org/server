package com.samchat.service;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.samchat.service.interfaces.IBaseSrv;

public class BaseSrv<T> implements IBaseSrv{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 根据条件更新对应记录信息
	 * 
	 * @param sqlName
	 *            待更新SQL语句
	 * @param param
	 *            需持久化参数信息
	 * @return result 受影响行数(1执行成功,0执行失败)
	 */
	public int executeUpdateSql(String sqlName, Object param) {
		long startTime = System.currentTimeMillis();
		int retId =  (param == null) ? sqlSessionTemplate.update(sqlName)
				: sqlSessionTemplate.update(sqlName, param);
 		return retId;
	}

	/**
	 * 根据条件Object查询语句查询
	 * 
	 * @param sqlName
	 *            待查询SQL语句
	 * @param param
	 *            查询参数信息
	 * @return List<T> 对应的查询结果
	 */

	@SuppressWarnings("rawtypes")
	public List executeSqlList(String sqlName, Object param) {
         List resList = ((param == null) ? sqlSessionTemplate.selectList(sqlName)
				: sqlSessionTemplate.selectList(sqlName, param));
 		return resList;
	}

	/**
	 * 设置分页属性
	 * @param currentPage >= 1
	 * @param pageSize
	 */
	public void setPageConfig(int currentPage,int pageSize){
		PageHelper.startPage(currentPage,pageSize, true);
	}
	
	/**
	 * 获取分页信息
	 * @param resList
	 * @return
	 */
	public <T1 extends Object> PageInfo<T1> getPageInfo(List<T1> resList){
		PageInfo<T1> pageInfo = new PageInfo<T1>(resList);
		return pageInfo;
	}
	
	/**
	 * 返回翻页数据对象
	 * @param sqlName 自定义的sql名称 需要填命名空间
	 * @param paramMap
	 * @param currentPage 当前页数
	 * @param pageSize 页显示条数
	 * @return
	 */
	public PageInfo executePageSql(String sqlName, Map paramMap,int currentPage, int pageSize) {
		if (sqlName == null) {
			return new PageInfo();
		}

		if (currentPage == 0)
			currentPage = 1;
		if (pageSize == 0)
			pageSize = 10;
		
		//设置分页参数
		setPageConfig(currentPage,pageSize);
		
		List resList = executeSqlList(sqlName,paramMap);
		return getPageInfo(resList);
	}


	
	public Timestamp querySysdate() {
		Object ts = executeSqlList("query_sysdate", null).get(0);
		return (Timestamp)ts;
	}
}
