package com.skyworld.service;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class BaseSrv<T>{
	
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

	protected Class<T> clazz;
	protected Object mapper;
	
	public BaseSrv(){
	}
	
	public BaseSrv(Class<T> clazz){
		this.clazz = clazz;
	}
	
	private void setMapper(){
		String beanName = clazz.getSimpleName()+"Mapper";
		beanName = beanName.substring(0,1).toLowerCase() + beanName.substring(1);
//		mapper = BaseUtil.getBean(beanName);
	}
	
	protected <T1 extends Object> T1 getMapper(Class<T1> t){
		if(mapper == null)
			setMapper();
		return (T1)mapper;
	}
	
	public PageInfo<T> queryList(int currentPage, int pageSize)
			throws Exception {
		setPageConfig(currentPage,pageSize);
		
		if(mapper == null){
			setMapper();
		}
		
		String exampleBeanName = clazz.getName() +"Example";
		Class<?> clz = Class.forName(exampleBeanName);
		
		Method m = mapper.getClass().getMethod("selectByExample",clz);
		List<T> resList = (List<T>)m.invoke(mapper, clz.newInstance());
		return getPageInfo(resList);
	}

	public T queryById(Integer pid) throws Exception {
		if(mapper == null){
			setMapper();
		}
		Method m = mapper.getClass().getMethod("selectByPrimaryKey",Integer.class);
		return (T)m.invoke(mapper,pid);
	}

	public int update(T t) throws Exception {
		if(mapper == null){
			setMapper();
		}
		Method m = mapper.getClass().getMethod("updateByPrimaryKeySelective",clazz);
		return (Integer)m.invoke(mapper, t);
	}

	public int save(T t) throws Exception {
		if(mapper == null){
			setMapper();
		}
		Method m = mapper.getClass().getMethod("insertSelective",clazz);
		return (Integer)m.invoke(mapper, t);
	}

	public int delete(List pids) throws Exception {
		if(mapper == null){
			setMapper();
		}

	
/*		String criteria  =  exampleBeanName+"$Criteria";
		Class<?> clz = Class.forName(exampleBeanName); 
		Class<?> criteriaClass = Class.forName(criteria);
	    Method exampleMethod  = clz.getMethod("createCriteria");	createCriteria
	    criteriaClass =  (Class<?>) exampleMethod.invoke(clz);
	    Method  criteriaMethod =  criteriaClass.getMethod("andIdIn");
	    criteriaMethod.invoke(criteriaClass, pids);
	    Method mapperMethod = mapper.getClass().getMethod("deleteByExample");*/
		Method m = null;
		for(int i =0 ;i<pids.size() ;i++){
		m = mapper.getClass().getMethod("deleteByPrimaryKey",Integer.class);
	    m.invoke(mapper,Integer.valueOf(String.valueOf(pids.get(i))));
		}
		return 1;
	}
	
}
