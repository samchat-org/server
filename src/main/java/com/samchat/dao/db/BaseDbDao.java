package com.samchat.dao.db;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.samchat.common.beans.manual.db.QrySequenceVO;
import com.samchat.dao.db.interfaces.IBaseDbDao;

public abstract class BaseDbDao implements IBaseDbDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int executeUpdateSql(String sqlName, Object param) {
		return sqlSessionTemplate.update(getNamespace() + "." + sqlName, param);
	}

	public int executeUpdateSql(String sqlName) {
		return sqlSessionTemplate.update(getNamespace() + "." + sqlName);
	}

	@SuppressWarnings("rawtypes")
	public List executeSqlList(String sqlName, Object param) {
		return sqlSessionTemplate.selectList(getNamespace() + "." + sqlName, param);
	}

	@SuppressWarnings("rawtypes")
	public List executeSqlList(String sqlName) {
		return sqlSessionTemplate.selectList(getNamespace() + "." + sqlName);
	}

	public Object executeSqlOne(String sqlName, Object param) {
		return sqlSessionTemplate.selectOne(getNamespace() + "." + sqlName, param);
	}

	public Object executeSqlOne(String sqlName) {
		return sqlSessionTemplate.selectOne(getNamespace() + "." + sqlName);
	}

	public void setPageConfig(int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize, true);
	}

	public <T1 extends Object> PageInfo<T1> getPageInfo(List<T1> resList) {
		PageInfo<T1> pageInfo = new PageInfo<T1>(resList);
		return pageInfo;
	}

	public PageInfo executePageSql(String sqlName, Map paramMap, int currentPage, int pageSize) {
		if (sqlName == null) {
			return new PageInfo();
		}

		if (currentPage == 0)
			currentPage = 1;
		if (pageSize == 0)
			pageSize = 10;

		// 设置分页参数
		setPageConfig(currentPage, pageSize);
		List resList = executeSqlList(getNamespace() + "." + sqlName, paramMap);
		return getPageInfo(resList);
	}

	public void insert(String sqlName, Object param) {
		sqlSessionTemplate.insert(getNamespace() + "." + sqlName, param);
	}

	public void insert(String sqlName) {
		sqlName = this.getNamespace() + "." + sqlName;
		sqlSessionTemplate.insert(getNamespace() + "." + sqlName);
	}

	public Long querySeqId(String seqName) {
		QrySequenceVO sq = new QrySequenceVO();
		sq.setSeq_name(seqName);
		sqlSessionTemplate.insert("commonSqlMapper.query_seqId", sq);
		return sq.getSeq_id();
	}

	public Timestamp querySysdate() {
		return (Timestamp) sqlSessionTemplate.selectOne("commonSqlMapper.query_sysdate");
	}

	protected abstract String getNamespace();
}
