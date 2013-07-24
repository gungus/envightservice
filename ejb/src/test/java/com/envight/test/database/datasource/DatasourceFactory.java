package com.envight.test.database.datasource;

import javax.sql.DataSource;

public interface DatasourceFactory {
	public DataSource getDefaultDatasource() throws Exception;
}
