package com.pcm.daoservice;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AbstractDAO {
	protected SqlMapClient sqlMap = null;
	public SqlMapClient getSqlMap() {
		return sqlMap;
	}
	public void setSqlMap(SqlMapClient sqlMap) {
		this.sqlMap = sqlMap;
	}
}
